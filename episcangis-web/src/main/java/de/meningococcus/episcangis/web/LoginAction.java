package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class LoginAction extends Action
{
  private static Log log = LogFactory.getLog(LoginAction.class);

  private static final String FORWARD_ERROR = "error",
      FORWARD_SUCCESS = "welcome";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    HttpSession session = request.getSession();
    String forward = FORWARD_ERROR;
    if (request.isUserInRole("nrzm") || request.isUserInRole("public_health"))
    {
      UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
      User user = userDao.getUser(request.getUserPrincipal().getName());
      if (user != null)
      {
        session.setAttribute("user", user);
        forward = FORWARD_SUCCESS;
      }
      else
      {
        throw new UserNotFoundException("The user with login name '"
            + request.getUserPrincipal().getName()
            + "' was not found in database.");
      }
    }
    return (mapping.findForward(forward));
  }
}
