package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class GetFeatureInfoFormBean extends ActionForm
{
  private static final long serialVersionUID = 1L;

  private int x, y;

  public int getX()
  {
    return x;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public int getY()
  {
    return y;
  }

  public void setY(int y)
  {
    this.y = y;
  }
}
