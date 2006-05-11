package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.AreaDAO;
import de.meningococcus.episcangis.db.model.Area;
import de.meningococcus.episcangis.db.model.AreaType;

/**
 * Implementation of the DAO AreaDAO. This class uses Jakarta Commons Dbutils (<a
 * href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLAreaDAO extends DbUtilsDAO implements AreaDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLAreaDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String GET_AREA_ID = "SELECT area_id AS id, X(Centroid(the_geom)) AS centerLon, Y(Centroid(the_geom)) AS centerLat, "
      + "AsText(force_2d(Envelope(transform(awt.the_geom, srid)))) AS bboxaswkt, identifier, area_type_id AS areaTypeId "
      + "FROM areas_with_types AS awt WHERE area_id=?",
      GET_AREAS_TYPE = "SELECT area_id AS id, X(Centroid(the_geom)) AS centerLon, Y(Centroid(the_geom)) AS centerLat, "
          + "AsText(force_2d(Envelope(transform(awt.the_geom, srid)))) AS bboxaswkt, identifier, area_type_id AS areaTypeId  "
          + "FROM areas_with_types AS awt WHERE area_type_id=? ORDER BY identifier",
      GET_AREA_POPULATIONS_ID = "SELECT year, population FROM area_populations WHERE area_id=?";

  PgSQLAreaDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.AreaDAO#getArea(int)
   */
  @SuppressWarnings("unchecked")
  public Area getArea(int id)
  {
    Area ret = null;
    try
    {
      ret = (Area) run.query(GET_AREA_ID, id, new BeanHandler(Area.class));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
      e.printStackTrace();
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.AreaDAO#getAreaPopulations(int)
   */
  @SuppressWarnings("unchecked")
  public HashMap<String, Long> getAreaPopulations(int areaId)
  {
    HashMap<String, Long> ret = new HashMap<String, Long>();
    try
    {
      List<Object[]> resultRows = (List<Object[]>) run.query(
          GET_AREA_POPULATIONS_ID, areaId, new ArrayListHandler());
      for (Object[] row : resultRows)
      {
        ret.put(((Integer) row[0]).toString(), (Long) row[1]);
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
      e.printStackTrace();
    }
    return ret;
  }

  /*
   * (non-Javadoc)
   * @see org.epidegis.db.dao.AreaDAO#getAreas(de.meningococcus.episcangis.db.model.AreaType)
   */
  @SuppressWarnings("unchecked")
  public Collection<Area> getAreas(AreaType type)
  {
    List<Area> result = new ArrayList<Area>();;
    try
    {
      if (type != null)
      {
        result.addAll((List<Area>) run.query(GET_AREAS_TYPE, type.getId(),
            new BeanListHandler(Area.class)));
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
      e.printStackTrace();
    }
    return result;
  }
}