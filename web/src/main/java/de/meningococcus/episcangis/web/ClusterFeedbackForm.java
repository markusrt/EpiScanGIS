/**
 *
 */
package de.meningococcus.episcangis.web;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author reinhardt
 *
 */
public class ClusterFeedbackForm extends ValidatorForm
{
  private static final long serialVersionUID = 1L;

  private boolean workContactQuestion, schoolContactQuestion,
      relationshipQuestion, relationshipByMarriageQuestion,
      leisureContactQuestion, miscContactQuestion, massEventQuestion,
      discoPubQuestion, knownAcquaintanceCaseQuestion;

  private String workContact, schoolContact, relationship,
      relationshipByMarriage, leisureContact, miscContact, massEvent, discoPub,
      knownAcquaintanceCase, tan;

  private boolean infoByReport = false;

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
   * @return the discoPubQuestion
   */
  public boolean isDiscoPubQuestion()
  {
    return discoPubQuestion;
  }

  /**
   * @param discoPubQuestion the discoPubQuestion to set
   */
  public void setDiscoPubQuestion(boolean discoPubQuestion)
  {
    this.discoPubQuestion = discoPubQuestion;
  }

  /**
   * @return the knownAcquaintanceCaseQuestion
   */
  public boolean isKnownAcquaintanceCaseQuestion()
  {
    return knownAcquaintanceCaseQuestion;
  }

  /**
   * @param knownAcquaintanceCaseQuestion the knownAcquaintanceCaseQuestion to set
   */
  public void setKnownAcquaintanceCaseQuestion(
      boolean knownAcquaintanceCaseQuestion)
  {
    this.knownAcquaintanceCaseQuestion = knownAcquaintanceCaseQuestion;
  }

  /**
   * @return the leisureContactQuestion
   */
  public boolean isLeisureContactQuestion()
  {
    return leisureContactQuestion;
  }

  /**
   * @param leisureContactQuestion the leisureContactQuestion to set
   */
  public void setLeisureContactQuestion(boolean leisureContactQuestion)
  {
    this.leisureContactQuestion = leisureContactQuestion;
  }

  /**
   * @return the massEventQuestion
   */
  public boolean isMassEventQuestion()
  {
    return massEventQuestion;
  }

  /**
   * @param massEventQuestion the massEventQuestion to set
   */
  public void setMassEventQuestion(boolean massEventQuestion)
  {
    this.massEventQuestion = massEventQuestion;
  }

  /**
   * @return the miscContactQuestion
   */
  public boolean isMiscContactQuestion()
  {
    return miscContactQuestion;
  }

  /**
   * @param miscContactQuestion the miscContactQuestion to set
   */
  public void setMiscContactQuestion(boolean miscContactQuestion)
  {
    this.miscContactQuestion = miscContactQuestion;
  }

  /**
   * @return the relationshipByMarriageQuestion
   */
  public boolean isRelationshipByMarriageQuestion()
  {
    return relationshipByMarriageQuestion;
  }

  /**
   * @param relationshipByMarriageQuestion the relationshipByMarriageQuestion to set
   */
  public void setRelationshipByMarriageQuestion(
      boolean relationshipByMarriageQuestion)
  {
    this.relationshipByMarriageQuestion = relationshipByMarriageQuestion;
  }

  /**
   * @return the relationshipQuestion
   */
  public boolean isRelationshipQuestion()
  {
    return relationshipQuestion;
  }

  /**
   * @param relationshipQuestion the relationshipQuestion to set
   */
  public void setRelationshipQuestion(boolean relationshipQuestion)
  {
    this.relationshipQuestion = relationshipQuestion;
  }

  /**
   * @return the schoolContactQuestion
   */
  public boolean isSchoolContactQuestion()
  {
    return schoolContactQuestion;
  }

  /**
   * @param schoolContactQuestion the schoolContactQuestion to set
   */
  public void setSchoolContactQuestion(boolean schoolContactQuestion)
  {
    this.schoolContactQuestion = schoolContactQuestion;
  }

  /**
   * @return the workContactQuestion
   */
  public boolean isWorkContactQuestion()
  {
    return workContactQuestion;
  }

  /**
   * @param workContactQuestion the workContactQuestion to set
   */
  public void setWorkContactQuestion(boolean workContactQuestion)
  {
    this.workContactQuestion = workContactQuestion;
  }

  /**
   * @return the tan
   */
  public String getTan()
  {
    return tan;
  }

  /**
   * @param tan the tan to set
   */
  public void setTan(String tan)
  {
    this.tan = tan;
  }
}
