package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An Area in EpiScanGIS represents any kind of polygon-shaped region. This may
 * be a country, a county, a building and so forth. Different areas are
 * distinguished from each other by their AreaType. The hierarchical information
 * about an area is stored in its type (e. g. a country contain counties).
 * @see de.meningococcus.episcangis.db.model.AreaType
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class Area
{
  /**
   * This classes logger
   */
  Log log = LogFactory.getLog(Area.class);

  private static final long serialVersionUID = 1L;

  private String identifier, country, bboxAsWKT;

  private int id, areaTypeId;

  private double centerLat, centerLon;

  /**
   * This areas bounding box
   */
  private BoundingBox bbox = new BoundingBox();

  public Area()
  {
  }

  public double getCenterLat()
  {
    return centerLat;
  }

  public void setCenterLat(double centerLat)
  {
    this.centerLat = centerLat;
  }

  public double getCenterLon()
  {
    return centerLon;
  }

  public void setCenterLon(double centerLon)
  {
    this.centerLon = centerLon;
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getIdentifier()
  {
    return identifier;
  }

  public void setIdentifier(String identifier)
  {
    this.identifier = identifier;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public BoundingBox getBoundingBox()
  {
    return bbox;
  }

  public String getBboxAsWKT()
  {
    return bboxAsWKT;
  }

  /**
   * Sets this areas bounding box using an OGC well known text representation.
   * On any error the getBbox().isValid() will return false.
   * @param bboxAsText String having form POLYGON((minx miny,minx maxy,maxx
   *          maxy,maxx miny,minx miny))
   */
  public void setBboxAsWKT(String bboxAsWKT)
  {
    int NO_COORD_VALUES = 10;
    this.bboxAsWKT = bboxAsWKT;

    String[] coords = bboxAsWKT.substring(bboxAsWKT.indexOf("((") + 2,
        bboxAsWKT.indexOf("))")).split("[ ,]");
    try
    {
      if (coords.length == NO_COORD_VALUES)
      {
        bbox.setMinx(Double.parseDouble(coords[0]));
        bbox.setMiny(Double.parseDouble(coords[1]));
        bbox.setMaxx(Double.parseDouble(coords[4]));
        bbox.setMaxy(Double.parseDouble(coords[3]));
      }
      else
      {
        log.error("setBboxAsWKT: String boxAsWKT has " + coords.length
            + " coordinate values. Excpected " + NO_COORD_VALUES);
      }
    }
    catch (NumberFormatException nfe)
    {
      log.error("setBboxAsWKT: String boxAsWKT includes unparsable doubles: "
          + nfe.getMessage());
    }
    if (!bbox.isValid())
    {
      log.error("setBboxAsWKT: String boxAsWKT was parsed successfully, "
          + "but bbox is invalid.");
    }
  }

  public void setAreaTypeId(int areaTypeId) throws SQLException
  {
    this.areaTypeId = areaTypeId;
  }

  public int getAreaTypeId()
  {
    return areaTypeId;
  }
}
