package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

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
    String forward = FORWARD_ERROR;
    ActionMessages messages = new ActionMessages();
    MoveMapFormBean move = (MoveMapFormBean) form;
    AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
        "map");
    if (map != null)
    {
      map.move(move.getXoffset(), move.getYoffset());
      forward = FORWARD_SUCCESS;
    }
    else
    {
      ActionMessage msg = new ActionMessage("error.beannotfound", "map");
      messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
      log.error(msg.toString());
    }
    saveMessages(request, messages);
    return (mapping.findForward(forward));
  }
}
