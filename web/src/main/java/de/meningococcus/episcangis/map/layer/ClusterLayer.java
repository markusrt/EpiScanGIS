package de.meningococcus.episcangis.map.layer;

import java.sql.Date;
import java.util.Vector;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.SatScanJob;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.InvalidParameterValueException;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.SelectParameter;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ClusterLayer extends MapLayer
{
  public ClusterLayer(String name, String title, boolean hasLegend,
      boolean hasDateSelector, AbstractWmsMap map, int jobId)
  {
    super(name, title, hasLegend, map);
    ParameterComponent clusterDateSelector = new SelectParameter(
        "CLUSTERANALYSISDATE" + jobId, "Date");
    SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();
    SatScanJob ssJob = ssDao.getSatScanJob(jobId);
    Vector<Date> analysisDates = new Vector<Date>(ssDao
        .getClusterAnalysisDates(ssJob));
    if (isValid() && analysisDates.size()>0)
    {
      if (hasDateSelector )
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
