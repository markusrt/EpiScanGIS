package de.meningococcus.episcangis.map;

import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SelectParameter extends ParameterComposite
{
  private static Log log = LogFactory.getLog(SelectParameter.class);

  boolean singleSelect = false;

  public SelectParameter(String name, String title, boolean singleSelect)
  {
    super(name, title);
    this.singleSelect = singleSelect;
  }

  @Override
  public void selectValue(String value, boolean selection)
  {
    if (singleSelect)
    {
      Iterator<ParameterComponent> iterator = iterator();
      while (iterator.hasNext())
      {
        iterator.next().setSelected(false);
      }
    }
    super.selectValue(value, selection);
  }

  @Override
  public String getValue()
  {
    StringBuffer value = new StringBuffer();
    Iterator<ParameterComponent> iterator = iterator();
    while (iterator.hasNext())
    {
      ParameterComponent parameterComponent = iterator.next();
      try
      {
        if (parameterComponent.isSelected())
        {
          value.append(parameterComponent.getValue()).append(',');
        }
      }
      catch (UnsupportedOperationException exception)
      {
        log.warn("SelectParameter supports only ParameterValue components");
      }
    }
    if (value.length() > 0 && value.charAt(value.length() - 1) == ',')
    {
      value.deleteCharAt(value.length() - 1);
    }
    return value.toString();
  }

  public boolean isSingleSelect()
  {
    return singleSelect;
  }

}
