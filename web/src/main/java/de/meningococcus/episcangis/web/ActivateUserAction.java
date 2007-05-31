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
 * @struts.action
 *    name="activateUser"
 *    path="/ActivateUser"
 *    scope="request"
 *    validate="false"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/ListUsers.do"
 */
public class ActivateUserAction extends Action
{
  private static Log log = LogFactory.getLog(ActivateUserAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_DENIED;
    HttpSession session = request.getSession();
    DynaActionForm dynaForm = (DynaActionForm) form;
    ActionMessages messages = new ActionMessages();

    User currentUser = (User) session.getAttribute("user");
    String username = (String) dynaForm.get("username");
    String role = (String) dynaForm.get("role");
    UserDAO userDao = ContextAttributes.getUserDAO();
    User userToChange = userDao.getUser(username);

    if (currentUser != null && currentUser.isInRole("admin")
        && (role.equals("public_health") || role.equals("nrzm"))
        && !userToChange.isInRole(role))
    {

      userToChange.addRole(role);
      userDao.updateUser(userToChange);
      SimpleEmail email = new SimpleEmail();
      email.setHostName(GlobalSettings
          .getConfigurationProperty("mailserver.hostname"));
      email.addTo(userToChange.getEmail(), userToChange.getForename() + " "
          + userToChange.getLastname());
      email.setFrom(currentUser.getEmail(), currentUser.getForename() + " "
          + currentUser.getLastname());
      email.addBcc(currentUser.getEmail(), currentUser.getForename() + " "
          + currentUser.getLastname());
      email.setSubject("Freischaltung des erweiterten Zugangs zu EpiScanGIS");

      email
          .setMsg("Sehr geehrte/r EpiScanGIS Anwender/in,\n"
              + "wir haben Sie für den erweiterten Zugang zu EpiScanGIS freigeschalten.\n\n"
              + "Benutzername:\t\t"
              + userToChange.getUsername()
              + "\n"
              + "Benutzerrolle/n:\t\t"
              + userToChange.getRoles()
              + "\n"
              + "Passwort:\t\t****** (von Ihnen bei der Registrierung angegeben)"
              + "\n\n"
              + "Sie haben damit die Möglichkeit in Detailbereiche (Bundesländer) der Karte zu zoomen und die Ergebnisse der letzten prospektiven Clusteranalyse zu sehen.\n\n"
              + "Bitte teilen Sie uns mit, wenn es Sie beim Anmelden oder beim Ausführen des Kartenbetrachtern Probleme haben. Wir arbeiten ständig an der Verbesserung des Systems.\n\n"
              + "Mit freundlichen Grüßen,\n\n" + currentUser.getForename()
              + " " + currentUser.getLastname());
      email.send();

      ActionMessage msg = new ActionMessage("user.activation.success", email
          .getSentDate(), role, userToChange.getUsername(), userToChange
          .getEmail());
      messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
      log.info(msg.toString());
      forward = GlobalSettings.FORWARD_SUCCESS;
    }
    else if (currentUser != null && currentUser.isInRole("admin"))
    {
      String error = "Activation of user account '" + username + "' for role '"
          + role + "' failed. Your username is '" + currentUser.getUsername()
          + "' your security role/s is/are '" + currentUser.getRoles() + "'";
      log.warn(error);
      throw new ManageUsersException(error);
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
