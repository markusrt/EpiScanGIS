package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SetParameterFormBean extends ActionForm
{
  private String name = "", value = "";

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

}
