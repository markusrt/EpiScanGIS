package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.SimpleEmail;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @struts.action name="activateUser" path="/DeleteUser" scope="request"
 *                validate="false" roles="admin"
 * 
 * @struts.action-forward name="success" path="/ListUsers.do"
 */
public class DeleteUserAction extends Action
{
  private static Log log = LogFactory.getLog(DeleteUserAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    HttpSession session = request.getSession();
    String forward = GlobalSettings.FORWARD_ERROR;
    DynaActionForm dynaForm = (DynaActionForm) form;
    ActionMessages messages = new ActionMessages();

    String username = (String) dynaForm.get("username");
    UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
    User userToChange = userDao.getUser(username);

    User currentUser = (User) session.getAttribute("user");

    if (userToChange != null)
    {
      userDao.deleteUser(userToChange);
      ActionMessage msg = new ActionMessage("user.deletion.success",
          userToChange.getUsername());
      messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
      log.info(msg.toString());
      forward = GlobalSettings.FORWARD_SUCCESS;
    }
    else if (userToChange == null)
    {
      String error = "Deletion of user account '" + username
          + " failed. The user was not found.";
      if (currentUser != null)
      {
        log.warn(currentUser.getUsername()
            + "'s attempt to delete a user account failed:");
      }
      log.warn(error);
      throw new ManageUsersException(error);
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
