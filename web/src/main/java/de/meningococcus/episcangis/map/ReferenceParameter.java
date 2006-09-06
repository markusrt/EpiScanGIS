package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ReferenceParameter extends ParameterReference
{
  protected ReferenceParameter(String name)
  {
    super(name, new ParameterComposite("j"));
  }
}
