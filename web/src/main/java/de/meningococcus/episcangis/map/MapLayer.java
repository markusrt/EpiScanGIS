package de.meningococcus.episcangis.map;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * This is ht
 * @author Markus Reinhardt
 */
public class MapLayer
{
  private static Log log = LogFactory.getLog(MapLayer.class);

  private AbstractWmsMap map;

  private String title, name, description = "";

  private boolean active, hasLegend, queryable, opaque;

  private Map<String, AbstractParameter> parameters = new HashMap<String, AbstractParameter>();

  private boolean isValid;

  MapLayer(String name, String title, boolean hasLegend, AbstractWmsMap map)
  {
    isValid = false;
    this.map = map;
    this.name = name;
    this.title = title;
    this.hasLegend = hasLegend;
    this.addParameter(new ReferenceParameter("areaId"));
    
    String resourceName = this.name;
    
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
    try {
    ResourceBundle messages;
    messages = ResourceBundle.getBundle("MessageResources");
    String i18nTitle = messages.getString("map.layer." + resourceName);
    if( i18nTitle != null ) {
      setTitle(i18nTitle);
    }
    String description = messages.getString("map.layer." + resourceName + ".description");
    if( description != null ) {
      setDescription(description);
    }
    }
    catch(MissingResourceException e) {
      log.warn("Layer '"+ resourceName + 
        "': Some resources needed for i18n of map layer description/title are missing:");
      log.error(e.getMessage());
    }
  }

  public String getTitle()
  {
    return title;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean isdefault)
  {
    this.active = isdefault;
  }

  public boolean hasLegend()
  {
    return hasLegend;
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

  public Vector<AbstractParameter> getParameters(boolean resolveReferences)
  {
    Vector<AbstractParameter> params = new Vector<AbstractParameter>();
    if (resolveReferences)
    {
      for (AbstractParameter p : parameters.values())
      {
        params.add(getParameter(p.getName()));
      }
    }
    else
    {
      params.addAll(parameters.values());
    }
    return params;
  }

  public Map<String, ValueParameter> getValueParameters()
  {
    Map<String, ValueParameter> valueParameters = new HashMap<String, ValueParameter>();
    for (AbstractParameter p : getParameters(true))
    {
      if (p != null && p instanceof ValueParameter)
      {
        valueParameters.put(p.getName(), (ValueParameter) p);
      }
    }
    return valueParameters;
  }

  public boolean dependsOnParameter(String name)
  {
    AbstractParameter p = parameters.get(name);
    return p != null;
  }

  public AbstractParameter getParameter(String name)
  {
    AbstractParameter p = parameters.get(name);
    if (p != null)
    {
      if (p instanceof ReferenceParameter)
      {
        p = map.getParameter(name);
      }
    }
    return p;
  }

  public void updateParameter(String name, Object value)
  {
    AbstractParameter p = getParameter(name);
    if (p != null && p instanceof ValueParameter)
    {
      log.debug("Get Parameter: " + p.getName() + " arg: " + name);
      ((ValueParameter) p).setValue((String) value);
    }
  }

  public String getName()
  {
    return name;
  }

  protected void addParameter(AbstractParameter p)
  {
    if (this.parameters.get(p.getName()) == null)
    {
      this.parameters.put(p.getName(), p);
    }
    else
    {
      log.error("Layerparameter '" + p.getName() + "' already exists.");
    }
  }

  public String getDescription()
  {
    return description;
  }

  public boolean isQueryable()
  {
    return queryable;
  }

  public void setQueryable(boolean queryable)
  {
    this.queryable = queryable;
  }

  protected void setDescription(String description)
  {
    this.description = description;
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
   * @param opaque The opaque to set.
   */
  public void setOpaque(boolean opaque)
  {
    this.opaque = opaque;
  }

}
