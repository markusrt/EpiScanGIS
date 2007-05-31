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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import de.meningococcus.episcangis.db.model.User;
import de.meningococcus.episcangis.map.AbstractWmsMap;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MoveMapAction extends Action
{
  private static Log log = LogFactory.getLog(MoveMapAction.class);

  private static final String FORWARD_ERROR = "error",
      FORWARD_SUCCESS = "success";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    HttpSession session = request.getSession();
    String forward = FORWARD_ERROR;
    ActionMessages messages = new ActionMessages();
    DynaActionForm dynaForm = (DynaActionForm) form;

    if (dynaForm.get("xoffset") != null && dynaForm.get("yoffset") != null)
    {
      AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
          "map");
      User user = (User) session.getAttribute("user");

      if (map != null
          && (user != null && (user.isInRole("nrzm") || user
              .isInRole("public_health"))))
      {
        map.move((Double) dynaForm.get("xoffset"), (Double) dynaForm
            .get("yoffset"));
        forward = FORWARD_SUCCESS;
      }
      else
      {
        ActionMessage msg = new ActionMessage("error.beannotfound", "map");
        messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
        log.error(msg.toString());
      }
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
