package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    String referer = request.getHeader("Referer");
    
    session.invalidate();
    session = request.getSession(true);

    // Redirect to referring site or welcome page if not present
    if (referer != null && referer.length() > 0
        && referer.contains(GlobalSettings.MAPBROWSER_SITE_URL))
    {
      forward = GlobalSettings.FORWARD_MAPBROWSER;
    }
    return mapping.findForward(forward);
  }
}