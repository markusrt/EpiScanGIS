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

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class CountCasesByAttributeAction extends Action
{
  private static Log log = LogFactory.getLog(CountCasesByAttributeAction.class);

  private static final String FORWARD_ERROR = "error",
      FORWARD_SUCCESS = "success";

  public ActionForward execute(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    String forward = FORWARD_ERROR;
    DynaActionForm dynaForm = (DynaActionForm) form;

    if (dynaForm.get("areaTier") != null && dynaForm.get("attribute") != null)
    {
      ReportedCaseDAO rcDao = ContextAttributes.getReportedCaseDAO();
      request.setAttribute("cases", rcDao
            .countCasesPerAreaGroupedByAttribute((String) dynaForm
                .get("attribute"), (Integer) dynaForm.get("areaTier")));
      forward = FORWARD_SUCCESS;
    }
    return (mapping.findForward(forward));
  }
}
