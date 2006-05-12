package de.meningococcus.episcangis.web;

import de.meningococcus.episcangis.db.DaoFactory;
import servletunit.struts.MockStrutsTestCase;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public abstract class AbstractStrutsTestCase extends MockStrutsTestCase
{

  public void setUp() throws Exception
  {
    super.setUp();
    DaoFactory
        .setConfigFile("/home/mreinhardt/episcangis/web/src/test/resources/epidegis-db.properties");
  }

  public void tearDown() throws Exception
  {
    super.tearDown();
  }

  public AbstractStrutsTestCase(String testName)
  {
    super(testName);
  }

}
