package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanCluster;
import de.meningococcus.episcangis.db.model.SatScanExecution;
import de.meningococcus.episcangis.db.model.SatScanJob;

/**
 * Implementation of the DAO SatScanDAO. This class uses Jakarta Commons Dbutils (<a
 * href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLSatScanDAO extends DbUtilsDAO implements SatScanDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLSatScanDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String GET_SATSCAN_JOBS = "SELECT satscan_job_id AS id, job_type AS jobtype, * FROM satscan_jobs",
      INSERT_SATSCAN_JOB = "INSERT INTO satscan_jobs "
          + "(name, analysistype, modeltype, scanareas, montecarloreps, timeaggregationunits, timeaggregationlength, job_type) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
      UPDATE_SATSCAN_JOB = "UPDATE satscan_jobs "
          + "SET name = ?, analysistype  = ?, modeltype  = ?, scanareas  = ?, "
          + "montecarloreps  = ?, timeaggregationunits  = ?, "
          + "timeaggregationlength  = ?, job_type  = ?, lastrun = ? "
          + " WHERE satscan_job_id = ?",
      GET_SATSCAN_JOB_ID = "SELECT satscan_job_id AS id, * FROM satscan_jobs WHERE satscan_job_id=?",
      GET_FIRST_SATSCAN_EXECUTION = "SELECT case_type_id, satscan_job_id, planned_execution FROM satscan_executions ORDER BY planned_execution LIMIT 1",
      INSERT_SATSCAN_EXECUTION = "INSERT INTO satscan_executions "
          + "(satscan_job_id, case_type_id, planned_execution) "
          + "VALUES (?, ?, ?)",
      DELETE_SATSCAN_EXECUTION = "DELETE FROM satscan_executions WHERE satscan_job_id=? AND case_type_id=? AND planned_execution=?",
      INSERT_SATSCAN_CLUSTER = "INSERT INTO satscan_clusters "
          + "(area_id, number, satscan_job_id, case_type_id, expectedcases, "
          + "circleradius, llr, pvalue, analysisdate, startdate, enddate ) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      GET_SATSCAN_CLUSTER_CLUSTER = "SELECT satscan_cluster_id AS id, "
          + "area_id AS mostCentralLocation, satscan_job_id AS jobId, * FROM satscan_clusters "
          + "WHERE number=? AND satscan_job_id=? AND case_type_id = ? AND analysisdate=? ORDER BY satscan_cluster_id DESC",
      GET_CLUSTER_ANALYSISDATES_JOBID = "SELECT analysisdate FROM satscan_clusters WHERE satscan_job_id=? GROUP BY analysisdate ORDER BY analysisdate",
      INSERT_SATSCAN_CLUSTER_CASES = "INSERT INTO satscan_cluster_cases "
          + "(satscan_cluster_id, case_id)" + "VALUES (?, ?)";

  PgSQLSatScanDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.SatScanDAO#getSatscanJobs()
   */
  @SuppressWarnings("unchecked")
  public Collection<SatScanJob> getSatScanJobs()
  {
    List<SatScanJob> result = null;
    try
    {
      result = (List<SatScanJob>) run.query(GET_SATSCAN_JOBS,
          new BeanListHandler(SatScanJob.class));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see org.epidegis.db.dao.SatScanDAO#insertSatscanJob(de.meningococcus.episcangis.db.model.SatScanJob)
   */
  public int createSatScanJob(SatScanJob job)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(INSERT_SATSCAN_JOB, new Object[] {
          job.getName(), job.getAnalysistype(), job.getModeltype(),
          job.getScanareas(), job.getMontecarloreps(),
          job.getTimeaggregationunits(), job.getTimeaggregationlength(),
          job.getJobtype() });
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.SatScanDAO#getSatscanJob(int)
   */
  public SatScanJob getSatScanJob(int id)
  {
    SatScanJob ret = null;
    try
    {
      ret = (SatScanJob) run.query(GET_SATSCAN_JOB_ID, id, new BeanHandler(
          SatScanJob.class));
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.SatScanDAO#getFirstSatscanExecution()
   */
  public SatScanExecution getEarliestSatScanExecution()
  {
    SatScanExecution ret = null;
    try
    {
      Object[] result = (Object[]) run.query(GET_FIRST_SATSCAN_EXECUTION,
          new ArrayHandler());
      if (result != null && result.length == 3)
      {
        Integer caseTypeId = (Integer) result[0];
        SatScanJob job = getSatScanJob(((Integer) result[1]).intValue());
        Date plannedExecution = (Date) result[2];
        if (caseTypeId != null && job != null && plannedExecution != null)
        {
          ret = new SatScanExecution(caseTypeId.intValue(), job,
              plannedExecution);
        }
      }
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see org.epidegis.db.dao.SatScanDAO#deleteSatscanExecution(de.meningococcus.episcangis.db.model.SatScanExecution)
   */
  public int deleteSatScanExecution(SatScanExecution execution)
  {
    int deleteCount = 0;
    try
    {
      deleteCount = run.update(DELETE_SATSCAN_EXECUTION,
          new Object[] { execution.getJob().getId(), execution.getCaseTypeId(),
              execution.getPlannedExecution() });
    }
    catch (SQLException e)
    {
      log.error("SQL Query '" + DELETE_SATSCAN_EXECUTION + "' caused error: "
          + e.getMessage());
    }
    return deleteCount;
  }

  /*
   * (non-Javadoc)
   * @see org.epidegis.db.dao.SatScanDAO#createSatscanExecution(de.meningococcus.episcangis.db.model.SatScanExecution)
   */
  public int createSatScanExecution(SatScanExecution execution)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(INSERT_SATSCAN_EXECUTION,
          new Object[] { execution.getJob().getId(), execution.getCaseTypeId(),
              execution.getPlannedExecution() });
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * @see org.epidegis.db.dao.SatScanDAO#updateSatscanJob(de.meningococcus.episcangis.db.model.SatScanJob)
   */
  public int updateSatScanJob(SatScanJob job)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(UPDATE_SATSCAN_JOB, new Object[] {
          job.getName(), job.getAnalysistype(), job.getModeltype(),
          job.getScanareas(), job.getMontecarloreps(),
          job.getTimeaggregationunits(), job.getTimeaggregationlength(),
          job.getJobtype(), job.getLastrun(), job.getId() });
    }
    catch (SQLException e)
    {
      log.error("SQL Query '" + UPDATE_SATSCAN_JOB + "' caused error: "
          + e.getMessage());
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.SatScanDAO#insertSatscanCluster(org.epidegis.db.model.SatScanCluster)
   */
  public int createSatScanCluster(SatScanCluster cluster)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(INSERT_SATSCAN_CLUSTER, new Object[] {
          cluster.getMostCentralLocation(), cluster.getNumber(),
          cluster.getJobId(), cluster.getCasetypeId(),
          cluster.getExpectedCases(), cluster.getCircleRadius(),
          cluster.getLLR(), cluster.getPValue(), cluster.getAnalysisDate(),
          cluster.getStartDate(), cluster.getEndDate() });
      if (insertCount == 1)
      {
        int insertedCases = 0;
        int insertId = getSatScanCluster(cluster).getId();
        log.debug("Cluster inserted with id: " + insertId);
        for (ReportedCase rc : cluster.getCases())
        {
          insertedCases += run.update(INSERT_SATSCAN_CLUSTER_CASES,
              new Object[] { insertId, rc.getId() });
        }
        if (insertedCases != cluster.getObservedCases())
        {
          log.fatal("Ohoh");
        }
      }
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

  /**
   * Finds the specified Cluster in the database. This function is needed to
   * @param cluster
   * @return
   */
  private SatScanCluster getSatScanCluster(SatScanCluster cluster)
  {
    SatScanCluster ret = null;
    try
    {
      ret = (SatScanCluster) run.query(GET_SATSCAN_CLUSTER_CLUSTER,
          new Object[] { cluster.getNumber(), cluster.getJobId(),
              cluster.getCasetypeId(), cluster.getAnalysisDate() },
          new BeanHandler(SatScanCluster.class));
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.SatScanDAO#getClusterAnalysisDates(org.epidegis.db.model.SatScanJob)
   */
  @SuppressWarnings("unchecked")
  public Collection<Date> getClusterAnalysisDates(SatScanJob job)
  {
    Vector<Date> dates = new Vector<Date>();
    try
    {
      List<Object[]> results = (List<Object[]>) run.query(
          GET_CLUSTER_ANALYSISDATES_JOBID, job.getId(), new ArrayListHandler());
      for (Object[] result : results)
      {
        dates.add((Date) result[0]);
      }
    }
    catch (SQLException e)
    {
      log.error(e);
    }
    return dates;
  }
}
