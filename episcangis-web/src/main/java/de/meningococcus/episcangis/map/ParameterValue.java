package de.meningococcus.episcangis.map;

import java.util.Iterator;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterValue extends ParameterComponent
{

  private String value;

  private boolean selected = false;

  public ParameterValue(String title, String value)
  {
    super(null, title);
    this.value = value;
  }

  @Override
  public void setValue(String value)
  {
    this.value = value;

  }

  /**
   * Implementation of the template method getElement. ParameterValues are not
   * named, as they are the leafs of our tree. Therefore this method returns
   * this component irrespective of the given name.
   * @see ParameterComponent#getElement(java.lang.String)
   */
  @Override
  protected ParameterComponent getElement(String name)
  {
    return this;
  }

  /**
   * @see ParameterComponent#addElement(ParameterComponent)
   */
  @Override
  protected void addElement(ParameterComponent element)
  {
    throw new UnsupportedOperationException(this.getClass().getSimpleName()
        + " must not contain ParameterComponents");
  }

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
  public Iterator<ParameterComponent> iterator()
  {
    return new NullIterator<ParameterComponent>();
  }
}
