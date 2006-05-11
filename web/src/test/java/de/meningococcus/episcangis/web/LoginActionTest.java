package de.meningococcus.episcangis.web;

import de.meningococcus.episcangis.db.model.User;
import junit.framework.Test;
import junit.framework.TestSuite;

public class LoginActionTest extends AbstractStrutsTestCase
{
  public LoginActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(LoginActionTest.class);
  }

  public void testSuccessfulLogin()
  {
    setRequestPathInfo("/Login");
    request.setUserRole("nrzm");
    request.setUserPrincipal(new MockPrincipal("mock"));
    
    actionPerform();
    
    verifyForward(GlobalSettings.FORWARD_SUCCESS);
    assertEquals("mock", ((User) request.getSession().getAttribute("user"))
        .getUsername());
  }

  public void testFailedLogin()
  {
    setRequestPathInfo("/Login");
    
    actionPerform();
    
    verifyForward(GlobalSettings.FORWARD_SUCCESS);
  }

  public void testRefererForwardToIndex()
  {
    setRequestPathInfo("/Login");
    request.setHeader("Referer", "http://www.google.de");
    
    actionPerform();
    
    verifyForward(GlobalSettings.FORWARD_INDEX);
  }

  public void testRefererForwardMapbrowser()
  {
    setRequestPathInfo("/Login");
    request.setHeader("Referer", GlobalSettings.MAPBROWSER_SITE_URL);
    
    actionPerform();
    
    verifyForward(GlobalSettings.FORWARD_MAPBROWSER);
    
    request.setHeader("Referer", "http://myhost.mockup.org/"
        + GlobalSettings.MAPBROWSER_SITE_URL + "?mockparam=134&dummy=true");
    
    actionPerform();
    
    verifyForward(GlobalSettings.FORWARD_MAPBROWSER);
  }
}
