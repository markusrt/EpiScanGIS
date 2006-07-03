package de.meningococcus.episcangis.satscan;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.CaseType;
import de.meningococcus.episcangis.db.model.ReportedCase;
import de.meningococcus.episcangis.db.model.SatScanExecution;
import de.meningococcus.episcangis.db.model.SatScanJob;

public class SatScanJobScheduler
{
  private static Log log = LogFactory.getLog(SatScanJobScheduler.class);

  private SatScanJob jobToSchedule;

  private boolean scheduled = false;

  private Calendar initializationDate;

  public SatScanJobScheduler(SatScanJob job)
  {
    jobToSchedule = job;
    initializationDate = Calendar.getInstance();
  }

  public void schedule()
  {
    if (!scheduled && jobToSchedule.isActive())
    {
      ReportedCaseDAO rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
      CaseTypeDAO ctDao = DaoFactory.getDaoFactory().getCaseTypeDAO();
      SatScanDAO ssDao = DaoFactory.getDaoFactory().getSatScanDAO();
      if (rcDao.countCases() <= 0)
      {
        log
            .error("Couldn't find any cases in database, job scheduling aborted.");
      }
      else
      {
        FastDateFormat fdf = FastDateFormat.getInstance();
        ReportedCase firstCase, lastCase;
        firstCase = rcDao.getEarliestCase();
        lastCase = rcDao.getLatestCase();

        log.debug("Checking job " + jobToSchedule.getName());

        Calendar startDate = (Calendar) initializationDate.clone();
        Calendar endDate = (Calendar) initializationDate.clone();
        if (jobToSchedule.getLastrun() == null)
        {
          log.debug("Job's first run ever");
          startDate.setTime(firstCase.getReportDate());
        }
        else
        {
          startDate.setTime(jobToSchedule.getLastrun());
        }
        //endDate.setTime(lastCase.getReportDate());

        log.debug("Searching executions from " + fdf.format(startDate) + " to "
            + fdf.format(endDate));

        Vector<Date> scheduleDates = jobToSchedule.calculateScheduleDates(
            startDate, endDate);

        for (Date scheduleDate : scheduleDates)
        {
          int exeCount = 0;
          Collection<CaseType> caseTypes = ctDao.getCaseTypes(jobToSchedule
              .getObservationBegin(scheduleDate), jobToSchedule
              .getObservationEnd(scheduleDate));
          log.debug("Scheduling Executions for " + fdf.format(scheduleDate));
          log.debug("Searching Casetypes from "
              + jobToSchedule.getObservationBegin(scheduleDate) + " to "
              + jobToSchedule.getObservationEnd(scheduleDate));
          for (CaseType ct : caseTypes)
          {
            if (ct.isComplete())
            {
              log.debug(ct.getIdentifier());
              SatScanExecution exe = new SatScanExecution(ct.getId(),
                  jobToSchedule, scheduleDate);
              exeCount += ssDao.createSatScanExecution(exe);
            }
          }

          jobToSchedule.setLastrun(scheduleDate);
          ssDao.updateSatScanJob(jobToSchedule);

          log.info("Scheduled " + exeCount + " SatScan Executions for "
              + jobToSchedule.getName());
        }
      }
    }
    else if (!jobToSchedule.isActive())
    {
      log.info("The job '" + jobToSchedule.getName() + "' is disabled.");
    }
  }
}
