package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Collection;

import de.meningococcus.episcangis.db.model.CaseTypeAttribute;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface CaseTypeAttributeDAO
{
  /**
   * @param group name of attribute group
   * @return Collection of all attributes in this group, never null
   */
  public Collection<CaseTypeAttribute> selectDistinctCaseTypeAttributes(
      String group);

  /**
   * @param group name of attribute group
   * @return Collection of all attributes in this group, ordered by count, never
   *         null
   */
  public Collection<CaseTypeAttribute> selectDistinctCaseTypeAttributesByCount(
      String group);
}
