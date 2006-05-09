package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import de.meningococcus.episcangis.db.model.Area;

/**
 * @author Markus Reinhardt
 */
public class AreaTest extends AbstractTestCase
{
  public AreaTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(AreaTest.class);
  }

  public void testBboxAsText()
  {
    Area a = new Area();
    a.setBboxAsWKT("POLYGON((1.1 1.1,1.1 1.2,1.2 1.2,1.2 1.1,1.1 1.1))");
    assertTrue(a.getBoundingBox().isValid());
  }
}
