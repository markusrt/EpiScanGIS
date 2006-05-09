package de.meningococcus.episcangis.satscan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaDAO;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.Area;
import de.meningococcus.episcangis.db.model.CaseType;
import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanCluster;
import de.meningococcus.episcangis.db.model.SatScanExecution;
import de.meningococcus.episcangis.db.model.SatScanJob;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SatScanExecutor
{
  private static Log log = LogFactory.getLog(SatScanExecutor.class);

  private static final int GISFILE_TOKENCOUNT = 9, GISFILE_LOCATION_ID = 0,
      GISFILE_CLUSTER_NUMBER = 1, GISFILE_OBSERVED_CASES = 2,
      GISFILE_EXPECTED_CASES = 3, GISFILE_OBSERVED_TO_EXPECTED_CASES = 4,
      GISFILE_CLUSTER_PVALUE = 5, GISFILE_OBSERVED_CASES_LOCATION = 6,
      GISFILE_EXPECTED_CASES_LOCATION = 7,
      GISFILE_OBSERVED_TO_EXPECTED_CASES_LOCATION = 8;

  private static final int COLFILE_TOKENCOUNT = 13,
      COLFILE_MOST_CENTRAL_ID = 0, COLFILE_CLUSTER_NUMBER = 1,
      COLFILE_LATITUDE = 2, COLFILE_LONGITUDE = 3, COLFILE_CIRCLE_RADIUS = 4,
      COLFILE_LOCATIONS_IN_CLUSTER = 5, COLFILE_OBSERVED_CASES = 6,
      COLFILE_EXPECTED_CASES = 7, COLFILE_OBSERVED_TO_EXPECTED_CASES = 8,
      COLFILE_LLR = 9, COLFILE_PVALUE = 10, COLFILE_CLUSTER_START_DATE = 11,
      COLFILE_CLUSTER_END_DATE = 12;

  private static final String VERSION = "v5.1.3.";

  private BufferedReader satscan_out, satscan_err;

  private String executable, parameterTemplate;

  private static FastDateFormat satScanDateFormat = FastDateFormat
      .getInstance("yyyy/MM/dd");

  public SatScanExecutor(String satscanExecutable, String parameterFile)
      throws IOException
  {
    boolean isCorrectVersion = false;
    executable = satscanExecutable;

    String line;
    startProcess("");
    while ((line = satscan_out.readLine()) != null)
    {
      if (line.contains(VERSION))
      {
        isCorrectVersion = true;
        log.debug(line);
        break;
      }
    }
    if (!isCorrectVersion)
    {
      log.warn("SaTScan program Version does not match. Daemon is written for "
          + VERSION + ".");
    }

    InputStream templateFile = new FileInputStream(new File(parameterFile));
    try
    {
      parameterTemplate = IOUtils.toString(templateFile);
    }
    finally
    {
      IOUtils.closeQuietly(templateFile);
    }
  }

  public Vector<SatScanCluster> execute(SatScanExecution execution)
  {
    Vector<SatScanCluster> detectedClusters = new Vector<SatScanCluster>();
    try
    {
      AreaDAO aDao = DaoFactory.getDaoFactory().getAreaDAO();
      AreaTypeDAO atDao = DaoFactory.getDaoFactory().getAreaTypeDAO();
      CaseTypeDAO ctDao = DaoFactory.getDaoFactory().getCaseTypeDAO();
      ReportedCaseDAO rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
      SatScanJob job = execution.getJob();
//      Date observationBegin = job.getObservationBegin(execution
//          .getPlannedExecution()), observationEnd = job
//          .getObservationEnd(execution.getPlannedExecution());
      Date observationBegin = execution.getObservationBegin(), 
        observationEnd = execution.getObservationEnd();

      log.info("Running execution, analysing period " + observationBegin
          + " - " + observationEnd);

      File casFile = File.createTempFile("epidegis-satscan-"
          + execution.getPlannedExecution() + "-", ".cas");
      String filePrefix = casFile.getAbsolutePath().substring(0,
          casFile.getAbsolutePath().lastIndexOf('.'));
      File popFile = new File(filePrefix + ".pop");
      // File popFile = new File("G:\\temp\\speedy" + ".pop");

      File prmFile = new File(filePrefix + ".prm");
      // File geoFile = new File("G:\\temp\\speedy" + ".geo");

      File geoFile = new File(filePrefix + ".geo");
      File outFile = new File(filePrefix + ".txt");
      File colOutFile = new File(filePrefix + ".col.txt");
      File gisOutFile = new File(filePrefix + ".gis.txt");

      log.info("Preparing SatScan Execution in directory "
          + casFile.getParent());
      String prmTemplate = parameterTemplate;
      prmTemplate = StringUtils.replace(prmTemplate, "${casfile}", casFile
          .getAbsolutePath());
      prmTemplate = StringUtils.replace(prmTemplate, "${popfile}", popFile
          .getAbsolutePath());
      prmTemplate = StringUtils.replace(prmTemplate, "${geofile}", geoFile
          .getAbsolutePath());
      prmTemplate = StringUtils.replace(prmTemplate, "${outfile}", outFile
          .getAbsolutePath());
      prmTemplate = StringUtils.replace(prmTemplate, "${startdate}",
          satScanDateFormat.format(observationBegin));
      prmTemplate = StringUtils.replace(prmTemplate, "${enddate}",
          satScanDateFormat.format(observationEnd));
      prmTemplate = StringUtils.replace(prmTemplate, "${analysistype}", String
          .valueOf(job.getAnalysistype()));
      prmTemplate = StringUtils.replace(prmTemplate, "${modeltype}", String
          .valueOf(job.getModeltype()));
      prmTemplate = StringUtils.replace(prmTemplate, "${scanareas}", String
          .valueOf(job.getScanareas()));
      prmTemplate = StringUtils.replace(prmTemplate, "${timeaggregationunits}",
          String.valueOf(job.getTimeaggregationunits()));
      prmTemplate = StringUtils.replace(prmTemplate,
          "${timeaggregationlength}", String.valueOf(job
              .getTimeaggregationlength()));
      prmTemplate = StringUtils.replace(prmTemplate, "${montecarloreps}",
          String.valueOf(job.getMontecarloreps()));
      prmTemplate = StringUtils.replace(prmTemplate, "${maxtemporalsize}",
          String.valueOf(job.getMaxTemporalSize()));

      FileUtils.writeStringToFile(prmFile, prmTemplate, "latin1");

      StringBuilder casFileBuf = new StringBuilder(2048);
      StringBuilder geoFileBuf = new StringBuilder(2048);
      StringBuilder popFileBuf = new StringBuilder(2048);

      // HashMap<Integer, Boolean> dupAreas = new HashMap<Integer, Boolean>();
      @SuppressWarnings("unchecked")
      Collection<ReportedCase> allCases = rcDao.getCases(4, execution
          .getCaseTypeId(), execution.getObservationBegin(), execution
          .getObservationEnd());

      int areaTypeId = 0;
      CaseType thisExesCaseType = ctDao.getCaseType(execution.getCaseTypeId());

      for (ReportedCase rc : allCases)
      {
        CaseType thisCasesCaseType = ctDao.getCaseType(rc.getCaseTypeId());
        if ((job.getAnalysistype() == SatScanJob.ANALYSISTYPE_RETROSPECTIVE_SPACE_TIME && thisCasesCaseType
            .getId() == thisExesCaseType.getId())
            || (job.getAnalysistype() == SatScanJob.ANALYSISTYPE_PROSPECTIVE_SPACE_TIME && thisCasesCaseType
                .isSimilarTo(thisExesCaseType))
            && !rc.getIncidenceDate().before(observationBegin))
        {
          Area area = aDao.getArea(rc.getAreaId());
          areaTypeId = area.getAreaTypeId();
          casFileBuf.append(area.getId() + "\t" + 1 + "\t"
              + satScanDateFormat.format(rc.getIncidenceDate()) + "\n");
        }
      }
      log.debug("ATI: " + areaTypeId);
      for (Area area : aDao.getAreas(atDao.getAreaType(areaTypeId)))
      {
        HashMap<String, Long> populations = aDao.getAreaPopulations(area
            .getId());
        geoFileBuf.append(area.getId() + "\t" + area.getCenterLon() + "\t"
            + area.getCenterLat() + "\n");
        for (String year : populations.keySet())
        {
          popFileBuf.append(area.getId() + "\t" + year + "\t"
              + populations.get(year) + "\n");
        }
      }

      FileUtils.writeStringToFile(casFile, casFileBuf.toString(), "latin1");
      FileUtils.writeStringToFile(geoFile, geoFileBuf.toString(), "latin1");
      FileUtils.writeStringToFile(popFile, popFileBuf.toString(), "latin1");

      startProcess(prmFile.getAbsolutePath());
      String line;
      while ((line = satscan_out.readLine()) != null)
      {
        log.debug(line);
      }
      while ((line = satscan_err.readLine()) != null)
      {
        log.error(line);
      }

      if (colOutFile.exists() && gisOutFile.exists())
      {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(colOutFile)));
        try
        {
          while ((line = br.readLine()) != null)
          {
            log.info(line);
            SatScanCluster cluster = createClusterFromResult(execution, line);

            BufferedReader gisReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(gisOutFile)));
            while ((line = gisReader.readLine()) != null)
            {
              StringTokenizer st = new StringTokenizer(line);
              if (st.countTokens() != GISFILE_TOKENCOUNT)
              {
                log.error("Token count in result file line '" + line
                    + "' is not as expected. Found " + st.countTokens()
                    + " expected " + GISFILE_TOKENCOUNT + ".");
              }
              else
              {
                int locationId = 0, clusterNr = 0;
                for (int token = 0; token < GISFILE_TOKENCOUNT; token++)
                {
                  String tokenValue = st.nextToken();
                  switch (token)
                  {
                  case GISFILE_LOCATION_ID:
                    locationId = Integer.valueOf(tokenValue);
                    break;
                  case GISFILE_CLUSTER_NUMBER:
                    clusterNr = Integer.valueOf(tokenValue);
                  default:
                    break;
                  }
                }
                if (cluster.getNumber() == clusterNr)
                {
                  for (ReportedCase rc : allCases)
                  {
                    if (rc.getAreaId() == locationId
                        && cluster.containsDate(rc.getIncidenceDate()))
                    {
                      cluster.addCase(rc);
                    }
                  }
                }
              }
            }

            if (cluster.isValid())
            {
              log.info("Detected cluster: " + cluster.getNumber());
              detectedClusters.add(cluster);
              String clusterInfo = FileUtils
                  .readFileToString(outFile, "latin1");
              log.info(clusterInfo);
            }
          }
        }
        finally
        {
          IOUtils.closeQuietly(br);
        }
      }
      else
      {
        log.error("Could not open SatScans 'Cluster Information File' ("
            + colOutFile.getAbsolutePath() + ").");
        detectedClusters = null;
      }
      if (detectedClusters == null || detectedClusters.size() == 0)
      {
        // Clean up
        casFile.delete();
        popFile.delete();
        geoFile.delete();
        prmFile.delete();
        colOutFile.delete();
        outFile.delete();
        gisOutFile.delete();
      }
      else
      {
        colOutFile.delete();
        gisOutFile.delete();
      }
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return detectedClusters;
  }

  /**
   * <p>
   * This function parses on line of one SatScans cluster information file. It
   * is described as follows:
   * </p>
   * <p>
   * For export to GIS software and other databases, most information about the
   * clusters is also provided in column format. This output file is optional,
   * and can be requested in ASCII and/or dBase format. Each cluster is one
   * line, and the columns are as follows:
   * </p>
   * <p>
   * &lt;Most Central Location&gt;<br/> &lt;Cluster Number&gt;<br/>
   * &lt;X-coordinate&gt; or &lt;Latitude&gt;<br/> &lt;Y-coordinate&gt; or
   * &lt;Longitude&gt;<br/> &lt;Circle Radius&gt;<br/> &lt;Number Location IDs
   * in Cluster&gt;<br/> &lt;Observed Cases&gt;<br/> &lt;Expected Cases&gt;<br/>
   * &lt;Observed/Expected in Cluster&gt;<br/> &lt;Log Likelihood Ratio> or
   * <Test Statistic&gt;<br/> &lt;P-Value of Cluster&gt;<br/> &lt;Cluster
   * Start Date&gt;<br/> &lt;Cluster End Date&gt;<br/><br>
   * <b>Example line:</b><br/> 9120 1 9.2384 50.0084 27.46 2 2 0.13 15.57
   * 3.733889 0.15500 2001/12/3 2001/12/10
   * </p>
   * @param job Clusters job
   * @param resultFileLine
   */
  private SatScanCluster createClusterFromResult(SatScanExecution exe,
      String resultFileLine)
  {
    SatScanCluster cluster = new SatScanCluster(exe);
    StringTokenizer st = new StringTokenizer(resultFileLine);

    if (st.countTokens() != COLFILE_TOKENCOUNT)
    {
      log.error("Token count in result file line '" + resultFileLine
          + "' is not as expected. Found " + st.countTokens() + " expected "
          + COLFILE_TOKENCOUNT + ".");
    }
    else
    {
      for (int token = 0; token < COLFILE_TOKENCOUNT; token++)
      {
        String tokenValue = st.nextToken();
        switch (token)
        {
        case COLFILE_MOST_CENTRAL_ID:
          cluster.setMostCentralLocation(Integer.valueOf(tokenValue));
          break;
        case COLFILE_CLUSTER_NUMBER:
          cluster.setNumber(Integer.valueOf(tokenValue));
          break;
        case COLFILE_CIRCLE_RADIUS:
          cluster.setCircleRadius(Float.parseFloat(tokenValue));
          break;
        case COLFILE_OBSERVED_CASES:
          cluster.setObservedCases(Integer.valueOf(tokenValue));
          break;
        case COLFILE_EXPECTED_CASES:
          cluster.setExpectedCases(Float.parseFloat(tokenValue));
          break;
        case COLFILE_LLR:
          cluster.setLLR(Double.parseDouble(tokenValue));
          break;
        case COLFILE_PVALUE:
          cluster.setPValue(Double.parseDouble(tokenValue));
          break;
        case COLFILE_CLUSTER_START_DATE:
          cluster.setStartDate(Date.valueOf(tokenValue.replace('/', '-')));
          break;
        case COLFILE_CLUSTER_END_DATE:
          cluster.setEndDate(Date.valueOf(tokenValue.replace('/', '-')));
          break;
        default:
          break;
        }
      }
    }
    return cluster;
  }

  private void startProcess(String options) throws IllegalArgumentException,
      IOException
  {
    Process ls_proc = Runtime.getRuntime()
        .exec(this.executable + " " + options);

    satscan_out = new BufferedReader(new InputStreamReader(new DataInputStream(
        ls_proc.getInputStream())));

    satscan_err = new BufferedReader(new InputStreamReader(new DataInputStream(
        ls_proc.getErrorStream())));

  }
}
