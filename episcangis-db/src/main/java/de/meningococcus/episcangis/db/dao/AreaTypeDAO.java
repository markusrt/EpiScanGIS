package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Collection;

import de.meningococcus.episcangis.db.model.AreaType;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface AreaTypeDAO
{
  /**
   * @return Collection of all areatypes, never null
   */
  public Collection<AreaType> getAreaTypes();

  /**
   * @param parentId ID of parent area type
   * @return Collection of all child areatypes, never null
   */
  public Collection<AreaType> getChildAreaTypes(int parentId);

  /**
   * @param areaTypeId ID of areatype to search
   * @return AreaType-object or null if not found
   */
  public AreaType getAreaType(int areaTypeId);
}
