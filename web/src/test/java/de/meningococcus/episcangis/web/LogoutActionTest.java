package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpSession;

import junit.framework.Test;
import junit.framework.TestSuite;

public class LogoutActionTest extends AbstractStrutsTestCase
{
  public LogoutActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(LogoutActionTest.class);
  }

  public void testNewSessionOnLogout()
  {
    setRequestPathInfo("/Logout");
    HttpSession sessionBefore = request.getSession();
    actionPerform();
    assertNotSame(sessionBefore, request.getSession());
  }

  public void testRefererForwardToIndex()
  {
    setRequestPathInfo("/Logout");
    request.setHeader("Referer", "http://www.google.de");
    actionPerform();
    verifyForward(GlobalSettings.FORWARD_INDEX);
  }
  
  public void testRefererForwardMapbrowser()
  {
    setRequestPathInfo("/Logout");
    request.setHeader("Referer", GlobalSettings.MAPBROWSER_SITE_URL);
    
    actionPerform();
    
    assertTrue(getActualForward().contains(GlobalSettings.MAPBROWSER_SITE_URL));
    
    request.setHeader("Referer", "http://myhost.mockup.org/"
        + GlobalSettings.MAPBROWSER_SITE_URL + "?mockparam=134&dummy=true");
    
    actionPerform();
    
    assertTrue(getActualForward().contains(GlobalSettings.MAPBROWSER_SITE_URL));
  }
}
