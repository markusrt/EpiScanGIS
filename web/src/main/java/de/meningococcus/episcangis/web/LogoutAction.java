package de.meningococcus.episcangis.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RedirectingActionForward;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * TODO only redirect to referer if its needed (i.e. mapbrowser.vm)
 * 
 * @author mreinhardt
 * 
 */
public class LogoutAction extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_INDEX;
    HttpSession session = request.getSession();  
    Locale locale = (Locale)session.getAttribute(Globals.LOCALE_KEY);
    
    session.invalidate();
    session = request.getSession();

    if( locale != null ) {
      session.setAttribute(Globals.LOCALE_KEY, locale);
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
