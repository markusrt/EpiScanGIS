package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
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
 * TODO only redirect to referer if its needed (i.e. mapbrowser.vm)
 * 
 * @author Markus Reinhardt
 * 
 */
public class ForgotPasswordAction extends Action
{
  private static Log log = LogFactory.getLog(ForgotPasswordAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_ERROR;
    HttpSession session = request.getSession();
    DynaActionForm dynaForm = (DynaActionForm) form;
    ActionMessages messages = new ActionMessages();
    ActionMessages errors = new ActionMessages();
    UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
    String pretendedEmail = dynaForm.getString("email");
    
    for( User user : userDao.getUsers() ) {
      if( user.getEmail().equals(pretendedEmail)) {
        String newPassword = RandomStringUtils.random(8,true,true);
        user.setPassword(newPassword);
        userDao.updateUser(user);
        
        SimpleEmail email = new SimpleEmail();
        email.setHostName(GlobalSettings
            .getConfigurationProperty("mailserver.hostname"));
        email.addTo(pretendedEmail, user.getForename() + " "
            + user.getLastname());
        email.setFrom("noreply@hygiene.uni-wuerzburg.de", "NO reply!" );
        email.setSubject("Zurücksetzung ihres EpiScanGIS Passwortes");
        email
            .setMsg("Sehr geehrte/r EpiScanGIS Anwender/in,\n"
                + "ihre Passwort wurde auf einen neuen Wert gesetzt.\n\n"
                + "Benutzername:\t\t"+ user.getUsername()  + "\n"
                + "Passwort:\t\t" + newPassword + "\n\n"
                + "Dies ist eine automatisch erzeugte Email, bitte antworten Sie nicht darauf.");
        email.send();
        
        ActionMessage msg = new ActionMessage("user.resetPassword.success", pretendedEmail );
        messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
        log.info(msg.toString());
        forward = GlobalSettings.FORWARD_SUCCESS;
        saveMessages(request, messages);
      }
    }
    if (forward.equals(GlobalSettings.FORWARD_ERROR))
    {
      ActionMessage msg = new ActionMessage("user.resetPassword.failure", pretendedEmail );
      errors.add(ActionMessages.GLOBAL_MESSAGE, msg);
      log.error(msg.toString());
      saveErrors(request, errors);
    }
    
    return mapping.findForward(forward);
  }
}
