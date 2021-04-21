package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class LayerFormBean extends ActionForm
{

  private static final long serialVersionUID = 1L;

  private String layer = "";

  private boolean active = false;

  public String getLayer()
  {
    return layer;
  }

  public void setLayer(String layer)
  {
    this.layer = layer;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

}
