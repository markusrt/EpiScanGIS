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

import de.meningococcus.episcangis.db.dao.ClusterFeedbackDAO;
import de.meningococcus.episcangis.db.model.ClusterFeedback;
import de.meningococcus.episcangis.db.model.SatScanJob;

/**
 * @author rzxp001 TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class ClusterFeedbackTest extends AbstractTestCase
{
  /**
   *
   */
  public ClusterFeedbackTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(ClusterFeedbackTest.class);
  }

  public void testCRUD()
  {
    //ClusterFeedbackDAO cfDao = DaoFactory.getDaoFactory().getClusterFeedbackDAO();
    ClusterFeedback cf = new ClusterFeedback();
    cf.setClusterId(1002);
    cf.setCaseId(3655);
    cf.setExpirationDate(Date.valueOf("2006-12-31"));
    cf.setDiscoPub("AKW");
    //cfDao.createClusterFeedback(cf);
    // String tan=cf.getTan();
    // assertNotNull(tan);
    // assertNotNull(cf.getLastChange());
    // assertTrue(cf.isExpired());
    // cf=null;
    // assertNotNull(cf=cfDao.findByTan(tan));
    // assertEquals(tan, cf.getTan());
    // cf.setDiscoPub("Airport");
    // cf.setTan("No Go!");
    // cfDao.updateClusterFeedback(cf);
    // cf=cfDao.findByTan(tan);
    // assertEquals("Airport", cf.getDiscoPub());
    // assertEquals(tan, cf.getTan());
    // assertEquals(1, cfDao.findAll().size());
    // cfDao.deleteClusterFeedback(cf);
    // assertNull(cfDao.findByTan(tan));
    // assertEquals(0, cfDao.findAll().size());
  }
}
