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

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.model.AreaType;

/**
 * Implementation of the DAO AreaTypeDAO. This class uses Jakarta Commons
 * Dbutils (<a href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLAreaTypeDAO extends DbUtilsDAO implements AreaTypeDAO
{

  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLAreaTypeDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String GET_AREA_TYPES = "SELECT area_type_id AS id, * FROM area_types ORDER BY tier",
      GET_CHILD_AREA_TYPES_PARENTID = "SELECT area_type_id AS id, * FROM area_types "
          + " WHERE parent=? ORDER BY description",
      GET_AREA_TYPE_ID = "SELECT area_type_id AS id, * FROM area_types "
          + " WHERE area_type_id=? ORDER BY tier";

  /**
   * @param dataSource
   */
  PgSQLAreaTypeDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.AreaTypeDAO#getAreaTypes()
   */
  @SuppressWarnings("unchecked")
  public Collection<AreaType> getAreaTypes()
  {
    List<AreaType> result = new ArrayList<AreaType>();
    try
    {
      result.addAll((List<AreaType>) run.query(GET_AREA_TYPES, new BeanListHandler(
          AreaType.class)));
    }
    catch (SQLException e)
    {
      log.error(e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.AreaTypeDAO#getAreaType(int)
   */
  public AreaType getAreaType(int areaTypeId)
  {
    AreaType result = null;
    try
    {
      result = (AreaType) run.query(GET_AREA_TYPE_ID, areaTypeId,
          new BeanHandler(AreaType.class));
    }
    catch (SQLException e)
    {
      log.error(e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.AreaTypeDAO#getAreaTypes()
   */
  @SuppressWarnings("unchecked")
  public Collection<AreaType> getChildAreaTypes(int parentId)
  {
    List<AreaType> result = new ArrayList<AreaType>();;
    try
    {
      result.addAll((List<AreaType>) run.query(GET_CHILD_AREA_TYPES_PARENTID,
          parentId, new BeanListHandler(AreaType.class)));
    }
    catch (SQLException e)
    {
      log.error(e.getMessage());
    }
    return result;
  }
}
