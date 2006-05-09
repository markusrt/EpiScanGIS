package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import junit.framework.Test;
import junit.framework.TestSuite;

import de.meningococcus.episcangis.db.model.SatScanJob;

/**
 * @author rzxp001 TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class SatScanJobTest extends AbstractTestCase
{
  /**
   *  
   */
  public SatScanJobTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(SatScanJobTest.class);
  }

  public void testSchedule()
  {
    SatScanJob job = new SatScanJob("testjob", SatScanJob.JOBTYPE_WEEKLY,
        SatScanJob.ANALYSISTYPE_PROSPECTIVE_SPACE_TIME,
        SatScanJob.MODELTYPE_POISSON);
    job.setMaxTemporalSize(60);
    job.setTimeaggregationunits(SatScanJob.TIMEAGGREGATIONUNITS_DAY);
    Date scheduleDate = Date.valueOf("2008-03-30");

    assertNotNull("Observation window begin.", job
        .getObservationBegin(scheduleDate));
    assertNotNull("Observation window end.", job
        .getObservationEnd(scheduleDate));
    assertEquals("Observation start date.", "2007-12-22", job
        .getObservationBegin(scheduleDate).toString());
    assertEquals("Observation end date.", "2008-03-30", job.getObservationEnd(
        scheduleDate).toString());

    Calendar from = Calendar.getInstance();
    from.setTimeInMillis(Date.valueOf("2001-03-30").getTime());
    Calendar to = Calendar.getInstance();
    to.setTimeInMillis(Date.valueOf("2001-04-30").getTime());
    Vector<Date> scheduleDates = job.calculateScheduleDates(from, to);
    assertEquals(5, scheduleDates.size());
  }
}
