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
  private static final String DESCRIPTION = "Displays all cases of the selected finetype within the observation period and age intervall. Notation: '{serogroup} : P1.{PorA} : F{FetA}'.";

  private static Log log = LogFactory.getLog(CaseTypesLayer.class);

  CaseTypesLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    OLDSelectParameter caseTypeSelect = new OLDSelectParameter("CASETYPES",
        "Finetype");

    CaseTypeDAO ctDao = DaoFactory.getDaoFactory().getCaseTypeDAO();
    for (CaseType ct : ctDao.getCaseTypesByCount(-1))
    {
      if (ct.isComplete())
      {
        caseTypeSelect.addValue(new ParameterValue(ct.getFormattedIdentifier(), "'"
            + ct.getIdentifier() + "'"));
      }
      else
      {
        log
            .debug("Incomplete casetype '" + ct.getIdentifier()
                + "' is ignored.");
      }
    }
    this.setDescription(DESCRIPTION);
    this.addParameter(caseTypeSelect);
    this.addParameter(new ReferenceParameter("fromMonth"));
    this.addParameter(new ReferenceParameter("fromYear"));
    this.addParameter(new ReferenceParameter("toMonth"));
    this.addParameter(new ReferenceParameter("toYear"));
    this.addParameter(new ReferenceParameter("fromAge"));
    this.addParameter(new ReferenceParameter("toAge"));
    this.addParameter(new ReferenceParameter("incUAge"));
  }
}
