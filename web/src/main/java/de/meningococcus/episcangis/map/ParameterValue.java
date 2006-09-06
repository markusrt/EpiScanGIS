package de.meningococcus.episcangis.map;

import java.util.Iterator;

import org.apache.commons.lang.StringEscapeUtils;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterValue extends ParameterComponent
{

  private String value;

  private boolean selected = false;

  public ParameterValue(String value)
  {
    this(value, value);
  }

  public ParameterValue(String title, String value)
  {
    super(null, title);
    this.value = value;
  }

  public void setValue(String value)
  {
    this.value = value;

  }

//  /**
//   * Implementation of the template method getElement. ParameterValues are not
//   * named, as they are the leafs of our tree. Therefore this method returns
//   * this component irrespective of the given name.
//   *
//   * @see ParameterComponent#get(java.lang.String)
//   */
//  @Override
//  public ParameterComponent get(String name)
//  {
//    if( this.getName().equals(name))return this;
//    else return null;
//  }

  @Override
  public String getValue()
  {
    return value;
  }

  @Override
  public void selectValue(String value, boolean selection)
  {
    if (getValue().equals(value))
    {
      setSelected(selection);
    }
  }

  @Override
  public boolean isSelected()
  {
    return selected;
  }

  @Override
  public void setSelected(boolean selection)
  {
    selected = selection;
  }

  public String toString()
  {
    return value;
  }

  public boolean equals(Object obj)
  {
    if (obj.getClass().equals(ParameterValue.class))
    {
      return (this.value.equals(((ParameterValue) obj).getValue()));
    }
    else if (obj.getClass().equals(String.class))
    {
      return this.value.equals((String) obj);
    }
    else
    {
      return super.equals(obj);
    }
  }

  @Override
  public Iterator<ParameterComponent> oldIterator()
  {
    return new NullIterator<ParameterComponent>();
  }

  @Override
  public void selectValue(String value)
  {
    if (getValue().equals(value))
    {
      setSelected(true);
    }
  }

  @Override
  public String toXML()
  {
    StringBuilder b = new StringBuilder(800);
    b.append("<value selected=\"").append(isSelected()).append("\" name=\"")
        .append(StringEscapeUtils.escapeXml(getTitle())).append("\">").append(
            StringEscapeUtils.escapeXml(getValue())).append("</value>");
    return b.toString();
  }

  @Override
  public Iterator<ParameterComponent> iterator()
  {
    return new NullIterator<ParameterComponent>();
  }

  @Override
  public String getAliasValue()
  {
    return getTitle();
  }
}
