package de.meningococcus.episcangis.web;

import java.sql.Date;
import java.sql.Timestamp;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.ReportedCase;

public class GlobalInformation {
	ReportedCaseDAO rcDao;

	public GlobalInformation() {
		rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
	}

	public long getCaseCount() {
		return rcDao.countCases();
	}

	public Date getLatestCaseDate() {
		ReportedCase rCase = rcDao.getLatestCase();
		if (rCase != null) {
			return rCase.getReportDate();
		}
		return null;
	}

	public Date getLastChange() {
		Timestamp lastChange = rcDao.getLastChange();
		if (lastChange != null) {
			return new Date(lastChange.getTime());
		}
		return null;
	}

}
