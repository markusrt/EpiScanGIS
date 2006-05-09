package de.meningococcus.episcangis.map;

import java.util.Vector;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public interface MultiValueParameter extends ValueParameter
{
  public void addValue(ParameterValue val);

  public Vector<ParameterValue> getValues();
}
