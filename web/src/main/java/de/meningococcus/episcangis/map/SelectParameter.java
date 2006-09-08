package de.meningococcus.episcangis.map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SelectParameter extends ParameterComposite
{
  private static Log log = LogFactory.getLog(SelectParameter.class);

  private boolean multiSelect = false;

  public SelectParameter(String uniqueName)
  {
    this(uniqueName, "");
  }

  public SelectParameter(String uniqueName, String longTitle)
  {
    this(uniqueName, longTitle, false);
  }

  public SelectParameter(String uniqueName, String longTitle,
      boolean isMultiSelect)
  {
    super(uniqueName, longTitle);
    this.multiSelect = isMultiSelect;
    if (isMultiSelect)
    {
      this.elementName = "multiselectparameter";
    }
    else
    {
      this.elementName = "selectparameter";
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.map.ParameterComponent#add(de.meningococcus.episcangis.map.ParameterComponent)
   */
  public void add(ParameterComponent newElement)
  {
    if (newElement instanceof ParameterValue)
    {
      super.add(newElement);
      if (getValue().equals(""))
      {
        newElement.setSelected(true);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see de.meningococcus.episcangis.map.ParameterComponent#selectValue(java.lang.String,
   *      boolean)
   */
  @Override
  public void selectValue(String selectedValue, boolean isSelected)
      throws InvalidParameterValueException
  {
    String[] subValues;
    for (ParameterComponent pc : this)
    {
      pc.setSelected(false);
    }

    ParameterComponent value = super.get(selectedValue);
    if(value != null ){
      value.setSelected(isSelected);
    }
    if (isMultiSelect() && !getValue().equals(selectedValue)
        && (subValues = selectedValue.split(",")).length > 0)
    {
      for (int i = 0; i < subValues.length; i++)
      {
        ParameterComponent v = super.get(subValues[i]);
        if(v != null ){
          v.setSelected(isSelected);
        }
      }
    }

  }

  @Override
  public String getValue()
  {
    StringBuffer value = new StringBuffer();
    for (ParameterComponent pc : this)
    {
      try
      {
        if (pc.isSelected())
        {
          value.append(pc.getValue()).append(',');
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

  @Override
  public String getAliasValue()
  {
    StringBuffer value = new StringBuffer();
    for (ParameterComponent pc : this)
    {
      try
      {
        if (pc.isSelected())
        {
          value.append(pc.getTitle()).append(',');
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

  public boolean isMultiSelect()
  {
    return multiSelect;
  }

}
