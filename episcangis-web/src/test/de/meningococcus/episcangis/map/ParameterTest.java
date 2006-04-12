package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestSuite;


import de.meningococcus.episcangis.map.MultiSelectParameter;
import de.meningococcus.episcangis.map.OLDSelectParameter;
import de.meningococcus.episcangis.map.ParameterValue;
import de.meningococcus.episcangis.map.PeriodParameter;
import de.meningococcus.episcangis.web.AbstractTestCase;

/**
 * @author rzxp001 TODO To change the template for this generated type comment
 *         go to Window - Preferences - Java - Code Style - Code Templates
 */
public class ParameterTest extends AbstractTestCase
{
  /**
   *  
   */
  public ParameterTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(ParameterTest.class);
  }

  public void testSelectParameter()
  {
    OLDSelectParameter p = new OLDSelectParameter("testparam");
    p.addValue(new ParameterValue("all", "'B','C','Y','W135','29E','A'"));
    p.addValue(new ParameterValue("B", "'B'"));
    p.addValue(new ParameterValue("C", "'C'"));
    p.addValue(new ParameterValue("Y", "'Y'"));
    p.addValue(new ParameterValue("W135", "'W135'"));
    p.addValue(new ParameterValue("29E", "'29E'"));
    p.addValue(new ParameterValue("A", "'A'"));
    p.addValue(new ParameterValue("dup", "'Y'"));

    assertEquals("Adding of values works", 7, p.getValues().size());
    p.setValue("'B','C','Y','W135','29E','A'");
    assertEquals("Value selected.", "'B','C','Y','W135','29E','A'", p
        .getValue());
  }

  public void testMultiSelectParameter()
  {
    MultiSelectParameter p = new MultiSelectParameter("testparam");
    p.addValue(new ParameterValue("Test1", "test1"));
    p.addValue(new ParameterValue("Test2", "test2"));
    p.addValue(new ParameterValue("Test3", "test2"));
    p.addValue(new ParameterValue("Test4", "test3"));

    assertEquals("Adding of values works", 3, p.getValues().size());

    p.setValue("test1,test3");
    assertEquals("Values 'test1' and 'test3' selected.", "test1,test3", p
        .getValue());

  }

  public void testPeriodParameter()
  {
    Calendar from = Calendar.getInstance();
    from.set(Calendar.YEAR, from.get(Calendar.YEAR) - 5);

    Calendar to = Calendar.getInstance();
    to.set(Calendar.MONDAY, Calendar.OCTOBER);

    PeriodParameter observationPeriod = new PeriodParameter(
        "observationPeriod", from.getTime(), to.getTime(), "Observation period");

    from.set(Calendar.MONTH, Calendar.JANUARY);
    from.set(Calendar.YEAR, to.get(Calendar.YEAR));

    observationPeriod.setDefaultSelection(from.getTime(), to.getTime());

    assertEquals("Defaultselection fromMonth JANUARY", "01", observationPeriod
        .getFromMonth().getValue());
    assertEquals("Defaultselection fromYear ", String.valueOf(from
        .get(Calendar.YEAR)), observationPeriod.getFromYear().getValue());

    assertEquals("Defaultselection toMonth OCTOBER", "10", observationPeriod
        .getToMonth().getValue());
    assertEquals("Defaultselection toYear ", String.valueOf(to
        .get(Calendar.YEAR)), observationPeriod.getToYear().getValue());

  }
}
