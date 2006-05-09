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
  private static final String DESCRIPTION = "Displays the yearly incidence of all cases (if applicable with a particular serogroup) within the observation period currently selected, irrespective of age group and/or fine type. The value gets linear inter-/extrapolated depending on the period. Depth sets the area resolution to calculate incidence for.";

  IncidenceLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);

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

    this.setDescription(DESCRIPTION);

    this.addParameter(tier);
    this.addParameter(new ReferenceParameter("fromMonth"));
    this.addParameter(new ReferenceParameter("fromYear"));
    this.addParameter(new ReferenceParameter("toMonth"));
    this.addParameter(new ReferenceParameter("toYear"));
    this.addParameter(new ReferenceParameter("SEROGROUPS"));
  }
}
