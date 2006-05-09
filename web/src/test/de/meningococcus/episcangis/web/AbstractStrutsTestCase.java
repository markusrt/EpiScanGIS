package de.meningococcus.episcangis.web;

import java.io.File;

import servletunit.struts.MockStrutsTestCase;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class AbstractStrutsTestCase extends MockStrutsTestCase
{

  public String basedir = System.getProperty("basedir");

  public void setUp() throws Exception
  {
    super.setUp();
    setConfigFile(getTestFile("src/webapp/WEB-INF/struts-config.xml"));
    setServletConfigFile(getTestFile("src/webapp/WEB-INF/web.xml"));
  }

  public void tearDown() throws Exception
  {
    super.tearDown();
  }

  /**
   * Get test input file.
   * @param path Path to test input file.
   */
  public String getTestFile(String path)
  {
    return new File(basedir, path).getAbsolutePath();
  }

  public AbstractStrutsTestCase(String testName)
  {
    super(testName);
  }

}
