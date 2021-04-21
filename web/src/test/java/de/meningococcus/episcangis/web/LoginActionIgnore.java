package de.meningococcus.episcangis.web;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;

import de.meningococcus.episcangis.db.model.User;
import junit.framework.Test;
import junit.framework.TestSuite;

public class LoginActionIgnore extends AbstractStrutsTestCase
{
  public LoginActionIgnore(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(LoginActionIgnore.class);
  }

  public void testSuccessfulLogin()
  {
    setRequestPathInfo("/Login");
    request.setUserRole("nrzm");
    request.setUserPrincipal(new MockPrincipal("uvogel"));

    actionPerform();

    verifyForward(GlobalSettings.FORWARD_SUCCESS);
    User currentUser = (User) request.getSession().getAttribute("user");
    assertEquals("uvogel", currentUser.getUsername());
    assertTrue(DateUtils.isSameDay(currentUser.getLastLogin(), new Date(
        Calendar.getInstance().getTime().getTime())));
  }

  public void testFailedLogin()
  {
//    setRequestPathInfo("/Login");
//
//    actionPerform();
//
//    verifyForward(GlobalSettings.FORWARD_SUCCESS);
  }

  public void testRefererForwardToIndex()
  {
    setRequestPathInfo("/Login");
    request.setUserRole("nrzm");
    request.setUserPrincipal(new MockPrincipal("uvogel"));

    request.setHeader("Referer", "http://www.google.de");

    actionPerform();

    verifyForward(GlobalSettings.FORWARD_INDEX);
  }

  public void testRefererForwardMapbrowser()
  {
    setRequestPathInfo("/Login");
    request.setUserRole("nrzm");
    request.setUserPrincipal(new MockPrincipal("uvogel"));
    
    request.setHeader("Referer", GlobalSettings.MAPBROWSER_SITE_URL);

    actionPerform();

    assertTrue(getActualForward().contains(GlobalSettings.MAPBROWSER_SITE_URL));

    request.setHeader("Referer", "http://myhost.mockup.org/"
        + GlobalSettings.MAPBROWSER_SITE_URL + "?mockparam=134&dummy=true");

    actionPerform();

    assertTrue(getActualForward().contains(GlobalSettings.MAPBROWSER_SITE_URL));
  }
}
