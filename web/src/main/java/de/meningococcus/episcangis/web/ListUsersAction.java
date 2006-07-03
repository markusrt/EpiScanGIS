package de.meningococcus.episcangis.web;

import java.util.Collection;

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
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */
public class ListUsersAction extends Action
{
  private static Log log = LogFactory.getLog(ListUsersAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_DENIED;
    HttpSession session = request.getSession();
    
    UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
    Collection<User> allUsers = userDao.getUsers();
    session.setAttribute("allUsers", allUsers);
    Collection<String> allRoles = userDao.getRoles();
    session.setAttribute("allRoles", allRoles);
    forward = GlobalSettings.FORWARD_SUCCESS;

    return (mapping.findForward(forward));
  }
}
