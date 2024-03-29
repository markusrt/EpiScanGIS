package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.exporter.PrintExporter;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PrintMapAction extends Action
{
  private static Log log = LogFactory.getLog(PrintMapAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_ERROR;

    AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
        "map");
    if (map != null)
    {
      PrintExporter exporter = new PrintExporter();
      map.export(exporter);
      request.setAttribute("printMap", exporter);
      forward = GlobalSettings.FORWARD_SUCCESS;
    }
    else
    {
      throw new SessionBeanNotFoundException(
          "The session bean 'map' was not found.");
    }
    return (mapping.findForward(forward));
  }
}
