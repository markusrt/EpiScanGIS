package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

/* ====================================================================
 *   Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class ValidationUtil
{
  private static Log log = LogFactory.getLog(ValidationUtil.class);

  public static boolean validateTwoFields(Object bean, ValidatorAction va,
      Field field, ActionMessages errors, HttpServletRequest request)
  {
    log.debug("validateTwoFields called");
    String value = ValidatorUtils.getValueAsString(bean, field.getProperty());
    String sProperty2 = field.getVarValue("secondProperty");
    String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
    log.debug("(value,secondProperty,value2): " + value + "," + sProperty2
        + "," + value2);
    if (!GenericValidator.isBlankOrNull(value))
    {
      if (!value.equals(value2))
      {
        errors.add(field.getKey(), Resources.getActionMessage(request, va,
            field));
        return false;
      }
    }
    return true;
  }
}
