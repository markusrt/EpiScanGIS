package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanCluster;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface ReportedCaseDAO
{
  /**
   * @param areaTier
   *          depth of AreaTypes in type hierarchie to match this case on
   * @param caseTypeId
   *          return cases by CaseType
   * @param from
   *          earliest reportDate
   * @param to
   *          latest reportDate
   * @return Collection of matching cases, never null
   */
  public Collection<ReportedCase> getCases(int areaTier, int caseTypeId,
      Date from, Date to);

  /**
   * @param areaTier
   *          depth of AreaTypes in type hierarchie to match this case on
   * @param from
   *          earliest reportDate
   * @param to
   *          latest reportDate
   * @return Collection of matching cases, never null
   */
  public Collection<ReportedCase> getCases(int areaTier, Date from, Date to);
  
  /**
   * @param cluster 
   * @return
   */
  public Collection<ReportedCase> getCasesInCluster(SatScanCluster cluster);

  /**
   * Executes a crosstable query to count the number of cases, that occured at
   * each area of specified tier. The cases are grouped by the specified
   * Attribute.
   * 
   * @param attribute
   *          Attribute by which the cases should be grouped
   * @param areaTier
   *          depth of AreaTypes in type hierarchie to match this case on
   * @return Returns a Collection of Object arrays with each row containing
   *         (count|attributevalue|area-identifier) ordered by
   *         area-identifier,attributevalue.
   */
  public Collection<Object[]> countCasesPerAreaGroupedByAttribute(
      String attribute, int areaTier);

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
  public Timestamp getLastChange();

}
