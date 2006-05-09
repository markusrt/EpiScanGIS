package de.meningococcus.episcangis.map;


import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.model.AreaType;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PopdensityLayer extends MapLayer
{
  PopdensityLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    OLDSelectParameter depthSelector = new OLDSelectParameter("POPDENTIER", "Depth");

    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    for (AreaType at : daoFactory.getAreaTypeDAO().getAreaTypes())
    {
      if (at.isGroup())
      {
        ParameterValue val = new ParameterValue(at.getDescription(), String.valueOf(at.getTier()));
        depthSelector.addValue(val);
      }
    }

    addParameter(depthSelector);
    addParameter(new ReferenceParameter("fromYear"));
  }
}
