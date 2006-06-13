package de.meningococcus.episcangis.web;

import java.sql.Date;
import java.sql.Timestamp;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;

public class GlobalInformation
{
  ReportedCaseDAO rcDao;
  public GlobalInformation() {
    rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
  }
  
  public long getCaseCount() {
    return rcDao.countCases();
  }
  
  public Date getLatestCaseDate() {
    return rcDao.getLatestCase().getReportDate();
  }
  
  public Date getLastChange() {
    return new Date(rcDao.getLastChange().getTime());
  }

}
