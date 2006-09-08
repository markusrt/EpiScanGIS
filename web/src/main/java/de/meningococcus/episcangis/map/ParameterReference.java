package de.meningococcus.episcangis.map;

import java.util.Date;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterReference extends ParameterComponent
{
  private static Log log = LogFactory.getLog(ParameterReference.class);

  private ParameterComponent referencedComponent;

  public ParameterReference(String name)
  {
    super(name);
  }

  public ParameterReference(String name, ParameterComponent referenence)
  {
    this(name);
    referencedComponent = referenence;
  }

  private ParameterComponent getReference()
  {
    if (referencedComponent == null)
    {
     log.warn("Reference '" + getName() + "' could not be resolved");
     return new ParameterValue("reference_to_" + getName(), "<<unresolved>>");
    }
    else {
      return referencedComponent;
    }
  }

  public void setReference(ParameterComponent parameterComponent) {
     referencedComponent = parameterComponent;
  }


  @Override
  public void selectValue(String value, boolean selection)
      throws InvalidParameterValueException
  {
    getReference().selectValue(value, selection);
  }

  @Override
  public boolean isSelected()
  {
    return getReference().isSelected();
  }

  @Override
  public void setSelected(boolean selection)
  {
    getReference().setSelected(selection);
  }

  @Override
  public String getValue()
  {
    return getReference().getValue();
  }

  @Override
  public Iterator<ParameterComponent> oldIterator()
  {
    return getReference().oldIterator();
  }

  @Override
  public String toXML()
  {
    return "";
  }

  @Override
  public Iterator<ParameterComponent> iterator()
  {
    return getReference().iterator();
  }

  @Override
  public String getAliasValue()
  {
    return getReference().getAliasValue();
  }

  public ParameterComponent get(String elementName)
  {
    return getReference().get(elementName);
  }


}
