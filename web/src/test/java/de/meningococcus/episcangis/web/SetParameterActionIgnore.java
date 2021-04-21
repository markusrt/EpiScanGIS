package de.meningococcus.episcangis.web;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.MockNrzmMap;
import de.meningococcus.episcangis.map.exporter.XmlExporter;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SetParameterActionIgnore extends AbstractStrutsTestCase
{
  public SetParameterActionIgnore(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(SetParameterActionIgnore.class);
  }

  public void testSwitchLocale()
  {
    AbstractWmsMap map = null;
    try
    {
       map = new MockNrzmMap(100,100);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    request.getSession().setAttribute("map", map);
    System.err.println(map.getParameter("SEROGROUPS").getAliasValue());

    setRequestPathInfo("/SetParameter");
    addRequestParameter("name", " SEROGROUPS");
    addRequestParameter("value", "\"B\"");
    actionPerform();
    XmlExporter xmlExporter = new XmlExporter();
    map.export(xmlExporter);
    System.err.println(map.getParameter("SEROGROUPS").getValue());
  }

}
