package de.meningococcus.episcangis.map.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.map.LayerNotFoundException;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class WmsLegendExporter extends AbstractWmsExporter
{
  private static Log log = LogFactory.getLog(WmsLegendExporter.class);

  private String format = "image/png", layer;

  public WmsLegendExporter(String layerName)
  {
    layer = layerName;
  }

  public String getContentType()
  {
    return format;
  }

  public InputStream getInputStream() throws IOException,
      LayerNotFoundException
  {
    if (layers.get(layer) == null)
    {
      throw new LayerNotFoundException("Layer '" + layer
          + "' does not exist. Legendgraphic could not be created.");
    }

    StringBuilder urlBuilder = new StringBuilder(getWmsUrl());
    urlBuilder.append("&REQUEST=GetLegendGraphic&FORMAT=").append(format)
        .append("&LAYER=").append(layer);

    URL imageUrl = new URL(urlBuilder.toString());
    log.debug("Legend URL: " + imageUrl.toString());
    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
    return conn.getInputStream();
  }

  @Override
  protected String getRequest()
  {
    return "&REQUEST=GetLegendGraphic";
  }

}
