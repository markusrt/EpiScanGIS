package de.meningococcus.episcangis.map;

import java.util.Locale;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

final class PublicMapLayerFactory implements MapLayerFactory
{
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
    else if (realName.equals("cluster"))
    {
      // Public is not allowed to see this layer
      ret = null;
    }
    else if (realName.equals("clusterretro"))
    {
      // Public is not allowed to see this layer
      ret = null;
    }
    else
    {
      ret = new MapLayer(name, title, hasLegend, map);
    }
    return ret;
  }
}
