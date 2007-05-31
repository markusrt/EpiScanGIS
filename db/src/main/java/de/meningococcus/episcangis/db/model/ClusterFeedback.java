/**
 *
 */
package de.meningococcus.episcangis.db.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * @author reinhardt
 *
 */
public class ClusterFeedback
{
  private static final long serialVersionUID = 1L;

  private int id, caseId, clusterId;

  private String tan, workContact, schoolContact, relationship,
      relationshipByMarriage, leisureContact, miscContact, massEvent, discoPub,
      knownAcquaintanceCase;

  private Date expirationDate;
  private Timestamp lastChange;

  private boolean infoByReport = false;

  public boolean isExpired() {
    return expirationDate.before(GregorianCalendar.getInstance().getTime());
  }

  /**
   * @return the discoPub
   */
  public String getDiscoPub()
  {
    return discoPub;
  }

  /**
   * @param discoPub
   *          the discoPub to set
   */
  public void setDiscoPub(String discoPub)
  {
    this.discoPub = discoPub;
  }

  /**
   * @return the infoByReport
   */
  public boolean getInfoByReport()
  {
    return infoByReport;
  }

  /**
   * @param infoByReport
   *          the infoByReport to set
   */
  public void setInfoByReport(boolean infoByReport)
  {
    this.infoByReport = infoByReport;
  }

  /**
   * @return the knownAcquaintanceCase
   */
  public String getKnownAcquaintanceCase()
  {
    return knownAcquaintanceCase;
  }

  /**
   * @param knownAcquaintanceCase
   *          the knownAcquaintanceCase to set
   */
  public void setKnownAcquaintanceCase(String knownAcquaintanceCase)
  {
    this.knownAcquaintanceCase = knownAcquaintanceCase;
  }

  /**
   * @return the leisureContact
   */
  public String getLeisureContact()
  {
    return leisureContact;
  }

  /**
   * @param leisureContact
   *          the leisureContact to set
   */
  public void setLeisureContact(String leisureContact)
  {
    this.leisureContact = leisureContact;
  }

  /**
   * @return the massEvent
   */
  public String getMassEvent()
  {
    return massEvent;
  }

  /**
   * @param massEvent
   *          the massEvent to set
   */
  public void setMassEvent(String massEvent)
  {
    this.massEvent = massEvent;
  }

  /**
   * @return the miscContact
   */
  public String getMiscContact()
  {
    return miscContact;
  }

  /**
   * @param miscContact
   *          the miscContact to set
   */
  public void setMiscContact(String miscContact)
  {
    this.miscContact = miscContact;
  }

  /**
   * @return the relationship
   */
  public String getRelationship()
  {
    return relationship;
  }

  /**
   * @param relationship
   *          the relationship to set
   */
  public void setRelationship(String relationship)
  {
    this.relationship = relationship;
  }

  /**
   * @return the relationshipByMarriage
   */
  public String getRelationshipByMarriage()
  {
    return relationshipByMarriage;
  }

  /**
   * @param relationshipByMarriage
   *          the relationshipByMarriage to set
   */
  public void setRelationshipByMarriage(String relationshipByMarriage)
  {
    this.relationshipByMarriage = relationshipByMarriage;
  }

  /**
   * @return the schoolContact
   */
  public String getSchoolContact()
  {
    return schoolContact;
  }

  /**
   * @param schoolContact
   *          the schoolContact to set
   */
  public void setSchoolContact(String schoolContact)
  {
    this.schoolContact = schoolContact;
  }

  /**
   * @return the workContact
   */
  public String getWorkContact()
  {
    return workContact;
  }

  /**
   * @param workContact
   *          the workContact to set
   */
  public void setWorkContact(String workContact)
  {
    this.workContact = workContact;
  }

  /**
   * @return the id
   */
  public int getId()
  {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  private void setId(int id)
  {
    this.id = id;
  }

  /**
   * @return the tan
   */
  public String getTan()
  {
    return tan;
  }

  /**
   * @param tan
   *          the tan to set
   */
  public void setTan(String tan)
  {
    this.tan = tan;
  }

  /**
   * @return the caseId
   */
  public int getCaseId()
  {
    return caseId;
  }

  /**
   * @param caseId the caseId to set
   */
  public void setCaseId(int caseId)
  {
    this.caseId = caseId;
  }

  /**
   * @return the clusterId
   */
  public int getClusterId()
  {
    return clusterId;
  }

  /**
   * @param clusterId the clusterId to set
   */
  public void setClusterId(int clusterId)
  {
    this.clusterId = clusterId;
  }

  /**
   * @return the expirationDate
   */
  public Date getExpirationDate()
  {
    return expirationDate;
  }

  /**
   * @param expirationDate the expirationDate to set
   */
  public void setExpirationDate(Date expirationDate)
  {
    this.expirationDate = expirationDate;
  }

  /**
   * @return the lastChange
   */
  public Timestamp getLastChange()
  {
    return lastChange;
  }

  /**
   * @param lastChange the lastChange to set
   */
  private void setLastChange(Timestamp lastChange)
  {
    this.lastChange = lastChange;
  }
}
