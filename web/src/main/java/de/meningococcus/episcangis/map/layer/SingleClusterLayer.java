package de.meningococcus.episcangis.map.layer;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ArbitraryValueParameter;
import de.meningococcus.episcangis.map.ParameterComponent;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SingleClusterLayer extends MapLayer
{
  public SingleClusterLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    ParameterComponent clusterSelector = new ArbitraryValueParameter(
        "CLUSTERID");
    if (isValid())
    {
      addParameter(clusterSelector);
    }
  }
}
