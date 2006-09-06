package de.meningococcus.episcangis.map;

import de.meningococcus.episcangis.map.layer.MapLayer;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public interface MapLayerFactory
{
  public abstract MapLayer getMapLayer(String name, String title,
      boolean hasLegend, AbstractWmsMap map);

  /**
   * @return prefix to the default configuration key wms.url (for example
   *         "nrzm."). This allows for different factories having different
   *         URLs.
   */
  public abstract String getMapUrlKeyPrefix();
}
