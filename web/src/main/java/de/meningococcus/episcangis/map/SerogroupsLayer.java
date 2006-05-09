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
  private static final String DESCRIPTION = "Displays the serogroup of each case within the observation period and age intervall.";

  SerogroupsLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);

    OLDSelectParameter serogroupSelector = new MultiSelectParameter("SEROGROUPS",
        "Serogroup(s)");

    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    CaseTypeAttributeDAO ctaDao = daoFactory.getCaseTypeAttributeDAO();

    ParameterValue all = new ParameterValue("all", "");
    all.setSelected(true);
    StringBuilder allvalues = new StringBuilder();
    serogroupSelector.addValue(all);

    for (Iterator i = ctaDao.selectDistinctCaseTypeAttributesByCount(
        "Serogroups").iterator(); i.hasNext();)
    {
      StringBuilder dbValue = new StringBuilder();
      CaseTypeAttribute cta = (CaseTypeAttribute) i.next();
      if (cta.getValue().length() > 0)
      {
        dbValue.append("'").append(cta.getValue()).append("'");
        ParameterValue val = new ParameterValue(cta.getValue(), dbValue.toString());
        serogroupSelector.addValue(val);
        allvalues.append(val.getValue());
        if (i.hasNext())
        {
          allvalues.append(",");
        }
      }
    }
    all.setValue(allvalues.toString());

    this.setDescription(DESCRIPTION);
    this.addParameter(serogroupSelector);
    this.addParameter(new ReferenceParameter("fromMonth"));
    this.addParameter(new ReferenceParameter("fromYear"));
    this.addParameter(new ReferenceParameter("toMonth"));
    this.addParameter(new ReferenceParameter("toYear"));
    this.addParameter(new ReferenceParameter("fromAge"));
    this.addParameter(new ReferenceParameter("toAge"));
    this.addParameter(new ReferenceParameter("incUAge"));
  }

}
