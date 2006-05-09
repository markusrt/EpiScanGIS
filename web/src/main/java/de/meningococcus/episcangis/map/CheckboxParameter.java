package de.meningococcus.episcangis.map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class CheckboxParameter extends OLDSelectParameter
{
  private static Log log = LogFactory.getLog(CheckboxParameter.class);

  CheckboxParameter(String name, String title, ParameterValue on, ParameterValue off)
  {
    super(name, title);
    values.setSize(2);
    values.set(0, on);
    values.set(1, off);
  }

  public void addValue(ParameterValue value)
  {
    log.warn("Method addValue(Value value) is disabled, use constructor, "
        + "setOnValue or setOffValue.");
  }

  public void setOnValue(ParameterValue on)
  {
    values.set(0, on);
  }

  public void setOffValue(ParameterValue off)
  {
    values.set(1, off);
  }

}
