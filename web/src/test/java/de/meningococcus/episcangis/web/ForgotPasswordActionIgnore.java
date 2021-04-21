package de.meningococcus.episcangis.web;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ForgotPasswordActionIgnore extends AbstractStrutsTestCase
{
  public ForgotPasswordActionIgnore(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(ForgotPasswordActionIgnore.class);
  }

  public void testResetNonexistentEmail()
  {
    setRequestPathInfo("/ForgotPassword");
    addRequestParameter("email", "invalid@bitmap-friends.de");

    actionPerform();
    verifyActionErrors(new String [] {"errors.emailexists"});
  }
}
