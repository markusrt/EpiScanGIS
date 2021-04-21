package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Calendar;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestSuite;
import de.meningococcus.episcangis.web.AbstractTestCase;

public class ParameterComponentTest extends AbstractTestCase
{
  private SelectParameter serogroups, fromAge;

  /**
   *
   */
  public ParameterComponentTest(String testName)
  {
    super(testName);
    serogroups = new SelectParameter("SEROGROUP", "Test parameter", false);
    serogroups.add(new ParameterValue("all", "'B','C','Y','W135','29E','A'"));
    serogroups.add(new ParameterValue("B", "'B'"));
    serogroups.add(new ParameterValue("C", "'C'"));
    serogroups.add(new ParameterValue("Y", "'Y'"));
    serogroups.add(new ParameterValue("W135", "'W135'"));
    serogroups.add(new ParameterValue("29E", "'29E'"));
    serogroups.add(new ParameterValue("A", "'A'"));

    fromAge = new SelectParameter("fromAge", "Age from", false);
    for (int age = 0; age <= 90; age++)
    {
      ParameterValue toValue = new ParameterValue(String.valueOf(age));
      if (age == 90)
      {
        toValue = new ParameterValue("90+", String.valueOf(1000));
      }
      fromAge.add(toValue);
    }
    try
    {
      fromAge.selectValue(String.valueOf(1000));
    }
    catch (InvalidParameterValueException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(ParameterComponentTest.class);
  }

  public void testComposite()
  {
    boolean letsFireAnException = false;
    try
    {
      ParameterComposite testParameters = new ParameterComposite("mapparams",
          "Map parameters");

      testParameters.add(serogroups);
      testParameters.add(fromAge);
      testParameters.add(new ParameterValue("fromAge", "duplicates not allowed"));

      assertEquals(3, testParameters.size());
      assertEquals("1000", testParameters.get("fromAge").getValue());

      testParameters.selectValue("SEROGROUP", "'B'");
      assertEquals("Value selected.", "'B'", testParameters.get("SEROGROUP")
          .getValue());

      ParameterComposite YaComposite = new ParameterComposite("layerparams",
          "Parameters for Maplayers");
      SelectParameter sel123 = new SelectParameter("123", "1, 2 or 3?");
      ParameterValue sel1 = new ParameterValue("1", "1");
      sel1.setSelected(true);
      sel123.add(sel1);
      sel123.add(new ParameterValue("2", "2"));
      sel123.add(new ParameterValue("3", "3"));
      YaComposite.add(sel123);
      testParameters.add(YaComposite);

      testParameters.selectValue("123", "2");
      assertEquals("Value selected.", "2", testParameters.get("123").getValue());

      Iterator<ParameterComponent> i = testParameters.iterator();
      int count = 0;
      while (i.hasNext())
      {
        count++;
        ParameterComponent p = i.next();
      }
      assertEquals(4, count);
    }
    catch (InvalidParameterValueException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    catch (ParameterNotFoundException e)
    {
      if (!letsFireAnException)
      {
        e.printStackTrace();
        fail(e.getMessage());
      }
    }

  }

  public void testSelectParameter()
  {
    try
    {
      SelectParameter p = new SelectParameter("testparam", "Test parameter",
          false);
      p.add(new ParameterValue("all", "'B','C','Y','W135','29E','A'"));
      p.add(new ParameterValue("B", "'B'"));
      p.add(new ParameterValue("C", "'C'"));
      p.add(new ParameterValue("Y", "'Y'"));
      p.add(new ParameterValue("W135", "'W135'"));
      p.add(new ParameterValue("29E", "'29E'"));
      p.add(new ParameterValue("A", "'A'"));

      assertEquals("Adding of values works", 7, p.size());
      p.selectValue("'B','C','Y','W135','29E','A'");
      assertEquals("Value selected.", "'B','C','Y','W135','29E','A'", p
          .getValue());

      System.err.println(p.getValue());
      p.selectValue("'29E'");
      assertEquals("Value selected.", "'29E'", p.getValue());
      p.selectValue("'A'");
      p.selectValue("'B'");
      assertEquals("Value selected.", "'B'", p.getValue());
    }
    catch (InvalidParameterValueException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public void testMultiSelectParameter()
  {
    try
    {
      SelectParameter p = new SelectParameter("testparam", "Test parameter",
          true);
      p.add(new ParameterValue("all", "\"B\",\"C\",\"Y\",\"W135\",\"29E\",\"A\""));
      p.add(new ParameterValue("B", "\"B\""));
      p.add(new ParameterValue("C", "\"C\""));
      p.add(new ParameterValue("Y", "\"Y\""));
      p.add(new ParameterValue("W135", "\"W135\""));
      p.add(new ParameterValue("29E", "\"29E\""));
      p.add(new ParameterValue("A", "\"A\""));

      assertEquals("Adding of values works", 7, p.size());

      p.selectValue("\"B\",\"C\",\"Y\",\"W135\",\"29E\",\"A\"");
      //Bug on 2006-07-09
      assertEquals("Value selected.", "\"B\",\"C\",\"Y\",\"W135\",\"29E\",\"A\"", p
          .getValue());
      int selectCount = 0;
      for(ParameterComponent value : p ) {
        if(value.isSelected())selectCount++;
      }
      assertEquals(1, selectCount);
      p.selectValue("\"C\",\"B\",\"Y\"");
      assertEquals("Values \"C\" selected.", "\"B\",\"C\",\"Y\"", p.getValue());
    }
    catch (InvalidParameterValueException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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

    try
    {
      assertEquals("Defaultselection fromMonth JANUARY", "01",
          observationPeriod.getValue("fromMonth"));

      assertEquals("Defaultselection fromYear ", String.valueOf(from
          .get(Calendar.YEAR)), observationPeriod.getValue("fromYear"));

      assertEquals("Defaultselection toMonth OCTOBER", "10", observationPeriod
          .getValue("toMonth"));
      assertEquals("Defaultselection toYear ", String.valueOf(to
          .get(Calendar.YEAR)), observationPeriod.getValue("toYear"));

    }
    catch (ParameterNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void testBloodyBug()
  {
    ParameterComponent mapParameters = new ParameterComposite("dummy");
    Calendar from = Calendar.getInstance();
    from.set(Calendar.YEAR, from.get(Calendar.YEAR) - 5);

    Calendar to = Calendar.getInstance();
    to.set(Calendar.MONDAY, Calendar.OCTOBER);

    ParameterComponent observationPeriod = new PeriodParameter(
        "observationPeriod", from.getTime(), to.getTime(), "Observation period");

    from.set(Calendar.MONTH, Calendar.JANUARY);
    from.set(Calendar.YEAR, to.get(Calendar.YEAR));

    ((PeriodParameter) observationPeriod).setDefaultSelection(from.getTime(),
        to.getTime());
    mapParameters.add(observationPeriod);
    mapParameters.add(fromAge);

    String name = "fromMonth";
    ParameterComponent para = observationPeriod.get(name);
    assertEquals("fromMonth", para.getName());
    assertTrue(serogroups instanceof SelectParameter);
  }
}
