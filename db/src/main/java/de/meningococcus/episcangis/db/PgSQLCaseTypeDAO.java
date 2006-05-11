package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.model.CaseType;
import de.meningococcus.episcangis.db.model.NRZMCaseType;

/**
 * Implementation of the DAO CaseTypeDAO. This class uses Jakarta Commons
 * Dbutils (<a href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * 
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLCaseTypeDAO extends DbUtilsDAO implements CaseTypeDAO
{

  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLCaseTypeDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String GET_CASE_TYPES_BY_COUNT = "SELECT case_type_id AS id, case_types.identifier, count(*) AS count "
      + "FROM cases NATURAL JOIN case_types "
      + "GROUP BY  case_type_id, case_types.identifier "
      + "ORDER BY count(*) DESC ",
      GET_CASE_TYPES_FROM_TO = "SELECT case_type_id AS id, case_types.identifier, count(*) AS count "
          + "FROM cases NATURAL JOIN case_types "
          + "WHERE ( reportDate BETWEEN ? AND ?) "
          + "GROUP BY  case_type_id, case_types.identifier ",
      GET_CASE_TYPE = "SELECT case_type_id AS id, case_types.identifier, count(*) AS count "
          + "FROM cases NATURAL JOIN case_types "
          + "WHERE case_type_id = ? "
          + "GROUP BY  case_type_id, case_types.identifier ";

  PgSQLCaseTypeDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.CaseTypeDAO#getCaseTypesByCount(int)
   */
  @SuppressWarnings("unchecked")
  public Collection<CaseType> getCaseTypesByCount(int limit)
  {
    List<CaseType> result = new ArrayList<CaseType>();
    try
    {
      ResultSetHandler beanHandler = new BeanListHandler(getCaseTypeClass());
      if (limit == -1)
      {
        result.addAll((List<CaseType>) run.query(GET_CASE_TYPES_BY_COUNT,
            beanHandler));
      }
      else
      {
        result.addAll((List<CaseType>) run.query(GET_CASE_TYPES_BY_COUNT
            + " LIMIT ?", new Integer(limit), beanHandler));
      }
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
   * @see de.meningococcus.episcangis.db.dao.CaseTypeDAO#getCaseTypes(java.sql.Date,
   *      java.sql.Date)
   */
  @SuppressWarnings("unchecked")
  public Collection<CaseType> getCaseTypes(Date from, Date to)
  {
    List<CaseType> result = new ArrayList<CaseType>();
    try
    {
      result.addAll((List<CaseType>) run.query(GET_CASE_TYPES_FROM_TO,
          new Object[] { from, to }, new BeanListHandler(getCaseTypeClass())));
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
   * @see de.meningococcus.episcangis.db.dao.CaseTypeDAO#getCaseTypeClass()
   */
  public Class getCaseTypeClass()
  {
    return NRZMCaseType.class;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.CaseTypeDAO#getCaseType(int)
   */
  public CaseType getCaseType(int id)
  {
    CaseType ret = null;
    try
    {
      ret = (CaseType) run.query(GET_CASE_TYPE, new Object[] { id },
          new BeanHandler(getCaseTypeClass()));
    }
    catch (SQLException e)
    {
      log.error("SQL Query  caused error: " + e.getMessage());
    }
    return ret;
  }
}
