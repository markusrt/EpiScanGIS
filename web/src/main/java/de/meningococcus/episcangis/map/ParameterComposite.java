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

//  @Override
//  public ParameterComponent get(String elementName)
//  {
//    ParameterComponent result = super.get(elementName);
//    if (result != null)
//    {
//      return result;
//    }
//    else
//    {
//      Iterator<ParameterComponent> iterator = topLevelIterator();
//      while (iterator.hasNext())
//      {
//        result = iterator.next();
//        if (result != null)
//        {
//          return result;
//        }
//      }
//    }
//    return null;
//  }

  public int size()
  {
    return elements.size();
  }

  @Override
  public void selectValue(String value, boolean selection)
      throws InvalidParameterValueException
  {
    boolean changedValue = false;
    Iterator<ParameterComponent> iterator = topLevelIterator();
    while (iterator.hasNext())
    {
      ParameterComponent next = iterator.next();
      next.selectValue(value, selection);
      if (next.getValue().equals(value) && next.isSelected() != selection)
      {
        throw new InvalidParameterValueException("Parameter value '" + value
            + "' is invalid for the parameter '" + getName() + "'");
      }
      else if (next.getValue().equals(value) && next.isSelected() == selection)
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
  public Iterator<ParameterComponent> iterator()
  {
    return new ParameterComponentIterator(elements.iterator());
  }

  public Iterator<ParameterComponent> topLevelIterator()
  {
    return elements.iterator();
  }

  @Override
  public String getValue()
  {
    StringBuffer value = new StringBuffer();
    value.append("{");
    Iterator<ParameterComponent> iterator = topLevelIterator();
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
    Iterator<ParameterComponent> iterator = topLevelIterator();
    while (iterator.hasNext())
    {
      ParameterComponent parameterComponent = iterator.next();
      b.append(parameterComponent.toXML());
    }
    b.append("</").append(elementName).append(">");
    return b.toString();
  }

}
