package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class LogoutAction extends Action
{
  private static final String FORWARD_ERROR = "error",
      FORWARD_SUCCESS = "welcome";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = FORWARD_SUCCESS;
    HttpSession session = request.getSession();
    session.invalidate();
    session = request.getSession(true);
    return (mapping.findForward(forward));
  }
}
