package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.Resources;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;

public class StrutsValidator
{

  public static boolean validateUsername(Object bean, ValidatorAction va,
      Field field, ActionMessages errors, Validator validator,
      HttpServletRequest request)
  {
    String value = evaluateBean(bean, field);
    if (!GenericValidator.isBlankOrNull(value))
    {
      try
      {
        UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
        if (userDao.getUser(value) != null)
        {
          errors.add(field.getKey(), Resources.getActionMessage(validator,
              request, va, field));
          return false;
        }
      }
      catch (Exception e)
      {
        errors.add(field.getKey(), Resources.getActionMessage(validator,
            request, va, field));
        return false;
      }
    }
    return true;
  }
  
  /**
   * @param bean
   * @param field
   * @return
   */
  private static String evaluateBean(Object bean, Field field) {
      String value;

      if (isString(bean)) {
          value = (String) bean;
      } else {
          value = ValidatorUtils.getValueAsString(bean, field.getProperty());
      }

      return value;
  }
  
  /**
   * Return <code>true</code> if the specified object is a String or a
   * <code>null</code> value.
   *
   * @param o Object to be tested
   * @return The string value
   */
  protected static boolean isString(Object o) {
      return (o == null) ? true : String.class.isInstance(o);
  }


}
