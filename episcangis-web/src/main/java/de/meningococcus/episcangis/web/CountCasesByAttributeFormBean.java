package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class CountCasesByAttributeFormBean extends ActionForm
{
  private static final long serialVersionUID = 1L;

  private int areaTier;

  private String attribute;

  public int getAreaTier()
  {
    return areaTier;
  }

  public void setAreaTier(int areaTier)
  {
    this.areaTier = areaTier;
  }

  public String getAttribute()
  {
    return attribute;
  }

  public void setAttribute(String attribute)
  {
    this.attribute = attribute;
  }
}
