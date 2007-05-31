package de.meningococcus.episcangis.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.LayerState;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ToggleLayerStateAction extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_ERROR;
    DynaActionForm dynaForm = (DynaActionForm) form;

    if (dynaForm.get("layer") != null && dynaForm.get("active") != null)
    {
      AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
          "map");
      if (map != null)
      {
        Collection<LayerState> layerstate = map.toggleLayerState(
            (String) dynaForm.get("layer"), (Boolean) dynaForm.get("active"));
        request.setAttribute("layerstates", layerstate);
        forward = GlobalSettings.FORWARD_SUCCESS;
      }
      else
      {
        throw new SessionBeanNotFoundException("map");
      }
    }
    return (mapping.findForward(forward));
  }
}
