package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.File;

import junit.framework.TestCase;

/**
 * Abstract base class for test cases.
 * 
 * @author <a href="jason@zenplex.com">Jason van Zyl </a>
 */
public abstract class AbstractTestCase extends TestCase
{
  /**
   * Constructor.
   */
  public AbstractTestCase(String testName)
  {
    super(testName);
  }

}
