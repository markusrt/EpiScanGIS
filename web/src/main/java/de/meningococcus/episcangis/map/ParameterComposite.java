package de.meningococcus.episcangis.map;

import java.util.ArrayList;
import java.util.Iterator;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public abstract class ParameterComposite extends ParameterComponent
{
  ArrayList<ParameterComponent> parameterComponents;

  public ParameterComposite(String name, String title)
  {
    super(name, title);
    parameterComponents = new ArrayList<ParameterComponent>();
  }

  @Override
  protected ParameterComponent getElement(String name)
  {
    ParameterComponent parameterComponent;
    Iterator<ParameterComponent> iterator = iterator();
    while (iterator.hasNext())
    {
      parameterComponent = iterator.next();
      if (parameterComponent.getName() != null
          && parameterComponent.getName().equals(name))
      {
        return parameterComponent;
      }
    }
    return null;
  }

  @Override
  protected final void addElement(ParameterComponent element)
  {
    parameterComponents.add(element);
  }

  @Override
  public void selectValue(String value, boolean selection)
  {
    Iterator<ParameterComponent> iterator = iterator();
    while (iterator.hasNext())
    {
      iterator.next().selectValue(value, selection);
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
    return new ParameterComponentIterator(parameterComponents.iterator());
  }

  public void setValue(String value)
  {
    Iterator<ParameterComponent> iterator = iterator();
    while (iterator.hasNext())
    {
      iterator.next().setValue(value);
    }
  }

  public abstract String getValue();
}
