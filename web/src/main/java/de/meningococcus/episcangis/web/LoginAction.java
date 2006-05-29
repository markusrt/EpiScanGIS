package de.meningococcus.episcangis.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
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
 * 
 * @author Markus Reinhardt
 * 
 */
public class LoginAction extends Action
{
  private static Log log = LogFactory.getLog(LoginAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    HttpSession session = request.getSession();
    String forward = GlobalSettings.FORWARD_SUCCESS;
    
    if (request.isUserInRole("nrzm") || request.isUserInRole("public_health"))
    {
      Locale locale = (Locale)session.getAttribute(Globals.LOCALE_KEY);
      UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
      User user = userDao.getUser(request.getUserPrincipal().getName());

      // invalidate session to clear existing data (map bean,...)
      session.invalidate();
      session = request.getSession(true);
      
      if( locale != null ) {
        session.setAttribute(Globals.LOCALE_KEY, locale);
      }

      if (user != null)
      {
        session.setAttribute("user", user);
      }
      else
      {
        throw new UserNotFoundException("The user with login name '"
            + request.getUserPrincipal().getName()
            + "' was not found in database.");
      }
    }
    
    //  Redirect to referring site or welcome page if not present
    String referer = request.getHeader("Referer");
    if (referer != null && referer.length() > 0
        && referer.contains(GlobalSettings.MAPBROWSER_SITE_URL))
    {
      return new RedirectingActionForward(referer);
    }
    else {
      return mapping.findForward(forward);
    }
  }
}
