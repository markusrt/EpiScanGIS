package de.meningococcus.episcangis.web;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ForgotPasswordActionTest extends AbstractStrutsTestCase
{
  public ForgotPasswordActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(ForgotPasswordActionTest.class);
  }

  public void testResetNonexistentEmail()
  {
    setRequestPathInfo("/ForgotPassword");
    addRequestParameter("email", "invalid@bitmap-friends.de");

    actionPerform();
    verifyActionErrors(new String [] {"errors.emailexists"});
  }
}
