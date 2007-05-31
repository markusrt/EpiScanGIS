package de.meningococcus.episcangis.map.layer;

import java.util.ArrayList;
import java.util.Collection;
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
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MockSerogroupsLayer extends MapLayer
{
  public MockSerogroupsLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);

    ParameterComponent serogroupSelector = new SelectParameter("SEROGROUPS",
        "Serogroup(s)", true);
    ParameterValue all = new ParameterValue("all", "dummy");
    StringBuilder allvalues = new StringBuilder();
    serogroupSelector.add(all);

    Collection<CaseTypeAttribute> attribs = new ArrayList<CaseTypeAttribute>();
    CaseTypeAttribute c2 = new CaseTypeAttribute();
    c2.setValue("\"B\"");
    attribs.add(c2);
    CaseTypeAttribute c3 = new CaseTypeAttribute();
    c3.setValue("\"C\"");
    attribs.add(c3);
    CaseTypeAttribute c4 = new CaseTypeAttribute();
    c4.setValue("\"Y\"");
    attribs.add(c4);
    CaseTypeAttribute c5 = new CaseTypeAttribute();
    c5.setValue("\"W135\"");
    attribs.add(c5);
    CaseTypeAttribute c6 = new CaseTypeAttribute();
    c6.setValue("\"X\"");
    attribs.add(c6);
    CaseTypeAttribute c7 = new CaseTypeAttribute();
    c7.setValue("\"29E\"");
    attribs.add(c7);
    CaseTypeAttribute c8 = new CaseTypeAttribute();
    c8.setValue("\"Z\"");
    attribs.add(c8);
    CaseTypeAttribute c9 = new CaseTypeAttribute();
    c9.setValue("\"A\"");
    attribs.add(c9);

    for (Iterator i = attribs.iterator(); i.hasNext();)
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
