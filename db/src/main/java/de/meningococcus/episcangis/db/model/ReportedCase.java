package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class ReportedCase
{

  public static final String GENDER_MALE = "m", GENDER_FEMALE = "f",
      GENDER_UNKNOWN = "?";

  private static final long serialVersionUID = 1L;

  private int age, areaId, id;

  private long areaPopulation;

  private double areaLat, areaLon;

  private String gender;

  private Date incidenceDate, reportDate;
  
  private Timestamp lastChange;

  private int caseTypeId;

  /**
   * @return Returns the age.
   */
  public int getAge()
  {
    return age;
  }

  /**
   * @param age The age to set.
   */
  public void setAge(int age)
  {
    this.age = age;
  }

  /**
   * @return Returns the agent_type.
   */
  public int getCaseTypeId()
  {
    return caseTypeId;
  }

  /**
   * @param agent_type The agent_type to set.
   */
  public void setCaseTypeId(int caseTypeId)
  {
    this.caseTypeId = caseTypeId;
  }

  /**
   * @return Returns the gender.
   */
  public String getGender()
  {
    return gender;
  }

  /**
   * @param gender The gender to set.
   */
  public void setGender(String gender)
  {
    this.gender = gender;
  }

  /**
   * @return Returns the incidenceDate.
   */
  public Date getIncidenceDate()
  {
    return incidenceDate;
  }

  /**
   * @param incidenceDate The incidenceDate to set.
   */
  public void setIncidenceDate(Date incidenceDate)
  {
    this.incidenceDate = incidenceDate;
  }

  /**
   * @return Returns the reportDate.
   */
  public Date getReportDate()
  {
    return reportDate;
  }

  /**
   * @param reportDate The reportDate to set.
   */
  public void setReportDate(Date reportDate)
  {
    this.reportDate = reportDate;
  }

  public int getAreaId()
  {
    return areaId;
  }

  public void setAreaId(int areaId)
  {
    this.areaId = areaId;
  }

  public double getAreaLat()
  {
    return areaLat;
  }

  public void setAreaLat(double areaLat)
  {
    this.areaLat = areaLat;
  }

  public double getAreaLon()
  {
    return areaLon;
  }

  public void setAreaLon(double areaLon)
  {
    this.areaLon = areaLon;
  }

  public long getAreaPopulation()
  {
    return areaPopulation;
  }

  public void setAreaPopulation(long areaPopulation)
  {
    this.areaPopulation = areaPopulation;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  /**
   * @return Returns the lastChange.
   */
  public Timestamp getLastChange()
  {
    return lastChange;
  }

  /**
   * @param lastChange The lastChange to set.
   */
  public void setLastChange(Timestamp lastChange)
  {
    this.lastChange = lastChange;
  }
}
