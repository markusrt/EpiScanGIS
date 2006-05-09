package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Collection;

import de.meningococcus.episcangis.db.model.SatScanCluster;
import de.meningococcus.episcangis.db.model.SatScanExecution;
import de.meningococcus.episcangis.db.model.SatScanJob;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface SatScanDAO
{
  /**
   * @return collection of all SatScanJob definitions in datastore, never null
   */
  public Collection<SatScanJob> getSatScanJobs();

  /**
   * @param id ID of job to search
   * @return SatScanJob object or null if not found
   */
  public SatScanJob getSatScanJob(int id);

  /**
   * @param job SatScanJob to insert in datastore
   * @return number of inserted jobs
   */
  public int createSatScanJob(SatScanJob job);

  /**
   * @param job SatScanJob to update in datastore
   * @return number of updated jobs
   */
  public int updateSatScanJob(SatScanJob job);

  /**
   * @return earliest SatScanExecution in datastore by planned execution time
   */
  public SatScanExecution getEarliestSatScanExecution();

  /**
   * @param execution SatScanExecution to remove from datastore 
   * @return number of deleted executions
   */
  public int deleteSatScanExecution(SatScanExecution execution);

  /**
   * @param execution SatScanExecution to insert in datastore
   * @return  number of inserted executions
   */
  public int createSatScanExecution(SatScanExecution execution);

  /**
   * @param SatScanCluster to insert in datastore
   * @return  number of inserted clusters
   */
  public int createSatScanCluster(SatScanCluster cluster);

  /**
   * @param job SatScanJob for which to search analysis dates 
   * @return collection of all matching Dates in datastore, never null
   */
  public Collection<Date> getClusterAnalysisDates(SatScanJob job);
}
