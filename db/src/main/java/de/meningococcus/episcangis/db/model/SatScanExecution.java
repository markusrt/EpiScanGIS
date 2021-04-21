package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class SatScanExecution
{
  private SatScanJob job;

  private int caseTypeId;

  private Date plannedExecution;

  public SatScanExecution(int caseTypeId, SatScanJob job, Date plannedexecution)
  {
    this.caseTypeId = caseTypeId;
    this.job = job;
    this.plannedExecution = plannedexecution;
  }

  public int getCaseTypeId()
  {
    return caseTypeId;
  }

  public SatScanJob getJob()
  {
    return job;
  }

  public Date getPlannedExecution()
  {
    return plannedExecution;
  }

  public void setCaseTypeId(int caseTypeId)
  {
    this.caseTypeId = caseTypeId;
  }

  public void setJob(SatScanJob job)
  {
    this.job = job;
  }

  public void setPlannedExecution(Date plannedExecution)
  {
    this.plannedExecution = plannedExecution;
  }

  public Date getObservationBegin()
  {
    return job.getObservationBegin(plannedExecution);
  }

  public Date getObservationEnd()
  {
    return job.getObservationEnd(plannedExecution);
  }
}
