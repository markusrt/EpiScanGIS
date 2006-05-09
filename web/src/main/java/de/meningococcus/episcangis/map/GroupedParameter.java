package de.meningococcus.episcangis.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class GroupedParameter extends AbstractParameter
{
  private Map<String, AbstractParameter> parameters = new HashMap<String, AbstractParameter>();

  public GroupedParameter(String name, String title)
  {
    super(name, title);
  }

  GroupedParameter(String name)
  {
    this(name, "");
  }

  public void addParameter(AbstractParameter p)
  {
    if (!p.getClass().equals(GroupedParameter.class))
    {
      if (parameters.get(p.getName()) == null)
      {
        parameters.put(p.getName(), p);
      }
    }
  }

  public Vector<AbstractParameter> getParameters()
  {
    return new Vector<AbstractParameter>(parameters.values());
  }

  public AbstractParameter getNamedParameter(String name)
  {
    return parameters.get(name);
  }
}
