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
public class CheckboxParameter extends SelectParameter
{
  private static Log log = LogFactory.getLog(CheckboxParameter.class);

  CheckboxParameter(String name, String title, ParameterValue on, ParameterValue off)
  {
    super(name, title);
    add(on);
    add(off);
  }

  public void addValue(ParameterValue value)
  {
    log.warn("Method addValue(Value value) is disabled, use constructor.");
  }

}
