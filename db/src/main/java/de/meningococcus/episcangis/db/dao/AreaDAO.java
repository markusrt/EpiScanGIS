package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt
 *   All Rights Reserved.
 * ====================================================================
 */

import java.util.Collection;
import java.util.HashMap;

import de.meningococcus.episcangis.db.model.Area;
import de.meningococcus.episcangis.db.model.AreaType;

/**
 * 
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface AreaDAO
{
  /**
   * @param areaId ID of area to search
   * @return Area object or null if not found
   */
  public Area getArea(int areaId);

  /**
   * @param type type of areas to search
   * @return Collection of areas, never null
   */
  public Collection<Area> getAreas(AreaType type);

  /**
   * @param areaId ID of area to get population from
   * @return Hashmap of population count keyed by year
   */
  public HashMap<String, Long> getAreaPopulations(int areaId);
}
