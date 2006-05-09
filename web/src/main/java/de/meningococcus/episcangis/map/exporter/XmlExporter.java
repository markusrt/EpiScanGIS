package de.meningococcus.episcangis.map.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

import de.meningococcus.episcangis.db.model.BoundingBox;
import de.meningococcus.episcangis.map.AbstractParameter;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.CheckboxParameter;
import de.meningococcus.episcangis.map.MapLayer;
import de.meningococcus.episcangis.map.MultiSelectParameter;
import de.meningococcus.episcangis.map.OLDSelectParameter;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.PeriodParameter;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class XmlExporter implements AbstractWmsMap.Exporter
{
  private Map<String, MapLayer> layers = null;

  private Vector<AbstractParameter> mapParameters = null;

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
    b.append(parametersToXML(mapParameters));

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
      b.append(parametersToXML(mlb.getParameters(false)));
      b.append("</layer>");
    }
    b.append("</map>");

    return b.toString();
  }

  private String parametersToXML(Collection<AbstractParameter> parameters)
  {
    StringBuilder b = new StringBuilder(1000);
    for (AbstractParameter p : parameters)
    {
      b.append(parameterToXML(p));
    }
    return b.toString();
  }

  private String parameterToXML(AbstractParameter p)
  {
    StringBuilder b = new StringBuilder(1000);
    if (p instanceof PeriodParameter)
    {
      b.append("<periodparameter name=\"").append(
          StringEscapeUtils.escapeXml(p.getName())).append("\">");
      if (p.getTitle().length() > 0)
      {
        b.append("<title>").append(StringEscapeUtils.escapeXml(p.getTitle()))
            .append("</title>");
      }
      b.append(parameterToXML(((PeriodParameter) p).getFromMonth()));
      b.append(parameterToXML(((PeriodParameter) p).getFromYear()));
      b.append(parameterToXML(((PeriodParameter) p).getToMonth()));
      b.append(parameterToXML(((PeriodParameter) p).getToYear()));
      b.append("</periodparameter>");
    }
    else if (p instanceof MultiSelectParameter)
    {
      b.append("<multiselectparameter name=\"").append(
          StringEscapeUtils.escapeXml(p.getName())).append("\">");
      b.append(valuesToXML(((MultiSelectParameter) p).getValues()));
      b.append("</multiselectparameter>");
    }
    else if (p instanceof CheckboxParameter)
    {
      b.append("<checkboxparameter name=\"").append(
          StringEscapeUtils.escapeXml(p.getName())).append("\">");
      if (p.getTitle().length() > 0)
      {
        b.append("<title>").append(StringEscapeUtils.escapeXml(p.getTitle()))
            .append("</title>");
      }
      b.append(valuesToXML(((OLDSelectParameter) p).getValues()));
      b.append("</checkboxparameter>");
    }
    else if (p instanceof OLDSelectParameter)
    {
      b.append("<selectparameter name=\"").append(
          StringEscapeUtils.escapeXml(p.getName())).append("\">");
      if (p.getTitle().length() > 0)
      {
        b.append("<title>").append(StringEscapeUtils.escapeXml(p.getTitle()))
            .append("</title>");
      }
      b.append(valuesToXML(((OLDSelectParameter) p).getValues()));
      b.append("</selectparameter>");
    }
    return b.toString();
  }

  private String valuesToXML(Collection<ParameterValue> values)
  {
    StringBuilder b = new StringBuilder(800);
    for (ParameterValue value : values)
    {
      b.append("<value selected=\"").append(value.isSelected()).append(
          "\" name=\"").append(StringEscapeUtils.escapeXml(value.getTitle()))
          .append("\">").append(StringEscapeUtils.escapeXml(value.getValue()))
          .append("</value>");
    }
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

  public void addMapparameters(Vector<AbstractParameter> parameters)
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
