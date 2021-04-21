package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class AreaType
{
  private static final long serialVersionUID = 1L;

  private String description, country;

  private int id, tier, parent;

  private boolean active, group;

  public AreaType()
  {
  }

  public String getCountry()
  {
    return country;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getParent()
  {
    return parent;
  }

  public void setParent(int parent)
  {
    this.parent = parent;
  }

  public int getTier()
  {
    return tier;
  }

  public void setTier(int tier)
  {
    this.tier = tier;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public boolean isGroup()
  {
    return group;
  }

  public void setGroup(boolean group)
  {
    this.group = group;
  }
}
