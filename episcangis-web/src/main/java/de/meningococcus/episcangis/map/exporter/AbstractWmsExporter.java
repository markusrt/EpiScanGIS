package de.meningococcus.episcangis.map.exporter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


import de.meningococcus.episcangis.db.model.BoundingBox;
import de.meningococcus.episcangis.map.AbstractParameter;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.LayerNotFoundException;
import de.meningococcus.episcangis.map.MapLayer;
import de.meningococcus.episcangis.map.ValueParameter;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

abstract class AbstractWmsExporter implements AbstractWmsMap.Exporter
{
  private String srs;

  private BoundingBox bbox = null;

  protected Map<String, MapLayer> layers;

  private int width, height;

  private String wmsUrl;

  abstract protected String getRequest();

  private String buildUrl(Vector<MapLayer> usedLayers)
  {
    StringBuilder urlBuilder = new StringBuilder(200);
    urlBuilder.append(wmsUrl);
    urlBuilder.append(getRequest());
    urlBuilder.append("&SRS=").append(srs);
    urlBuilder.append("&BBOX=").append(bbox.toString());
    urlBuilder.append("&WIDTH=").append(width);
    urlBuilder.append("&HEIGHT=").append(height);

    Map<String, ValueParameter> usedParameters = new HashMap<String, ValueParameter>();

    /*
     * Collect all paramameters from used layers
     */
    urlBuilder.append("&layers=");
    for (Iterator usedLayersIt = usedLayers.iterator(); usedLayersIt.hasNext();)
    {
      MapLayer mlb = (MapLayer) usedLayersIt.next();
      urlBuilder.append(mlb.getName());
      if (usedLayersIt.hasNext())
      {
        urlBuilder.append(",");
      }
      usedParameters.putAll(mlb.getValueParameters());
    }
    for (String parameterName : usedParameters.keySet())
    {
      urlBuilder.append("&").append(parameterName).append("=").append(
          usedParameters.get(parameterName).getValue());
    }
    return urlBuilder.toString();

  }

  protected Vector<MapLayer> getActiveLayers()
  {
    Vector<MapLayer> active = new Vector<MapLayer>();
    for (MapLayer mlb : layers.values())
    {
      if (mlb.isActive())
      {
        active.add(mlb);
      }
    }
    return active;
  }

  protected String buildMapUrl()
  {
    return buildUrl(getActiveLayers());
  }

  protected String buildLayerUrl(String layer) throws LayerNotFoundException
  {
    Vector<MapLayer> usedLayers = new Vector<MapLayer>();
    if (layer != null)
    {
      MapLayer singleLayer = (MapLayer) layers.get(layer);
      if (singleLayer == null)
      {
        throw new LayerNotFoundException(
            "WmsExporter is configured to export a single layer named '"
                + layer + "', but this layer does not exits.");
      }
      usedLayers.add(singleLayer);
    }
    return buildUrl(usedLayers);
  }

  public void addBbox(BoundingBox box)
  {
    bbox = box;
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
    this.srs = srs;
  }

  public void addWmsUrl(String wmsUrl)
  {
    this.wmsUrl = wmsUrl;
  }

  public void addLayers(Map<String, MapLayer> layers)
  {
    this.layers = layers;
  }

  public void addMapparameters(Vector<AbstractParameter> parameters)
  {
    // not used
  }

  protected String getWmsUrl()
  {
    return wmsUrl;
  }
}
