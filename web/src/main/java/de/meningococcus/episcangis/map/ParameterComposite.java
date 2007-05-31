package de.meningococcus.episcangis.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringEscapeUtils;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterComposite extends ParameterComponent
{
  private LinkedHashMap<String,ParameterComponent> elements;
  protected String elementName = "composite";

  public ParameterComposite(String name)
  {
    this(name, "");
  }

  public ParameterComposite(String name, String title)
  {
    super(name, title);
    elements = new LinkedHashMap<String,ParameterComponent>();
  }

  public void add(ParameterComponent element)
  {
    elements.put(element.getName(), element);
  }

  public int size()
  {
    return elements.size();
  }

  @Override
  public boolean isSelected()
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setSelected(boolean selection)
  {
    throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<ParameterComponent> oldIterator()
  {
    return elements.values().iterator();
  }

  public Iterator<ParameterComponent> iterator()
  {
    return elements.values().iterator();
  }

  @Override
  public String getValue()
  {
    StringBuffer value = new StringBuffer();
    value.append("{");
    Iterator<ParameterComponent> iterator = iterator();
    while (iterator.hasNext())
    {
      ParameterComponent parameterComponent = iterator.next();
      if (parameterComponent instanceof ParameterComposite)
      {
        value.append(parameterComponent.getName()).append("=");
      }
      value.append(parameterComponent.getValue());
      if (iterator.hasNext())
      {
        value.append(',');
      }
    }
    value.append("}");
    return value.toString();
  }

  @Override
  public String getAliasValue()
  {
    return getValue();
  }

  @Override
  public String toXML()
  {
    StringBuilder b = new StringBuilder();
    b.append("<").append(elementName).append(" name=\"").append(
        StringEscapeUtils.escapeXml(getName())).append("\">");
    if (getTitle().length() > 0)
    {
      b.append("<title>").append(StringEscapeUtils.escapeXml(getTitle()))
          .append("</title>");
    }
    for (ParameterComponent pc : this)
    {
      b.append(pc.toXML());
    }
    b.append("</").append(elementName).append(">");
    return b.toString();
  }

  /**
   * @return the elements
   */
  public Collection<ParameterComponent> getElements()
  {
    return elements.values();
  }

  @Override
  public ParameterComponent get(String elementName)
  {
    ParameterComponent pc = (ParameterComponent) elements.get(elementName);
    if (pc == null)
    {
      pc = super.get(elementName);
    }
    return pc;
  }
}
