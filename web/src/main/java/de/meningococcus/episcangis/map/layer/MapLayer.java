package de.meningococcus.episcangis.map.layer;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterComposite;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * This is ht
 *
 * @author Markus Reinhardt
 */
public class MapLayer
{
  private static Log log = LogFactory.getLog(MapLayer.class);

  private AbstractWmsMap map;

  private String title, name, description = "";

  private boolean active, hasLegend, queryable, opaque;

  private ParameterComponent parameters;

  private boolean isValid;

  public MapLayer(String name, String title, boolean hasLegend, AbstractWmsMap map)
  {
    isValid = false;
    this.map = map;
    this.name = name;
    this.title = title;
    this.hasLegend = hasLegend;
    parameters = new ParameterComposite("layerparameters_" + this.name);

    String resourceName = this.name;
    registerReference("areaId");

    if (!this.name.endsWith("_disabled"))
    {
      if (this.name.endsWith("_default"))
      {
        active = true;
        resourceName = this.name.substring(0, this.name.indexOf("_default"));
      }
      if (title != null && title.length() == 0)
      {
        this.title = this.name;
      }
      if (name.length() == 0)
      {
        log.warn("Failed to create layer: no name defined");
      }
      else
      {
        this.isValid = true;
      }
    }
    try
    {
      ResourceBundle messages;
      messages = ResourceBundle.getBundle("MessageResources");
      String i18nTitle = messages.getString("map.layer." + resourceName);
      if (i18nTitle != null)
      {
        setTitle(i18nTitle);
      }
      String description = messages.getString("map.layer." + resourceName
          + ".description");
      if (description != null)
      {
        setDescription(description);
      }
    }
    catch (MissingResourceException e)
    {
      log
          .warn("Layer '"
              + resourceName
              + "': Some resources needed for i18n of map layer description/title are missing:");
      log.error(e.getMessage());
    }
  }

  protected void registerReference(String refId ) {
    this.addParameter(map.getParameterReference(refId));
  }

  public boolean dependsOnParameter(String name)
  {
    return parameters.get(name) != null;
  }

  public boolean hasLegend()
  {
    return hasLegend;
  }

  public String getName()
  {
    return name;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean isdefault)
  {
    this.active = isdefault;
  }

  public boolean isValid()
  {
    return isValid;
  }

  public String toString()
  {
    String nl = System.getProperty("line.separator");
    return "Maplayer: " + title + nl + "name: " + name + nl + "Type: "
        + this.getClass().getName() + nl;
  }

  public ParameterComponent getParameters()
  {
    return parameters;
  }

  public ParameterComponent getParameter(String name)
  {
    return parameters.get(name);
  }

  protected void addParameter(ParameterComponent p)
  {
    parameters.add(p);
  }

  public boolean isQueryable()
  {
    return queryable;
  }

  public void setQueryable(boolean queryable)
  {
    this.queryable = queryable;
  }

  public String getDescription()
  {
    return description;
  }

  protected void setDescription(String description)
  {
    this.description = description;
  }

  public String getTitle()
  {
    return title;
  }

  protected void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * @return Returns the opaque.
   */
  public boolean isOpaque()
  {
    return opaque;
  }

  /**
   * @param opaque
   *          The opaque to set.
   */
  public void setOpaque(boolean opaque)
  {
    this.opaque = opaque;
  }


}
