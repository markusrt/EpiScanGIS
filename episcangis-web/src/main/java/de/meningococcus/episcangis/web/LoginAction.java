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
import org.apache.struts.action.RedirectingActionForward;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * TODO only redirect to referer if its needed (i.e. mapbrowser.vm)
 * @author Markus Reinhardt
 * 
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
    ActionForward actionForward = null;
    String forward = FORWARD_ERROR;
    if (request.isUserInRole("nrzm") || request.isUserInRole("public_health"))
    {
      UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
      User user = userDao.getUser(request.getUserPrincipal().getName());
      if (user != null)
      {
        session.setAttribute("user", user);
        log.debug("Is user " + user.getUsername() + " in role nrzm? "
            + user.isInRole("nrzm"));
        
        String referer = request.getHeader("Referer");
        //Redirect to referring site or welcome page if not present
        if (referer != null && referer.length() > 0)
        {
          actionForward = new RedirectingActionForward();
          actionForward.setPath( referer );
        }
        else {
          forward = FORWARD_SUCCESS;
        }
      }
      else
      {
        throw new UserNotFoundException("The user with login name '"
            + request.getUserPrincipal().getName()
            + "' was not found in database.");
      }
    }
    if(actionForward == null){
      actionForward = mapping.findForward(forward);
    }
    return (actionForward);
  }
}
