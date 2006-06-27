package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class SatScanJob
{
  private boolean active;

  private int id, timeaggregationlength, analysistype, modeltype, scanareas,
      timeaggregationunits, jobtype, montecarloreps, observationWindowSize,
      observationWindowUnit;

  private float maxTemporalSize;

  private String name;

  private Date lastrun;

  public static final int ANALYSISTYPE_PURELY_SPATIAL = 1,
      ANALYSISTYPE_PURELY_TEMPORAL = 2,
      ANALYSISTYPE_RETROSPECTIVE_SPACE_TIME = 3,
      ANALYSISTYPE_PROSPECTIVE_SPACE_TIME = 4, ANALYSISTYPE_N_A = 5,
      ANALYSISTYPE_PROSPECTIVE_PURELY_TEMPORAL = 6;

  public static final int MODELTYPE_POISSON = 0, MODELTYPE_BERNOULLI = 1,
      MODELTYPE_SPACE_TIME_PERMUTATION = 2;

  public static final int SCANAREAS_HIGH = 1, SCANAREAS_LOW = 2,
      SCANAREAS_HIGH_OR_LOW = 3;

  public static final int TIMEAGGREGATIONUNITS_NONE = 0,
      TIMEAGGREGATIONUNITS_YEAR = 1, TIMEAGGREGATIONUNITS_MONTH = 2,
      TIMEAGGREGATIONUNITS_DAY = 3;

  public static final int JOBTYPE_DAILY = 1, JOBTYPE_WEEKLY = 2,
      JOBTYPE_MONTHLY = 3, JOBTYPE_YEARLY = 4;

  private static final int OBSERVATIONUNIT_DAYS = Calendar.DAY_OF_YEAR,
      OBSERVATIONUNIT_MONTHS = Calendar.MONTH,
      OBSERVATIONUNIT_YEARS = Calendar.YEAR;

  public Date getLastrun()
  {
    return lastrun;
  }

  public void setLastrun(Date lastrun)
  {
    this.lastrun = lastrun;
  }

  public SatScanJob()
  {
  }

  public SatScanJob(String name, int jobtype, int analysistype, int modeltype)
  {
    this.name = name;
    this.jobtype = jobtype;
    this.analysistype = analysistype;
    this.modeltype = modeltype;
    this.montecarloreps = 999;
    this.scanareas = SCANAREAS_HIGH;
    this.timeaggregationlength = 1;
    this.timeaggregationunits = TIMEAGGREGATIONUNITS_YEAR;
  }

  public int getAnalysistype()
  {
    return analysistype;
  }

  public void setAnalysistype(int analysistype)
  {
    this.analysistype = analysistype;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getJobtype()
  {
    return jobtype;
  }

  public void setJobtype(int jobtype)
  {
    this.jobtype = jobtype;
  }

  public int getModeltype()
  {
    return modeltype;
  }

  public void setModeltype(int modeltype)
  {
    this.modeltype = modeltype;
  }

  public int getMontecarloreps()
  {
    return montecarloreps;
  }

  public void setMontecarloreps(int montecarloreps)
  {
    this.montecarloreps = montecarloreps;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getScanareas()
  {
    return scanareas;
  }

  public void setScanareas(int scanareas)
  {
    this.scanareas = scanareas;
  }

  public int getTimeaggregationunits()
  {
    return timeaggregationunits;
  }

  public void setTimeaggregationunits(int timeaggregationunits)
  {
    switch (timeaggregationunits)
    {
    case TIMEAGGREGATIONUNITS_DAY:
      observationWindowUnit = OBSERVATIONUNIT_DAYS;
      break;
    case TIMEAGGREGATIONUNITS_MONTH:
      observationWindowUnit = OBSERVATIONUNIT_MONTHS;
      break;
    case TIMEAGGREGATIONUNITS_YEAR:
      observationWindowUnit = OBSERVATIONUNIT_YEARS;
      break;
    default:
      break;
    }
    this.timeaggregationunits = timeaggregationunits;
  }

  public int getTimeaggregationlength()
  {
    return timeaggregationlength;
  }

  public void setTimeaggregationlength(int timeaggregationlength)
  {
    this.timeaggregationlength = timeaggregationlength;
  }

  public Vector<Date> calculateScheduleDates(Calendar startDate,
      Calendar endDate)
  {
    Vector<Date> scheduleDates = new Vector<Date>();
    Calendar from = (Calendar) startDate.clone();
    Calendar calLastRun = (Calendar) startDate.clone();
    calLastRun.setTime(lastrun);

    boolean invalidSchedule = false;
    int scheduleIntervall = 0;

    switch (jobtype)
    {
    case JOBTYPE_DAILY:
      scheduleIntervall = Calendar.DAY_OF_YEAR;
      break;
    case JOBTYPE_WEEKLY:
      scheduleIntervall = Calendar.WEEK_OF_YEAR;
      from.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

      // endDate.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
      break;
    case JOBTYPE_MONTHLY:
      from.set(Calendar.DAY_OF_MONTH, from.getMinimum(Calendar.DAY_OF_MONTH));
      scheduleIntervall = Calendar.MONTH;
      break;
    case JOBTYPE_YEARLY:
      from.set(Calendar.DAY_OF_MONTH, from.getMinimum(Calendar.DAY_OF_MONTH));
      from.set(Calendar.MONTH, from.getMinimum(Calendar.MONTH));
      scheduleIntervall = Calendar.YEAR;
      break;
    default:
      invalidSchedule = true;
      break;
    }

    if (!invalidSchedule)
    {
      while (!from.after(calLastRun))
      {
        from.add(scheduleIntervall, 1);
      }

      while (from.compareTo(endDate) <= 0)
      {
        scheduleDates.add(new java.sql.Date(from.getTime().getTime()));
        from.add(scheduleIntervall, 1);
      }
      if (scheduleDates.isEmpty() ||
          ( !DateUtils.isSameDay(scheduleDates.lastElement(), endDate.getTime())
          && !scheduleDates.lastElement().after(endDate.getTime()) 
          && (jobtype == JOBTYPE_DAILY || jobtype == JOBTYPE_WEEKLY ) ))
      {
        scheduleDates.add(new java.sql.Date(endDate.getTime().getTime()));
      }
    }

    return scheduleDates;
  }

  public Date getObservationBegin(Date scheduleDate)
  {
    Calendar startDate = Calendar.getInstance();// (Calendar)
    // scheduleDate.clone();
    startDate.setTime(new java.util.Date(scheduleDate.getTime()));
    startDate.add(observationWindowUnit, -observationWindowSize);

    // SQL BETWEEN includes first date, add one day to get correct window
    // size on SELECT statement
    startDate.add(OBSERVATIONUNIT_DAYS, 1);

    return new Date(startDate.getTime().getTime());
  }

  public Date getObservationEnd(Date scheduleDate)
  {
    return scheduleDate;
  }

  public float getMaxTemporalSize()
  {
    return maxTemporalSize;
  }

  public void setMaxTemporalSize(float maxTemporalSize)
  {
    if (analysistype == ANALYSISTYPE_RETROSPECTIVE_SPACE_TIME)
    {
      observationWindowSize = 1;
      observationWindowUnit = OBSERVATIONUNIT_YEARS;
    }
    else
    {
      observationWindowSize = (int) Math.ceil(maxTemporalSize / 60 * 100);
    }
    this.maxTemporalSize = maxTemporalSize;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }
}
