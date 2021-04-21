package de.meningococcus.episcangis.map;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaDAO;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.Area;
import de.meningococcus.episcangis.db.model.AreaType;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class NrzmMap extends AbstractWmsMap
{
  private static Log log = LogFactory.getLog(NrzmMap.class);

  private AreaDAO aDao;

  ParameterComponent fromAge, toAge, areaSelector, includeUnknownAge,
      observationPeriod;

  public NrzmMap(int width, int height) throws MapInitializationException
  {
    super(width, height);
    // Get DAOs for db access
    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    AreaTypeDAO atDao = daoFactory.getAreaTypeDAO();
    ReportedCaseDAO rcDao = daoFactory.getReportedCaseDAO();
    aDao = daoFactory.getAreaDAO();

    // Create AREA SelectParameter. Fill it with all areas, that are stored
    // in db and are active
    areaSelector = new SelectParameter("areaId", "Area");
    for (AreaType at : atDao.getAreaTypes())
    {
      if (at.isActive())
      {
        Collection<Area> parentAreas = aDao.getAreas(at);
        for (AreaType childAt : atDao.getChildAreaTypes(at.getId()))
        {
          StringBuilder sb = new StringBuilder();
          for (int a = 0; a < childAt.getTier(); a++)
          {
            sb.append(' ');
          }
          Collection<Area> childAreas = aDao.getAreas(childAt);
          Iterator<Area> childAreaIt = childAreas.iterator();
          while (childAreaIt.hasNext())
          {
            Area childArea = childAreaIt.next();
            String identifier = childArea.getIdentifier();
            if (identifier.equals("Niedersachsen"))
            {
              childArea.setIdentifier("Bremen/Niedersachsen");
            }
            else if (identifier.equals("Schleswig-Holstein"))
            {
              childArea.setIdentifier("Hamburg/Schleswig-Holstein");
            }
            else if (identifier.equals("Rheinland-Pfalz"))
            {
              childArea.setIdentifier("Rheinland-Pfalz/Saarland");
            }
            else if (identifier.equals("Brandenburg"))
            {
              childArea.setIdentifier("Berlin/Brandenburg");
            }

            if (identifier.equals("Berlin") || identifier.equals("Hamburg")
                || identifier.equals("Saarland") || identifier.equals("Bremen"))
            {
              childAreaIt.remove();
            }
            else
            {
              childArea
                  .setIdentifier(sb.toString() + childArea.getIdentifier());
            }
          }
          parentAreas.addAll(childAreas);
        }

        for (Area area : parentAreas)
        {
          ParameterValue val = new ParameterValue(area.getIdentifier(), String
              .valueOf(area.getId()));
          areaSelector.add(val);
        }
      }
    }
    addMapParameter(areaSelector);

    // Create age range SelectParameters.
    fromAge = new SelectParameter("fromAge", "Age from");
    toAge = new SelectParameter("toAge", "to");
    for (int age = 0; age <= 90; age++)
    {
      fromAge.add(new ParameterValue(String.valueOf(age), String.valueOf(age)));
      ParameterValue toValue = new ParameterValue(String.valueOf(age), String
          .valueOf(age));
      if (age == 90)
      {
        toValue = new ParameterValue("90+", String.valueOf(1000));
      }
      toAge.add(toValue);
    }
    try
    {
      toAge.selectValue("1000");
    }
    catch (InvalidParameterValueException e){}

    // TODO refactor this
    // includeUnknownAge = new CheckboxParameter("incUAge", "include unknown
    // age",
    // new ParameterValue("yes", "-1"), new ParameterValue("no", "-2"));
    // addMapParameter(includeUnknownAge);

    addMapParameter(fromAge);
    addMapParameter(toAge);

    // Get first and last case. Construct PeriodParameter to select this
    // time range. Set from selection on last year, first month and to
    // selection on last year, last case's month;
    if (rcDao.getEarliestCase() != null && rcDao.getLatestCase() != null)
    {
      Calendar from = Calendar.getInstance();
      log.debug("FirstCase: " + rcDao.getEarliestCase().getReportDate());
      from.setTime(rcDao.getEarliestCase().getReportDate());

      Calendar to = Calendar.getInstance();
      to.setTime(rcDao.getLatestCase().getReportDate());

      observationPeriod = new PeriodParameter("observationPeriod", from
          .getTime(), to.getTime(), "Observation period");

      from.set(Calendar.MONTH, Calendar.JANUARY);
      from.set(Calendar.YEAR, to.get(Calendar.YEAR));

      ((PeriodParameter) observationPeriod).setDefaultSelection(from.getTime(),
          to.getTime());
      addMapParameter(observationPeriod);
    }
  }

  public synchronized Collection<String> setParameter(String name, String value)
      throws ParameterNotFoundException, InvalidParameterValueException
  {
    Collection<String> updatedLayers = super.setParameter(name, value);
    if (name.equals(areaSelector.getName()))
    {
      log.debug("AREAID: " + Integer.valueOf(value));
      Area a = aDao.getArea(Integer.valueOf(value));
      setBbox(a.getBoundingBox());
    }
    return updatedLayers;
  }

  protected MapLayerFactory getMapLayerFactory()
  {
    return new NrzmMapLayerFactory();
  }

}
