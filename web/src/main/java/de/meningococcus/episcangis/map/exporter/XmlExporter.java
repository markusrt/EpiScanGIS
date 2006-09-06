package de.meningococcus.episcangis.map.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

import de.meningococcus.episcangis.db.model.BoundingBox;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.layer.MapLayer;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class XmlExporter implements AbstractWmsMap.Exporter
{
  private Map<String, MapLayer> layers = null;

  private ParameterComponent mapParameters = null;

  private int width, height;

  private static final String ENCODING = "utf-8",
      XML_HEADER = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\"?>";

  public String buildXML()
  {
    StringBuilder b = new StringBuilder(100000);
    b.append(XML_HEADER).append(System.getProperty("line.separator"));
    b.append("<map>");
    b.append("<size width=\"").append(width).append("\" height=\"").append(
        height).append("\"/>");
    b.append(mapParameters.toXML());

    for (MapLayer mlb : layers.values())
    {
      b.append("<layer title=\"").append(
          StringEscapeUtils.escapeXml(mlb.getTitle())).append("\" active=\"")
          .append(mlb.isActive()).append("\" name=\"").append(
              StringEscapeUtils.escapeXml(mlb.getName())).append(
              "\" description=\"").append(
              StringEscapeUtils.escapeXml(mlb.getDescription())).append("\">");
      if (mlb.hasLegend())
      {
        b.append("<legend />");
      }
      b.append(mlb.getParameters().toXML());
      b.append("</layer>");
    }
    b.append("</map>");

    return b.toString();
  }

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

  public String getContentType()
  {
    return "text/xml";
  }

  public InputStream getInputStream() throws IOException
  {
    return IOUtils.toInputStream(buildXML(), ENCODING);
  }

}
