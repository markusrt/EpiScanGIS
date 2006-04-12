package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
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

  private String serogroup, vr1, vr2, fetA;

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
      serogroup = parts[0];
      vr1 = parts[1];
      vr2 = parts[2];
      fetA = parts[3];
    }
    else
    {
      serogroup = "ER!";
      vr1 = "ER!";
      vr2 = "ER!";
      fetA = "ER!";
      log.error("Invalid NRZM finetype identifier: " + this.identifier);
    }
  }

  public boolean isSimilarTo(CaseType ct)
  {
    boolean similar = true;

    if (ct instanceof NRZMCaseType)
    {
      NRZMCaseType nct = (NRZMCaseType) ct;
      similar &= (serogroup.equals(nct.getSerogroup())
          || serogroup.length() == 0 || nct.getSerogroup().length() == 0);
      similar &= (vr1.equals(nct.getVr1()) || vr1.length() == 0 || nct.getVr1()
          .length() == 0);
      similar &= (vr2.equals(nct.getVr2()) || vr2.length() == 0 || nct.getVr2()
          .length() == 0);
      similar &= (fetA.equals(nct.getFetA()) || fetA.length() == 0 || nct
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
    return fetA;
  }

  public String getPorA()
  {
    StringBuilder porA = new StringBuilder();
    porA.append(vr1).append(",").append(vr2);
    return porA.toString();
  }

  public String getSerogroup()
  {
    return serogroup;
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
    return vr1;
  }

  public String getVr2()
  {
    return vr2;
  }
}
