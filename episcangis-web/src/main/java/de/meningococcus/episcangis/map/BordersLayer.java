package de.meningococcus.episcangis.map;


import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.model.AreaType;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class BordersLayer extends MapLayer
{
  BordersLayer(String name, String title, boolean hasLegend, AbstractWmsMap map)
  {
    super(name, title, hasLegend, map);
    OLDSelectParameter depth = new OLDSelectParameter("BORDERTIER", "Depth");
    AreaTypeDAO atDao = DaoFactory.getDaoFactory().getAreaTypeDAO();
    for (AreaType at : atDao.getAreaTypes())
    {
      if (at.isGroup())
      {
        ParameterValue val = new ParameterValue(at.getDescription(), String.valueOf(at.getTier()));
        depth.addValue(val);
      }
    }

    this.addParameter(depth);
  }

}
