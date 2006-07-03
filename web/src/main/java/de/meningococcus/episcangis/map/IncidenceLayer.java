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

    OLDSelectParameter tier = new OLDSelectParameter("INCITIER", "Depth");

    AreaTypeDAO atDao = DaoFactory.getDaoFactory().getAreaTypeDAO();
    for (AreaType at : atDao.getAreaTypes())
    {
      if (at.isGroup())
      {
        ParameterValue val = new ParameterValue(at.getDescription(), String.valueOf(at.getTier()));
        tier.addValue(val);
      }
    }

    this.addParameter(tier);
    this.addParameter(new ReferenceParameter("fromMonth"));
    this.addParameter(new ReferenceParameter("fromYear"));
    this.addParameter(new ReferenceParameter("toMonth"));
    this.addParameter(new ReferenceParameter("toYear"));
    this.addParameter(new ReferenceParameter("SEROGROUPS"));
  }
}
