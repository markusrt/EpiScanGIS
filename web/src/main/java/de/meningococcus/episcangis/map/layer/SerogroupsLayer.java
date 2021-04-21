package de.meningococcus.episcangis.map.layer;

import java.util.Iterator;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO;
import de.meningococcus.episcangis.db.model.CaseTypeAttribute;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.InvalidParameterValueException;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.SelectParameter;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SerogroupsLayer extends MapLayer
{
  public SerogroupsLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);

    ParameterComponent serogroupSelector = new SelectParameter("SEROGROUPS",
        "Serogroup(s)", true);

    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    CaseTypeAttributeDAO ctaDao = daoFactory.getCaseTypeAttributeDAO();

    ParameterValue all = new ParameterValue("all", "dummy");
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
    try
    {
      serogroupSelector.selectValue(all.getValue());
    }
    catch (InvalidParameterValueException e)
    {
      e.printStackTrace();
    }
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
