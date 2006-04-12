package de.meningococcus.episcangis.map;

import java.util.Vector;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class OLDSelectParameter extends AbstractParameter implements
    MultiValueParameter
{
  protected Vector<ParameterValue> values = new Vector<ParameterValue>();

  public OLDSelectParameter(String name, String title)
  {
    super(name, title);
  }

  public OLDSelectParameter(String name)
  {
    this(name, "");
  }

  public void addValue(ParameterValue newValue)
  {
    if (values != null && !values.contains(newValue))
    {
      values.add(newValue);
      if (newValue.isSelected())
      {
        for (ParameterValue value : values)
        {
          if (value.equals(newValue))
          {
            value.setSelected(true);
          }
          else
          {
            value.setSelected(false);
          }
        }
      }
      else
      {
        if (values.size() == 1)
        {
          newValue.setSelected(true);
        }
      }
    }
  }

  public Vector<ParameterValue> getValues()
  {
    return values;
  }

  public String getValue()
  {
    StringBuilder ret = new StringBuilder(100);
    for (ParameterValue value : values)
    {
      if (value.isSelected())
      {
        ret.append(value.getValue());
        ret.append(",");
      }
    }
    if (ret.length() > 0)
    {
      return ret.substring(0, ret.length() - 1);
    }
    else
    {
      return "";
    }
  }

  public void setValue(String newValue)
  {
    for (ParameterValue value : values)
    {
      if (value.getValue().equals(newValue))
      {
        value.setSelected(true);
      }
      else
      {
        value.setSelected(false);
      }
    }
  }
}
