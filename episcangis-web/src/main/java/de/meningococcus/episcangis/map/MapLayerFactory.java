package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public interface MapLayerFactory
{
  public abstract MapLayer getMapLayer(String name, String title,
      boolean hasLegend, AbstractWmsMap map);
}
