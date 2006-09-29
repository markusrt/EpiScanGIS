package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.CaseType;
import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanCluster;

/**
 * Implementation of the DAO ReportedCaseDAO. This class uses Jakarta Commons
 * Dbutils (<a href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 *
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLReportedCaseDAO extends DbUtilsDAO implements ReportedCaseDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLReportedCaseDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static String SELECT_CASES = "SELECT case_id AS id, CAST( age AS int4),CAST(gender AS varchar),"
      + "incidencedate,reportdate, case_type_id AS caseTypeId, "
      + "last_change AS lastChange FROM cases ",
      GET_LAST_CASE = SELECT_CASES + "ORDER BY reportdate DESC LIMIT 1",
      GET_FIRST_CASE = SELECT_CASES + "ORDER BY reportdate ASC LIMIT 1",
      COUNT_CASES = "SELECT count(*) FROM cases",
      GET_CASES_AREA_FROM_TO = "SELECT awt.area_id AS areaid, awt.identifier, "
          + "cases.case_id AS id, CAST( age AS int4),CAST(gender AS varchar), "
          + "incidencedate,reportdate, X(Centroid(awt.the_geom)) AS areaLon, "
          + "Y(Centroid(awt.the_geom)) AS areaLat, population AS areaPopulation,"
          + "case_type_id AS caseTypeId, last_change AS lastChange "
          + "FROM contains_area_case, areas_with_types AS awt, cases "
          + "WHERE contains_area_case.area_id=awt.area_id AND "
          + "contains_area_case.case_id=cases.case_id "
          + "AND case_type_id = types_with_attributes.case_type_id "
          + "AND awt.tier=? AND reportDate BETWEEN ? AND ?",
      GET_CASES_AREA_FROM_TO_CASETYPE = GET_CASES_AREA_FROM_TO
          + " AND case_type_id = ?",
      LAST_CHANGE = "SELECT last_change FROM cases ORDER BY last_change DESC LIMIT 1",
      COUNT_CASES_PER_AREA_GROUPED_BY_ATTRIBUTE = "SELECT * FROM "
          + "episcangis_count_cases_per_area_attribute(?, ?) AS "
          + "(count bigint, Serogroup varchar, location varchar);",
      GET_CASES_CLUSTER = SELECT_CASES
          + " WHERE case_id IN (SELECT case_id FROM "
          + " satscan_cluster_cases WHERE satscan_cluster_id=?)";

  PgSQLReportedCaseDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.db.dao.ReportedCaseDAO#getFirstCase()
   */
  public ReportedCase getEarliestCase()
  {
    ReportedCase rc = null;
    try
    {
      rc = (ReportedCase) run.query(GET_FIRST_CASE, new BeanHandler(
          ReportedCase.class));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return rc;
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.db.dao.ReportedCaseDAO#getLastCase()
   */
  public ReportedCase getLatestCase()
  {
    ReportedCase rc = null;
    try
    {
      rc = (ReportedCase) run.query(GET_LAST_CASE, new BeanHandler(
          ReportedCase.class));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return rc;
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.db.dao.ReportedCaseDAO#countCases()
   */
  public long countCases()
  {
    Long count = new Long(0);
    try
    {
      count = (Long) run.query(COUNT_CASES, new ScalarHandler(1));
    }
    catch (SQLException e)
    {
      log.error("SQL Query  caused error: " + e.getMessage());
    }
    return count.longValue();
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.db.dao.ReportedCaseDAO#getCases(int, int,
   *      java.sql.Date, java.sql.Date)
   */
  @SuppressWarnings("unchecked")
  public Collection<ReportedCase> getCases(int areaTier, int caseTypeId,
      Date from, Date to)
  {
    List<ReportedCase> result = new ArrayList<ReportedCase>();
    try
    {
      result.addAll((List<ReportedCase>) run.query(
          GET_CASES_AREA_FROM_TO_CASETYPE, new Object[] { areaTier, from, to,
              caseTypeId }, new BeanListHandler(ReportedCase.class)));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.db.dao.ReportedCaseDAO#getCases(int,
   *      java.sql.Date, java.sql.Date)
   */
  @SuppressWarnings("unchecked")
  public Collection<ReportedCase> getCases(int areaTier, Date from, Date to)
  {
    List<ReportedCase> result = new ArrayList<ReportedCase>();
    try
    {
      result.addAll((List<ReportedCase>) run.query(GET_CASES_AREA_FROM_TO,
          new Object[] { areaTier, from, to }, new BeanListHandler(
              ReportedCase.class)));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public Collection<ReportedCase> getCasesInCluster(SatScanCluster cluster)
  {
    List<ReportedCase> result = new ArrayList<ReportedCase>();
    try
    {
      result.addAll((List<ReportedCase>) run.query(GET_CASES_CLUSTER,
          new Object[] { cluster.getId() }, new BeanListHandler(
              ReportedCase.class)));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  public Timestamp getLastChange()
  {
    Timestamp lastChange = null;
    try
    {
      lastChange = (Timestamp) run.query(LAST_CHANGE, new ScalarHandler(1));
    }
    catch (SQLException e)
    {
      log.error("SQL Query  caused error: " + e.getMessage());
    }
    return lastChange;
  }

  @SuppressWarnings("unchecked")
  public Collection<Object[]> countCasesPerAreaGroupedByAttribute(
      String attribute, int areaTier)
  {
    List<Object[]> result = new ArrayList<Object[]>();
    try
    {
      result.addAll((List<Object[]>) run.query(
          COUNT_CASES_PER_AREA_GROUPED_BY_ATTRIBUTE, new Object[] { attribute,
              (short) areaTier }, new ArrayListHandler()));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }
}
