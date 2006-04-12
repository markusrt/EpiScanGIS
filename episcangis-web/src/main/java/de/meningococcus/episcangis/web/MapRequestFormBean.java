package de.meningococcus.episcangis.web;

import org.apache.struts.action.ActionForm;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MapRequestFormBean extends ActionForm
{
  private static final long serialVersionUID = 1L;

  private int fromYear, toYear, fromMonth, toMonth, width, height;

  private String serogroup;

  public String getSerogroup()
  {
    return serogroup;
  }

  public void setSerogroup(String serogroup)
  {
    serogroup = serogroup.replace(',', '|');
    serogroup = "(" + serogroup + ")";
    this.serogroup = serogroup;
  }

  public int getFromMonth()
  {
    return fromMonth;
  }

  public int getFromYear()
  {
    return fromYear;
  }

  public int getHeight()
  {
    return height;
  }

  public void setHeight(int height)
  {
    this.height = height;
  }

  public int getToMonth()
  {
    return toMonth;
  }

  public int getToYear()
  {
    return toYear;
  }

  public int getWidth()
  {
    return width;
  }

  public void setWidth(int with)
  {
    this.width = with;
  }

  public void setFromMonth(int fromMonth)
  {
    this.fromMonth = fromMonth;
  }

  public void setFromYear(int fromYear)
  {
    this.fromYear = fromYear;
  }

  public void setToMonth(int toMonth)
  {
    this.toMonth = toMonth;
  }

  public void setToYear(int toYear)
  {
    this.toYear = toYear;
  }
}
