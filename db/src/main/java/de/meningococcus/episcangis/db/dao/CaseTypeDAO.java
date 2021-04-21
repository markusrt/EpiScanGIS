package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Collection;

import de.meningococcus.episcangis.db.model.CaseType;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface CaseTypeDAO
{

  /**
   * @return Class that is used to represent a case type
   */
  public Class getCaseTypeClass();

  /**
   * @param limit maximal number of case types to return
   * @return Collection of all case types, never null
   */
  public Collection<CaseType> getCaseTypesByCount(int limit);

  /**
   * Searches for any case type which was reported between from and to date.
   * @param from date of first reported case type to search
   * @param to date of last reported case type to search
   * @return Collection of all case types reported between from and to, never
   *         null
   */
  public Collection<CaseType> getCaseTypes(Date from, Date to);

  /**
   * @param id ID of case type to search
   * @return CaseType-object or null if not found
   */
  public CaseType getCaseType(int id);

}
