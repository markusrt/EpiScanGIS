package de.meningococcus.episcangis.web;

import java.util.Collection;

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
import de.meningococcus.episcangis.map.InvalidParameterValueException;
import de.meningococcus.episcangis.map.ParameterNotFoundException;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SetParameterAction extends Action
{
  private static Log log = LogFactory.getLog(SetParameterAction.class);

  private static final String FORWARD_ERROR = "error",
      FORWARD_SUCCESS = "success";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = FORWARD_ERROR;
    ActionMessages messages = new ActionMessages();
    SetParameterFormBean parameter = (SetParameterFormBean) form;
    AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
        "map");
    if (map != null)
    {
      try
      {
        Collection<String> updatedLayers = map.setParameter(
            parameter.getName(), parameter.getValue());
        request.setAttribute("updatedLayers", updatedLayers);
        forward = FORWARD_SUCCESS;
      }
      catch (ParameterNotFoundException e)
      {
        ActionMessage msg = new ActionMessage("error.parameternotfound",
            parameter.getName());
        messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
        log.error(msg.toString());
      }
      catch (InvalidParameterValueException e)
      {
        ActionMessage msg = new ActionMessage("error.invalidparameter",
            parameter.getName(), parameter.getValue());
        messages.add(ActionMessages.GLOBAL_MESSAGE, msg);
        log.error(msg.toString());
      }

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
