package de.meningococcus.episcangis.satscan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.SatScanCluster;
import de.meningococcus.episcangis.db.model.SatScanExecution;
import de.meningococcus.episcangis.db.model.SatScanJob;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SatScanDaemon
{
  private static Log log = LogFactory.getLog(SatScanDaemon.class);

  private SatScanExecutor satscan;

  private SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();

  public SatScanDaemon() throws IllegalArgumentException, IOException,
      ConfigurationException, SQLException
  {
    String satScanExecutable, prmTemplateFile;

    Configuration config;
    String configfile = "conf/epidegis-satscan.properties";

    config = new PropertiesConfiguration(configfile);
    if (config.containsKey("satscan.executable"))
    {
      satScanExecutable = config.getString("satscan.executable", "");
    }
    else
    {
      throw new ConfigurationException(
          "Could not find property 'satscan.executable' in epidegis-satscan.properties.");
    }

    if (config.containsKey("satscan.prmFileTemplate"))
    {
      prmTemplateFile = config.getString("satscan.prmFileTemplate");
    }
    else
    {
      throw new ConfigurationException(
          "Could not find property 'satscan.prmFileTemplate' in epidegis-satscan.properties.");
    }
    satscan = new SatScanExecutor(satScanExecutable, prmTemplateFile);

  }

  public void scheduleJobs()
  {
    Collection<SatScanJob> jobs = ssDao.getSatScanJobs();
    int count = 0;
    for (SatScanJob job : jobs)
    {
      SatScanJobScheduler scheduler = new SatScanJobScheduler(job);
      scheduler.schedule();
      count++;
    }
    log.info("Processed " + count + " job(s).");
  }

  public void runBatchExecution()
  {
    SatScanExecution exe;
    while ((exe = ssDao.getEarliestSatScanExecution()) != null)
    {
      Vector<SatScanCluster> detectedClusters = satscan.execute(exe);
      if (detectedClusters != null)
      {
        for (SatScanCluster cluster : detectedClusters)
        {
          ssDao.createSatScanCluster(cluster);
        }
        ssDao.deleteSatScanExecution(exe);
      }
      else
      {
        throw new SatScanRuntimeException(
            "Something went wrong during SaTScan batch execution.");
      }
    }
  }

  public static void main(String[] args)
  {
    try
    {
      SatScanDaemon daemon = new SatScanDaemon();
      daemon.scheduleJobs();
      daemon.runBatchExecution();
    }
    catch (IOException e)
    {
      log.fatal(e.getMessage());
      log.fatal("Check if SaTScan is installed, and property "
          + "satscan.executable points to the correct location.");
    }
    catch (Exception e)
    {
      log.fatal(e.getMessage());
      e.printStackTrace();
    }
    System.exit(0);
  }
}
