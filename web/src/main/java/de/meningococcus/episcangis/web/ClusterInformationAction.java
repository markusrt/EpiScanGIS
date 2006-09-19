package de.meningococcus.episcangis.web;

import java.util.Collection;

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
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanCluster;
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
public class ClusterInformationAction extends Action
{
  private static Log log = LogFactory.getLog(ClusterInformationAction.class);

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = GlobalSettings.FORWARD_ERROR;
    DynaActionForm dynaForm = (DynaActionForm) form;
    SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();

    if (dynaForm.get("clusterId") != null)
    {
      SatScanCluster cluster = ssDao.getSatScanCluster((Integer) dynaForm
          .get("clusterId"));
      if (cluster != null)
      {
        request.setAttribute("cluster", cluster);
        forward = GlobalSettings.FORWARD_SUCCESS;
      }
    }

    return mapping.findForward(forward);
  }
}
