package de.meningococcus.episcangis.map;

import java.sql.Date;
import java.util.ResourceBundle;
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
      AbstractWmsMap map, int jobId)
  {
    super(name, title, hasLegend, map);
    OLDSelectParameter clusterDateSelector = new OLDSelectParameter(
        "CLUSTERANALYSISDATE" + jobId, "Date");
    if (isValid())
    {
      ResourceBundle messages;
      messages = ResourceBundle.getBundle("MessageResources");

      setDescription(messages.getString(name + ".description"));
      SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();
      SatScanJob ssJob = ssDao.getSatScanJob(jobId);
      Vector<Date> analysisDates = new Vector<Date>(ssDao
          .getClusterAnalysisDates(ssDao.getSatScanJob(jobId)));
      if (ssJob.getAnalysistype() == SatScanJob.ANALYSISTYPE_RETROSPECTIVE_SPACE_TIME)
      {
        for (Date date : analysisDates)
        {
          ((OLDSelectParameter) clusterDateSelector).addValue(new ParameterValue(date
              .toString(), date.toString()));
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
