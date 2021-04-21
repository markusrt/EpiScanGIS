package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import de.meningococcus.episcangis.db.model.NRZMCaseType;

/**
 * @author Markus Reinhardt
 */
public class NRZMCaseTypeTest extends AbstractTestCase
{
  /**
   *
   */
  public NRZMCaseTypeTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(NRZMCaseTypeTest.class);
  }

  public void testNRZMCaseType()
  {
    NRZMCaseType complete = new NRZMCaseType("B;12-1;13-1;1-5");
    assertTrue(
        "NRZM case type '" + complete.getIdentifier() + "' is complete.",
        complete.isComplete());
    NRZMCaseType[] incomplete_s = { new NRZMCaseType(";;;"),
        new NRZMCaseType("B;;;"), new NRZMCaseType(";12-1;;"),
        new NRZMCaseType(";;13-1;"), new NRZMCaseType(";;;1-5"),
        new NRZMCaseType("B;;;1-5"), new NRZMCaseType(";12-1;13-1;") };
    for (int i = 0; i < incomplete_s.length; i++)
    {
      assertTrue("NRZM case type '" + complete.getIdentifier()
          + "' is similar to '" + incomplete_s[i].getIdentifier() + "'",
          complete.isSimilarTo(incomplete_s[i]));
    }
    NRZMCaseType[] incomplete_ns = { new NRZMCaseType("C;;;"),
        new NRZMCaseType("B;12-2;;"), new NRZMCaseType("B;12-1;13-1;1-3"),
        new NRZMCaseType(";12-1;13-2;"), new NRZMCaseType("B;12;13-1;1-5"),
        new NRZMCaseType(";;;1-8"), new NRZMCaseType(";121;;") };
    for (int i = 0; i < incomplete_ns.length; i++)
    {
      assertFalse("NRZM case type '" + complete.getIdentifier()
          + "' is not similar to '" + incomplete_ns[i].getIdentifier() + "'",
          complete.isSimilarTo(incomplete_ns[i]));
    }
  }

  public void testNullpointerBug_20060911() {
    NRZMCaseType ct = new NRZMCaseType("B;5-1;2-2;5-8");
    assertNotNull(ct.getFormattedIdentifier());
    System.err.println(ct.getFormattedIdentifier());
  }
}
