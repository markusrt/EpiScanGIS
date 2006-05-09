package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class CreateMapFormBean extends ActionForm
{

  private static final long serialVersionUID = 1L;

  private int width, height;

  /**
   * @return Returns the height.
   */
  public int getHeight()
  {
    return height;
  }

  /**
   * @param height
   *          The height to set.
   */
  public void setHeight(int height)
  {
    this.height = height;
  }

  /**
   * @return Returns the width.
   */
  public int getWidth()
  {
    return width;
  }

  /**
   * @param width
   *          The width to set.
   */
  public void setWidth(int width)
  {
    this.width = width;
  }

}
