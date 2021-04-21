package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class NRZMCaseType extends CaseType
{
  private static Log log = LogFactory.getLog(NRZMCaseType.class);

  private static final long serialVersionUID = 1L;

  private StringBuffer serogroup = new StringBuffer(1);
  private StringBuffer vr1 = new StringBuffer(4);
  private StringBuffer vr2 = new StringBuffer(4);
  private StringBuffer fetA = new StringBuffer(4);

  public NRZMCaseType()
  {
  }

  public NRZMCaseType(String identifier)
  {
    setIdentifier(identifier);
  }

  public void setIdentifier(String identifier)
  {
    super.setIdentifier(identifier);
    String[] parts = this.identifier.split(";", -1);
    if (parts.length == 4)
    {
      serogroup.append(parts[0]);
      vr1.append(parts[1]);
      vr2.append(parts[2]);
      fetA.append(parts[3]);
    }
    else
    {
      serogroup.append("ER!");
      vr1.append("ER!");
      vr2.append("ER!");
      fetA.append("ER!");
      log.error("Invalid NRZM finetype identifier: " + this.identifier);
    }
  }

  public boolean isSimilarTo(CaseType ct)
  {
    boolean similar = true;

    if (ct instanceof NRZMCaseType)
    {
      NRZMCaseType nct = (NRZMCaseType) ct;
      similar &= (getSerogroup().equals(nct.getSerogroup())
          || getSerogroup().length() == 0 || nct.getSerogroup().length() == 0);
      similar &= (getVr1().equals(nct.getVr1()) || getVr1().length() == 0 || nct.getVr1()
          .length() == 0);
      similar &= (getVr2().equals(nct.getVr2()) || getVr2().length() == 0 || nct.getVr2()
          .length() == 0);
      similar &= (getFetA().equals(nct.getFetA()) || getFetA().length() == 0 || nct
          .getFetA().length() == 0);
    }
    else
    {
      similar = super.isSimilarTo(ct);
    }
    return similar;
  }

  public String getFetA()
  {
    return fetA.toString();
  }

  public String getPorA()
  {
    StringBuilder porA = new StringBuilder();
    porA.append(vr1).append(",").append(vr2);
    return porA.toString();
  }

  public String getSerogroup()
  {
    return serogroup.toString();
  }

  public String getFormattedIdentifier()
  {
    StringBuilder formattedIdentifier = new StringBuilder(30);
    formattedIdentifier.append(serogroup).append(" : P1.").append(vr1).append(
        ",").append(vr2).append(" : F").append(fetA);
    return formattedIdentifier.toString();
  }

  public String getVr1()
  {
    return vr1.toString();
  }

  public String getVr2()
  {
    return vr2.toString();
  }
}
