package de.meningococcus.episcangis.map;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

final class PublicMapLayerFactory implements MapLayerFactory
{
  private static Log log = LogFactory.getLog(PublicMapLayerFactory.class);
  
  public MapLayer getMapLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    MapLayer ret;
    String realName;
    int index = name.lastIndexOf('_');
    if (index != -1)
    {
      realName = name.substring(0, index).toLowerCase(Locale.ENGLISH);
    }
    else
    {
      realName = name;
    }
    if (realName.equals("casetypes"))
    {
      ret = new CaseTypesLayer(name, title, hasLegend, map);
    }
    else if (realName.equals("serogroups"))
    {
      ret = new SerogroupsLayer(name, title, hasLegend, map);
    }
    else if (realName.equals("incidence"))
    {
      ret = new IncidenceLayer(name, title, hasLegend, map);
    }
    else if (realName.equals("borders"))
    {
      ret = new BordersLayer(name, title, hasLegend, map);
    }
    else if (realName.equals("popdensity"))
    {
      ret = new PopdensityLayer(name, title, hasLegend, map);
    }
    else if (realName.equals("cities"))
    {
      ret = new MapLayer(name, title, hasLegend, map);
    }
    else
    {
      // Public is not allowed to see all layers
      log.info("Layer " + realName + " is disabled in this factory.");
      ret = null;
    }
    return ret;
  }

  public String getMapUrlKeyPrefix()
  {
    return "";
  }
}
