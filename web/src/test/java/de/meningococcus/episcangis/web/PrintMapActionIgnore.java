package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.MapInitializationException;
import de.meningococcus.episcangis.map.MockNrzmMap;
import de.meningococcus.episcangis.map.NrzmMap;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PrintMapActionIgnore extends AbstractStrutsTestCase
{
  public PrintMapActionIgnore(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(PrintMapActionIgnore.class);
  }

  public void testExceptionIfNotInitialized()
  {
    setRequestPathInfo("/PrintMap");
    actionPerform();
    verifyForward(GlobalSettings.FORWARD_ERROR);
  }
  
  public void testPrintMockMap()
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
    setRequestPathInfo("/PrintMap");
    actionPerform();
    verifyForward(GlobalSettings.FORWARD_SUCCESS);
  }

}
