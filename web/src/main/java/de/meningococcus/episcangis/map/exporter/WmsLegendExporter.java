package de.meningococcus.episcangis.map.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
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

  private String format = "image/gif", layer;

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

    HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
    GetMethod get = new GetMethod(urlBuilder.toString());
    client.executeMethod(get);

    //HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
    return get.getResponseBodyAsStream();
    //HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
    //return conn.getInputStream();
  }

  @Override
  protected String getRequest()
  {
    return "&REQUEST=GetLegendGraphic";
  }

}
