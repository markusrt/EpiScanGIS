package de.meningococcus.episcangis.web;

import java.sql.Date;

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

}