package de.meningococcus.episcangis.map;

import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PublicMap extends AbstractWmsMap
{
  private static Log log = LogFactory.getLog(PublicMap.class);

  ParameterComponent fromAge, toAge, includeUnknownAge, observationPeriod, areaSelector;

  public PublicMap(int width, int height) throws MapInitializationException
  {
    super(width, height);
    // Get DAOs for db access
    DaoFactory daoFactory = DaoFactory.getDaoFactory();
    AreaTypeDAO atDao = daoFactory.getAreaTypeDAO();
    ReportedCaseDAO rcDao = daoFactory.getReportedCaseDAO();

    areaSelector = new ParameterComposite("areaId", "Area");
    areaSelector.add(new ParameterValue("Germany", "Germany"));
    addMapParameter(areaSelector);
    // Create age range SelectParameters.
    fromAge = new SelectParameter("fromAge", "Age from");
    toAge = new SelectParameter("toAge", "to");
    for (int age = 0; age <= 90; age++)
    {
      fromAge.add(new ParameterValue(String
          .valueOf(age), String.valueOf(age)));
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

    // TODO refactor
    // includeUnknownAge = new CheckboxParameter("incUAge", "include unknown
    // age",
    // new ParameterValue("yes", "-1"), new ParameterValue("no", "-2"));
    // addMapParameter(includeUnknownAge);
    addMapParameter(fromAge);
    addMapParameter(toAge);

    // Get first and last case. Construct PeriodParameter to select this
    // time range. Set from selection on last year, first month and to
    // selection on last year, last case's month;
    Calendar from = Calendar.getInstance();
    log.debug("FirstCase: " + rcDao.getEarliestCase().getReportDate());
    from.setTime(rcDao.getEarliestCase().getReportDate());

    Calendar to = Calendar.getInstance();
    to.setTime(rcDao.getLatestCase().getReportDate());

    observationPeriod = new PeriodParameter("observationPeriod",
        from.getTime(), to.getTime(), "Observation period");

    from.set(Calendar.MONTH, Calendar.JANUARY);
    from.set(Calendar.YEAR, to.get(Calendar.YEAR));

    ((PeriodParameter) observationPeriod).setDefaultSelection(from.getTime(),
        to.getTime());
    addMapParameter(observationPeriod);
  }

  public synchronized Collection<String> setParameter(String name, String value)
      throws ParameterNotFoundException, InvalidParameterValueException
  {
    Collection<String> updatedLayers = super.setParameter(name, value);
    return updatedLayers;
  }

  protected MapLayerFactory getMapLayerFactory()
  {
    return new PublicMapLayerFactory();
  }

}
