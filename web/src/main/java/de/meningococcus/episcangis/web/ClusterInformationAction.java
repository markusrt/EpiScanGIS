package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.SatScanCluster;

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
public class ClusterInformationAction extends Action
{
  private static Log log = LogFactory.getLog(ClusterInformationAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_ERROR;
    String target = request.getParameter("target");
    DynaActionForm dynaForm = (DynaActionForm) form;
    SatScanDAO ssDao = ContextAttributes.getSatScanDAO();

    if (dynaForm.get("clusterId") != null)
    {
      SatScanCluster cluster = ssDao.getSatScanCluster((Integer) dynaForm
          .get("clusterId"));
      if (cluster != null)
      {
        request.setAttribute("cluster", cluster);
        if (target != null && target.equals("inline"))
        {
          forward = "inline";
        }
        else
        {
          forward = GlobalSettings.FORWARD_SUCCESS;
        }

      }
    }

    return mapping.findForward(forward);
  }
}
