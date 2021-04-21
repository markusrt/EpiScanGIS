package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
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
//    SatScanJob job = new SatScanJob("testjob", SatScanJob.JOBTYPE_WEEKLY,
//        SatScanJob.ANALYSISTYPE_PROSPECTIVE_SPACE_TIME,
//        SatScanJob.MODELTYPE_POISSON);
//    job.setMaxTemporalSize(60);
//    job.setTimeaggregationunits(SatScanJob.TIMEAGGREGATIONUNITS_DAY);
//    job.setLastrun(Date.valueOf("2006-06-16"));
//    Date scheduleDate = Date.valueOf("2006-06-16");
//
//    assertNotNull("Observation window begin.", job
//        .getObservationBegin(scheduleDate));
//    assertNotNull("Observation window end.", job
//        .getObservationEnd(scheduleDate));
//    assertEquals("Observation start date.", "2006-03-09", job
//        .getObservationBegin(scheduleDate).toString());
//    assertEquals("Observation end date.", "2006-06-16", job.getObservationEnd(
//        scheduleDate).toString());
//
//    Calendar from = Calendar.getInstance();
//    from.setTimeInMillis(Date.valueOf("2006-06-03").getTime());
//    Calendar to = Calendar.getInstance();
//    to.setTimeInMillis(Date.valueOf("2006-06-23").getTime());
//    Vector<Date> scheduleDates = job.calculateScheduleDates(from, to);
//    assertEquals(1, scheduleDates.size());
//    
//    from.setTimeInMillis(Date.valueOf("2006-06-16").getTime());
//    to.setTimeInMillis(Date.valueOf("2006-06-22").getTime());
//    scheduleDates = job.calculateScheduleDates(from, to);
//    assertEquals(1, scheduleDates.size());
//    assertEquals("Schedule on enddate.", "2006-06-22", scheduleDates.get(0).toString() );
//    
//    from.setTimeInMillis(Date.valueOf("2006-06-16").getTime());
//    to.setTimeInMillis(Date.valueOf("2006-06-29").getTime());
//    scheduleDates = job.calculateScheduleDates(from, to);
//    assertEquals(2, scheduleDates.size());
//    assertEquals("Schedule on enddate.", "2006-06-29", scheduleDates.get(1).toString() );
//
//    job.setLastrun(Date.valueOf("2006-06-8"));
//    from.setTimeInMillis(Date.valueOf("2006-06-10").getTime());
//    to.setTimeInMillis(Date.valueOf("2006-06-23").getTime());
//    scheduleDates = job.calculateScheduleDates(from, to);
//    assertEquals(3, scheduleDates.size()); 
//    assertEquals("Schedule on friday.", "2006-06-09", scheduleDates.get(0).toString() );
//    assertEquals("Schedule on friday.", "2006-06-16", scheduleDates.get(1).toString() );
//    assertEquals("Schedule on friday.", "2006-06-23", scheduleDates.get(2).toString() );

  }
}
