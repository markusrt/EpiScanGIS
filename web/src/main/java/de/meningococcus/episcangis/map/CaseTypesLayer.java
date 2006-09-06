package de.meningococcus.episcangis.map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.model.CaseType;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class CaseTypesLayer extends MapLayer
{
  private static Log log = LogFactory.getLog(CaseTypesLayer.class);

  CaseTypesLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    ParameterComponent caseTypeSelect = new SelectParameter("CASETYPES",
        "Finetype");

    CaseTypeDAO ctDao = DaoFactory.getDaoFactory().getCaseTypeDAO();
    for (CaseType ct : ctDao.getCaseTypesByCount(-1))
    {
      if (ct.isComplete())
      {
        caseTypeSelect.add(new ParameterValue(ct.getFormattedIdentifier(), "'"
            + ct.getIdentifier() + "'"));
      }
      else
      {
        log
            .debug("Incomplete casetype '" + ct.getIdentifier()
                + "' is ignored.");
      }
    }
    this.addParameter(caseTypeSelect);
    registerReference("fromMonth");
    registerReference("fromYear");
    registerReference("toMonth");
    registerReference("toYear");
    registerReference("fromAge");
    registerReference("toAge");
    //  registerReference("incUAge");
  }
}
