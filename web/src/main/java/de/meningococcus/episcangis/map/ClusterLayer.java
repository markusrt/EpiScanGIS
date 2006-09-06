package de.meningococcus.episcangis.map;

import java.sql.Date;
import java.util.Vector;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.SatScanJob;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ClusterLayer extends MapLayer
{
  ClusterLayer(String name, String title, boolean hasLegend,
      boolean hasDateSelector, AbstractWmsMap map, int jobId)
  {
    super(name, title, hasLegend, map);
    ParameterComponent clusterDateSelector = new SelectParameter(
        "CLUSTERANALYSISDATE" + jobId, "Date");
    if (isValid())
    {
      SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();
      SatScanJob ssJob = ssDao.getSatScanJob(jobId);
      Vector<Date> analysisDates = new Vector<Date>(ssDao
          .getClusterAnalysisDates(ssJob));
      if (hasDateSelector)
      {
        for (Date date : analysisDates)
        {
          clusterDateSelector.add(new ParameterValue(date.toString(), date.toString()));
        }
        try
        {
          clusterDateSelector.selectValue(analysisDates.lastElement().toString());
        }
        catch (InvalidParameterValueException e)
        {
          e.printStackTrace();
        }
        addParameter(clusterDateSelector);
      }
      else
      {
        Date lastAnalysis = analysisDates.lastElement();
        setTitle(title + " (" + lastAnalysis.toString() + ")");
      }
    }
  }
}
