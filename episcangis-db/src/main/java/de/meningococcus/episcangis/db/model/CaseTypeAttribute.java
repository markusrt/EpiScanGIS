package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class CaseTypeAttribute
{
  private static final long serialVersionUID = 1L;

  private String value;

  private int casetypeid;

  public int getCasetypeId()
  {
    return casetypeid;
  }

  public void setCasetypeId(int casetypeid)
  {
    this.casetypeid = casetypeid;
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
