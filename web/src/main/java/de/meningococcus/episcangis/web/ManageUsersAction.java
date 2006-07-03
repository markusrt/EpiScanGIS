package de.meningococcus.episcangis.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.model.User;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ManageUsersAction extends DispatchAction
{
  private static Log log = LogFactory.getLog(ManageUsersAction.class);

  public ActionForward register(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException, IllegalAccessException,
      InvocationTargetException
  {
    ActionMessages messages = new ActionMessages();
    String forward = GlobalSettings.FORWARD_ERROR;
    
    DynaActionForm dynaForm = (DynaActionForm) form;
    User user = new User();
    BeanUtils.populate(user, dynaForm.getMap());
    if (DaoFactory.getDaoFactory().getUserDAO().createUser(user) < 1)
    {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "Could not create user: " + ConvertUtils.convert(user) ));
    }
    else {
      forward = GlobalSettings.FORWARD_SUCCESS;
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
