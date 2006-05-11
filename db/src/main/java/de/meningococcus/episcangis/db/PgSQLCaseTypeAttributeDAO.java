package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO;
import de.meningococcus.episcangis.db.model.CaseTypeAttribute;

/**
 * Implementation of the DAO CaseTypeAttributeDAO. This class uses Jakarta
 * Commons Dbutils (<a href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLCaseTypeAttributeDAO extends DbUtilsDAO implements
    CaseTypeAttributeDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLCaseTypeAttributeDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String SELECT_DISTINCT_CASETYPEATTRIBUTES = "SELECT "
      + "value FROM case_type_attributes NATURAL JOIN attributes "
      + "WHERE attributes.group LIKE ? GROUP BY value",
      SELECT_DISTINCT_CASETYPEATTRIBUTES_BY_COUNT = "SELECT value, "
          + "count(*) AS count FROM case_type_attributes "
          + "NATURAL JOIN attributes "
          + "WHERE attributes.group LIKE ? GROUP BY value ORDER BY count DESC";

  PgSQLCaseTypeAttributeDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO#selectDistinctCaseTypeAttributes(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  public Collection<CaseTypeAttribute> selectDistinctCaseTypeAttributes(
      String group)
  {
    List<CaseTypeAttribute> result = new ArrayList<CaseTypeAttribute>();
    try
    {
      result.addAll((List<CaseTypeAttribute>) run.query(
          SELECT_DISTINCT_CASETYPEATTRIBUTES, group, new BeanListHandler(
              CaseTypeAttribute.class)));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO#selectDistinctCaseTypeAttributesByCount(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  public Collection<CaseTypeAttribute> selectDistinctCaseTypeAttributesByCount(
      String group)
  {
    List<CaseTypeAttribute> result = new ArrayList<CaseTypeAttribute>();
    try
    {
      result.addAll((List<CaseTypeAttribute>) run.query(
          SELECT_DISTINCT_CASETYPEATTRIBUTES_BY_COUNT, group,
          new BeanListHandler(CaseTypeAttribute.class)));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }
}
