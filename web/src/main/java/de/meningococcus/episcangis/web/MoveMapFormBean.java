package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MoveMapFormBean extends ActionForm
{
  private static final long serialVersionUID = 1L;

  private double xoffset, yoffset;

  public double getXoffset()
  {
    return xoffset;
  }

  public void setXoffset(double xoffset)
  {
    this.xoffset = xoffset;
  }

  public double getYoffset()
  {
    return yoffset;
  }

  public void setYoffset(double yoffset)
  {
    this.yoffset = yoffset;
  }
}
