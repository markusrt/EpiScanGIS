package de.meningococcus.episcangis.map.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.map.LayerNotFoundException;
import de.meningococcus.episcangis.map.layer.MapLayer;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class WmsInformationExporter extends AbstractWmsExporter
{
  private static Log log = LogFactory.getLog(WmsInformationExporter.class);

  private String format = "text/xml";

  private int x, y;

  public WmsInformationExporter(/* File cacheDirectory, */int xPosition,
      int yPosition)
  {
    x = xPosition;
    y = yPosition;
  }

  public WmsInformationExporter(/* File cacheDirectory, */int xPosition,
      int yPosition, String layerName)
  {
    this(/* cacheDirectory, */xPosition, yPosition);
  }

  public String getContentType()
  {
    return format;
  }

  @SuppressWarnings("unchecked")
  private String parseGetFeatureInfoResult(URL queryUrl) throws IOException
  {
    StringBuilder trimmedResult = new StringBuilder(500);
    HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
    GetMethod get = new GetMethod(queryUrl.toString());
    client.executeMethod(get);
    List<String> lines = IOUtils.readLines(get.getResponseBodyAsStream());
    for (String line : lines)
    {
      if (line.startsWith("    ") && !line.startsWith("    oid"))
      {
        trimmedResult.append("<feature>").append(line).append("</feature>");
      }
    }
    return trimmedResult.toString();
  }

  public InputStream getInputStream() throws IOException,
      LayerNotFoundException
  {
    StringBuilder output = new StringBuilder(10000);
    output.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    output.append("<result>");
    for (MapLayer mlb : getActiveLayers())
    {
      StringBuilder urlBuilder = new StringBuilder(buildLayerUrl(mlb.getName()));
      urlBuilder.append("&X=").append(x);
      urlBuilder.append("&Y=").append(y);
      urlBuilder.append("&QUERY_LAYERS=").append(mlb.getName());
      log.debug("Feature URL: " + urlBuilder.toString());
      String parsed = parseGetFeatureInfoResult(new URL(urlBuilder.toString()));
      if (parsed.length() > 0)
      {
        output.append("<layer name=\"").append(
            StringEscapeUtils.escapeXml(mlb.getName())).append("\" title=\"")
            .append(StringEscapeUtils.escapeXml(mlb.getTitle())).append("\">");
        output.append(parsed);
        output.append("</layer>");
      }

    }
    output.append("</result>");
    return IOUtils.toInputStream(output.toString());
  }

  @Override
  protected String getRequest()
  {
    return "&REQUEST=GetFeatureInfo";
  }

}
