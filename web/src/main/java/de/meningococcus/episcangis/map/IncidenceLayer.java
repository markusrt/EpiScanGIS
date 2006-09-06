package de.meningococcus.episcangis.map;


import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.model.AreaType;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class IncidenceLayer extends MapLayer
{
  IncidenceLayer(String name, String title, boolean hasLegend,
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
