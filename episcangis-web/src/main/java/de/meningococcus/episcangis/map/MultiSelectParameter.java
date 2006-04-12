package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MultiSelectParameter extends OLDSelectParameter
{
  public MultiSelectParameter(String name, String title)
  {
    super(name, title);
  }

  public MultiSelectParameter(String name)
  {
    this(name, "");
  }

  public void setValue(String val)
  {
    super.setValue(val);
    if (!this.getValue().equals(val))
    {
      String[] stringValues = val.split(",");
      for (int a = 0; a < stringValues.length; a++)
      {
        for (ParameterValue value : values)
        {
          if (value.getValue().equals(stringValues[a]))
          {
            value.setSelected(true);
          }
          else if (a == 0)
          {
            value.setSelected(false);
          }
        }
      }
    }
  }
}
