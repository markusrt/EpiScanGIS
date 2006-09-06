package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;

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
    ReportedCaseDAO rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
    CountCasesByAttributeFormBean countCasesForm = (CountCasesByAttributeFormBean) form;
    if(countCasesForm != null ){
      request.setAttribute("cases", rcDao.countCasesPerAreaGroupedByAttribute(
      countCasesForm.getAttribute(), countCasesForm.getAreaTier()));
      forward = FORWARD_SUCCESS;
    }
    return (mapping.findForward(forward));
  }
}
