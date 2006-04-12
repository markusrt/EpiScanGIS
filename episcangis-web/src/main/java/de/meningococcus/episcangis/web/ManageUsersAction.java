package de.meningococcus.episcangis.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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

  private static final String FORWARD_ERROR = "error",
      REGISTER_USER_SUCCESS = "registerUserSuccess";

  public ActionForward register(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException, IllegalAccessException,
      InvocationTargetException
  {
    ActionMessages messages = new ActionMessages();
    String forward = FORWARD_ERROR;
    forward = REGISTER_USER_SUCCESS;
    DynaActionForm dynaForm = (DynaActionForm) form;
    User user = new User();
    BeanUtils.populate(user, dynaForm.getMap());
    if (DaoFactory.getDaoFactory().getUserDAO().createUser(user) < 1)
    {
      messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
          "Could not create user"));
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
