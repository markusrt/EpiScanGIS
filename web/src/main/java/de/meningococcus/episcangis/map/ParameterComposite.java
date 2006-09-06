package de.meningococcus.episcangis.map;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.lang.StringEscapeUtils;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterComposite extends ParameterComponent
{
  private ArrayList<ParameterComponent> elements;

  public ParameterComposite(String name)
  {
    this(name, "");
  }

  public ParameterComposite(String name, String title)
  {
    super(name, title);
    elements = new ArrayList<ParameterComponent>();
  }

  public void add(ParameterComponent element)
  {
    if (get(element.getName()) == null || element instanceof ParameterValue)
    {
      elements.add(element);
    }
  }

  public int size()
  {
    return elements.size();
  }

  @Override
  public void selectValue(String value, boolean selection)
      throws InvalidParameterValueException
  {
    boolean changedValue = false;
    for (ParameterComponent pc : this)
    {
      pc.selectValue(value, selection);
      if (pc.getValue().equals(value) && pc.isSelected() != selection)
      {
        throw new InvalidParameterValueException("Parameter value '" + value
            + "' is invalid for the parameter '" + getName() + "'");
      }
      else if (pc.getValue().equals(value) && pc.isSelected() == selection)
      {
        changedValue = true;
      }
    }
    if (!changedValue)
    {
      throw new InvalidParameterValueException("Parameter value '" + value
          + "' is invalid for the parameter '" + getName() + "'");
    }
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
    return new ParameterComponentIterator(elements.iterator());
  }

  public Iterator<ParameterComponent> iterator()
  {
    return elements.iterator();
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
  public ArrayList<ParameterComponent> getElements()
  {
    return elements;
  }
}
