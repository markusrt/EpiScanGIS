package de.meningococcus.episcangis.map;

import java.util.Iterator;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterValueBak extends ParameterComponent
{
  private String value;

  private boolean selected = false;

  public ParameterValueBak(String title, String value)
  {
    super(null, title);
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
   * 
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
      selected = selection;
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
    // TODO Auto-generated method stub
    
  }

  @Override
  public Iterator<ParameterComponent> iterator()
  {
    // TODO Auto-generated method stub
    return null;
  }

}
