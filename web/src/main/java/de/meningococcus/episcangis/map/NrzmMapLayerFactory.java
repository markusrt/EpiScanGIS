package de.meningococcus.episcangis.map;

import java.util.Locale;

import de.meningococcus.episcangis.map.layer.BordersLayer;
import de.meningococcus.episcangis.map.layer.CaseTypesLayer;
import de.meningococcus.episcangis.map.layer.ClusterLayer;
import de.meningococcus.episcangis.map.layer.IncidenceLayer;
import de.meningococcus.episcangis.map.layer.MapLayer;
import de.meningococcus.episcangis.map.layer.PopdensityLayer;
import de.meningococcus.episcangis.map.layer.SerogroupsLayer;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

final class NrzmMapLayerFactory implements MapLayerFactory
{
  public MapLayer getMapLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    MapLayer ret;
    String realName;
    if( name == null ) {
      name = "<unnamed layer>";
    }
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
      //TODO remove ids (82)
      ret = new ClusterLayer(name, title, hasLegend, true, map, 82);
    }
    else if (realName.equals("clusterretro"))
    {
      //TODO remove ids (211)
      ret = new ClusterLayer(name, title, hasLegend, true, map, 211);
    }
    else
    {
      ret = new MapLayer(name, title, hasLegend, map);
    }
    return ret;
  }

  public String getMapUrlKeyPrefix()
  {
    return "nrzm.";
  }
}
