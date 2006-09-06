package de.meningococcus.episcangis.map;

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

  private ParameterComponent referencedParameter;

  private ParameterComponent myReference;

  public ParameterReference(String name,
      ParameterComponent parameterToReference)
  {
    super(name);
    referencedParameter = parameterToReference;
  }

  private ParameterComponent getMyReference()
  {
    if (myReference == null)
    {
      //myReference = referencedParameter.get(getName());
      Iterator<ParameterComponent> i = new ParameterComponentIterator(referencedParameter.iterator());
      while(i.hasNext()) {
        ParameterComponent hit = i.next();
        if( !(hit instanceof ParameterReference) && hit.getName().equals(getName())) {
          myReference = hit;
          break;
        }
      }
      if ( myReference == null )
      {
        log.warn("reference '" + getName() + "' could not be resolved");
        return new ParameterValue("reference_to_" + getName(), "<<unresolved>>");
      }
    }
    return myReference;
  }

  @Override
  public ParameterComponent get(String elementName)
  {
    return getMyReference().get(elementName);
  }

  @Override
  public void selectValue(String value, boolean selection) throws InvalidParameterValueException
  {
    getMyReference().selectValue(value, selection);
  }

  @Override
  public boolean isSelected()
  {
    return getMyReference().isSelected();
  }

  @Override
  public void setSelected(boolean selection)
  {
    getMyReference().setSelected(selection);
  }

  @Override
  public String getValue()
  {
    return getMyReference().getValue();
  }

  @Override
  public Iterator<ParameterComponent> iterator()
  {
    return getMyReference().iterator();
  }

  @Override
  public String toXML()
  {
    return "";
  }

  @Override
  public Iterator<ParameterComponent> topLevelIterator()
  {
    return getMyReference().topLevelIterator();
  }

  @Override
  public String getAliasValue()
  {
    return getMyReference().getAliasValue();
  }

}
