package de.meningococcus.episcangis.web;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.dao.UserNotFoundException;
import de.meningococcus.episcangis.db.model.User;
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

  public void testResetByEmail()
  {
    setRequestPathInfo("/ForgotPassword");
    UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
    try
    {
      User mreinhardt = userDAO.getUser("mreinhardt");
      
      addRequestParameter("email", mreinhardt.getEmail());
      
      assertTrue("Password is secret", mreinhardt.hasPassword("geheim"));
      
//      actionPerform();
//
//      verifyForward(GlobalSettings.FORWARD_SUCCESS);
//      verifyNoActionErrors();
//      verifyActionMessages(new String [] {"user.resetPassword.success"});
//      
//      mreinhardt = userDAO.getUser("mreinhardt");
//      assertFalse("Password has changed", mreinhardt.hasPassword("geheim"));
//      mreinhardt.setPassword("geheim");
//      userDAO.updateUser(mreinhardt);
//      
//      addRequestParameter("email", "invalid@bitmap-friends.de");
//      
//      actionPerform();
//
//      verifyForward(GlobalSettings.FORWARD_ERROR);
//      verifyActionErrors(new String [] {"user.resetPassword.failure"});
    }
    catch (UserNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }

}
