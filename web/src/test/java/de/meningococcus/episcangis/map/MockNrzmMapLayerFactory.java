package de.meningococcus.episcangis.map;

import java.util.Locale;

import de.meningococcus.episcangis.map.layer.BordersLayer;
import de.meningococcus.episcangis.map.layer.CaseTypesLayer;
import de.meningococcus.episcangis.map.layer.ClusterLayer;
import de.meningococcus.episcangis.map.layer.IncidenceLayer;
import de.meningococcus.episcangis.map.layer.MapLayer;
import de.meningococcus.episcangis.map.layer.MockSerogroupsLayer;
import de.meningococcus.episcangis.map.layer.PopdensityLayer;
import de.meningococcus.episcangis.map.layer.SerogroupsLayer;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

final class MockNrzmMapLayerFactory implements MapLayerFactory
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
    if (realName.equals("serogroups"))
    {
      ret = new MockSerogroupsLayer(name, title, hasLegend, map);
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
