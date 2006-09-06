package de.meningococcus.episcangis.map.layer;


import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.model.AreaType;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.SelectParameter;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class IncidenceLayer extends MapLayer
{
  public IncidenceLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    setOpaque(true);

    ParameterComponent tier = new SelectParameter("INCITIER", "Depth");

    AreaTypeDAO atDao = DaoFactory.getDaoFactory().getAreaTypeDAO();
    for (AreaType at : atDao.getAreaTypes())
    {
      if (at.isGroup())
      {
        ParameterComponent val = new ParameterValue(at.getDescription(), String.valueOf(at.getTier()));
        tier.add(val);
      }
    }

    this.addParameter(tier);
    registerReference("fromMonth");
    registerReference("fromYear");
    registerReference("toMonth");
    registerReference("toYear");
    registerReference("SEROGROUPS");
  }
}
