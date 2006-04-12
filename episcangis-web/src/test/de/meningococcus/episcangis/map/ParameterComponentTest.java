package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import junit.framework.Test;
import junit.framework.TestSuite;


import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.SelectParameter;
import de.meningococcus.episcangis.web.AbstractTestCase;

/**
 * @author rzxp001 TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class ParameterComponentTest extends AbstractTestCase
{
  /**
   *  
   */
  public ParameterComponentTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(ParameterComponentTest.class);
  }

  public void testSingleSelectParameter()
  {
    ParameterComponent selectParameter = new SelectParameter(
        "singleSelect", "disallows to select multiple values", true);
    selectParameter.add(new ParameterValue("Test1", "test1"));
    selectParameter.add(new ParameterValue("Test2", "test2"));
    selectParameter.add(new ParameterValue("Test3", "test3"));
    selectParameter.selectValue("test1", true);
    selectParameter.selectValue("test2", true);
    assertEquals("Single select works", "test2", selectParameter.getValue());
    
    selectParameter.selectValue("test1", false);
    assertEquals("Unselect works", "", selectParameter.getValue());
  }
  
  public void testSelectParameter()
  {
    ParameterComponent selectParameter = new SelectParameter(
        "singleSelect", "disallows to select multiple values", false);
    selectParameter.add(new ParameterValue("Test1", "test1"));
    selectParameter.add(new ParameterValue("Test2", "test2"));
    selectParameter.add(new ParameterValue("Test3", "test3"));
    selectParameter.selectValue("test1", true);
    selectParameter.selectValue("test2", true);
    System.err.println(selectParameter.getValue());
    assertEquals("Single select works", "test1,test2", selectParameter.getValue());
  }
  
}
