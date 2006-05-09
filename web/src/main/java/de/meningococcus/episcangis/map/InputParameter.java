package de.meningococcus.episcangis.map;

import java.util.Vector;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class InputParameter extends AbstractParameter implements ValueParameter
{
  private ParameterValue value;

  InputParameter(String name)
  {
    super(name);
    this.value = new ParameterValue("", "");
  }

  public void setValue(String newVal)
  {
    this.value.setValue(newVal);
  }

  public String getValue()
  {
    return this.value.getValue();
  }

  public Vector<ParameterValue> getValues()
  {
    Vector<ParameterValue> values = new Vector<ParameterValue>(1);
    values.add(value);
    return values;
  }
}
