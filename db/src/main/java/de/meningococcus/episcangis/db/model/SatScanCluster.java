package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Vector;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class SatScanCluster
{
  private int id, mostCentralLocation, number, caseTypeId,
      jobId;

  private long observedCases;

  private float circleRadius, expectedCases;

  private double llr, pValue;

  private Date startDate, endDate, analysisDate;

  private Vector<ReportedCase> cases = new Vector<ReportedCase>();
  private CaseType caseType;

  private SatScanExecution execution;

  public SatScanCluster()
  {
  }

  public SatScanCluster(SatScanExecution execution)
  {
    this.execution = execution;
    setCasetypeId(execution.getCaseTypeId());
    setJobId(execution.getJob().getId());
    setAnalysisDate(execution.getPlannedExecution());
  }

  public void addCase(ReportedCase c)
  {
    cases.add(c);
  }

  public boolean containsDate(Date date)
  {
    return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
  }

  public Vector<ReportedCase> getCases()
  {
    return cases;
  }

  public float getCircleRadius()
  {
    return circleRadius;
  }

  public void setCircleRadius(float circleRadius)
  {
    this.circleRadius = circleRadius;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public void setEndDate(Date clusterEndDate)
  {
    this.endDate = clusterEndDate;
  }

  public int getNumber()
  {
    return number;
  }

  public void setNumber(int number)
  {
    this.number = number;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public void setStartDate(Date clusterStartDate)
  {
    this.startDate = clusterStartDate;
  }

  public float getExpectedCases()
  {
    return expectedCases;
  }

  public void setExpectedCases(float expectedCases)
  {
    this.expectedCases = expectedCases;
  }

  public double getLLR()
  {
    return llr;
  }

  public void setLLR(double logLokelihoodRatio)
  {
    this.llr = logLokelihoodRatio;
  }

  public int getMostCentralLocation()
  {
    return mostCentralLocation;
  }

  public void setMostCentralLocation(int mostCentralLocation)
  {
    this.mostCentralLocation = mostCentralLocation;
  }

  public long getObservedCases()
  {
    return observedCases;
  }

  public void setObservedCases(long observedCases)
  {
    this.observedCases = observedCases;
  }

  public double getPValue()
  {
    return pValue;
  }

  public void setPValue(double value)
  {
    pValue = value;
  }

  public boolean isValid()
  {
    boolean valid = true;
    valid &= startDate.compareTo(endDate) <= 0;
    valid &= (observedCases != 0 && observedCases == cases.size());
    valid &= execution != null;
    return valid;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getCasetypeId()
  {
    return caseTypeId;
  }

  public void setCasetypeId(int caseTypeId)
  {
    this.caseTypeId = caseTypeId;
  }

  public int getJobId()
  {
    return jobId;
  }

  public void setJobId(int jobId)
  {
    this.jobId = jobId;
  }

  public Date getAnalysisDate()
  {
    return analysisDate;
  }

  public void setAnalysisDate(Date analysisDate)
  {
    this.analysisDate = analysisDate;
  }

  /**
   * @return the caseType
   */
  public CaseType getCaseType()
  {
    return caseType;
  }

  /**
   * @param caseType the caseType to set
   */
  public void setCaseType(CaseType caseType)
  {
    this.caseType = caseType;
  }
}
