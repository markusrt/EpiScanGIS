package de.meningococcus.episcangis.map.layer;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.model.AreaType;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.SelectParameter;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PopdensityLayer extends MapLayer
{
  public PopdensityLayer(String name, String title, boolean hasLegend,
      AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    setOpaque(true);

    ParameterComponent depthSelector = new SelectParameter("POPDENTIER",
        "Depth");

    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    for (AreaType at : daoFactory.getAreaTypeDAO().getAreaTypes())
    {
      if (at.isGroup())
      {
        depthSelector.add(new ParameterValue(at.getDescription(), String
            .valueOf(at.getTier())));
      }
    }

    addParameter(depthSelector);
    registerReference("fromYear");
  }
}
