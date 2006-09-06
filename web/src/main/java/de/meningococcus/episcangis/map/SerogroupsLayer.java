package de.meningococcus.episcangis.map;

import java.util.Iterator;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO;
import de.meningococcus.episcangis.db.model.CaseTypeAttribute;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SerogroupsLayer extends MapLayer
{
  SerogroupsLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);

    ParameterComponent serogroupSelector = new SelectParameter("SEROGROUPS",
        "Serogroup(s)", true);

    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    CaseTypeAttributeDAO ctaDao = daoFactory.getCaseTypeAttributeDAO();

    ParameterValue all = new ParameterValue("all", "");
    StringBuilder allvalues = new StringBuilder();
    serogroupSelector.add(all);

    for (Iterator i = ctaDao.selectDistinctCaseTypeAttributesByCount(
        "Serogroups").iterator(); i.hasNext();)
    {
      StringBuilder dbValue = new StringBuilder();
      CaseTypeAttribute cta = (CaseTypeAttribute) i.next();
      if (cta.getValue().length() > 0)
      {
        dbValue.append("\"").append(cta.getValue()).append("\"");
        ParameterComponent val = new ParameterValue(cta.getValue(), dbValue.toString());
        serogroupSelector.add(val);
        allvalues.append(val.getValue());
        if (i.hasNext())
        {
          allvalues.append(",");
        }
      }
    }
    all.setValue(allvalues.toString());

    this.addParameter(serogroupSelector);
    registerReference("fromMonth");
    registerReference("fromYear");
    registerReference("toMonth");
    registerReference("toYear");
    registerReference("fromAge");
    registerReference("toAge");
    //registerReference("incUAge");
  }

}
