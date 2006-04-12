package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import de.meningococcus.episcangis.db.model.ReportedCase;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface ReportedCaseDAO
{
  /**
   * @param areaTier depth of AreaTypes in type hierarchie to match this case on
   * @param caseTypeId return cases by CaseType
   * @param from earliest reportDate  
   * @param to latest reportDate
   * @return Collection of matching cases, never null
   */
  public Collection<ReportedCase> getCases(int areaTier, int caseTypeId,
      Date from, Date to);

  /**
   * @param areaTier depth of AreaTypes in type hierarchie to match this case on
   * @param from earliest reportDate
   * @param to latest reportDate
   * @return Collection of matching cases, never null
   */
  public Collection<ReportedCase> getCases(int areaTier, Date from, Date to);

  /**
   * @return earliest case in datastore by reportdate
   */
  public ReportedCase getEarliestCase();

  /**
   * @return latest case in datastore by reportdate
   */
  public ReportedCase getLatestCase();

  /**
   * @return number of cases in datastore
   */
  public long countCases();
  
  /**
   * @return last change in case database
   */
  public Timestamp lastChange();
  
}
