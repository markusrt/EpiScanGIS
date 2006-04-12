package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * This class represents an abstract parameter.  
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public abstract class AbstractParameter
{
  private String name, title;

  protected AbstractParameter(String name, String title)
  {
    this(name);
    this.title = title;
  }

  protected AbstractParameter(String name)
  {
    this.name = name;
  }

  public AbstractParameter getNamedParameter(String name)
  {
    if (name.equals(this.name))
    {
      return this;
    }
    else
    {
      return null;
    }
  }

  public String getTitle()
  {
    return title;
  }

  public String getName()
  {
    return name;
  }

}
