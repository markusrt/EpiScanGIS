package de.meningococcus.episcangis.map.exporter;

import java.util.Map;
import java.util.Vector;

import de.meningococcus.episcangis.db.model.BoundingBox;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.layer.MapLayer;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PrintExporter implements AbstractWmsMap.Exporter
{
  private Map<String, MapLayer> layers = null;

  private ParameterComponent mapParameters = null;

  private int width, height;

  public void addBbox(BoundingBox box)
  {
    // not used
  }

  public void addFormat(String format)
  {
    // not used
  }

  public void addHeight(int height)
  {
    this.height = height;
  }

  public void addWidth(int width)
  {
    this.width = width;
  }

  public void addSrs(String srs)
  {
    // not used
  }

  public void addTransparent(boolean transparent)
  {
    // not used
  }

  public void addWmsUrl(String wmsUrl)
  {
    // not used
  }

  public void addLayers(Map<String, MapLayer> layers)
  {
    this.layers = layers;
  }

  public void addMapparameters(ParameterComponent parameters)
  {
    this.mapParameters = parameters;
  }

  public Vector<MapLayer> getActiveLayers() {
    Vector<MapLayer> activeLayers = new Vector<MapLayer>();
    for( MapLayer layer : layers.values() ) {
      if(layer.isActive()) {
        activeLayers.add(layer);
      }
    }
    return activeLayers;
  }

  public ParameterComponent getMapparameters() {
    return mapParameters;
  }

}
