package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class CaseType
{
  private static final long serialVersionUID = 1L;

  protected String identifier;

  protected int count, id;

  public CaseType()
  {
  }

  public int getCount()
  {
    return count;
  }

  public void setCount(int count)
  {
    this.count = count;
  }

  public String getIdentifier()
  {
    return identifier;
  }

  public void setIdentifier(String identifier)
  {
    this.identifier = identifier;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int casetypeid)
  {
    this.id = casetypeid;
  }

  public boolean isComplete()
  {
    String[] parts = identifier.split(";", -1);
    boolean complete = true;
    for (int i = 0; i < parts.length; i++)
    {
      complete &= parts[i].length() > 0;
    }
    return complete;
  }

  public boolean isSimilarTo(CaseType ct)
  {
    return ct.getIdentifier().equals(identifier);
  }

  public String getFormattedIdentifier()
  {
    return identifier;
  }

}
