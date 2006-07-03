package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpSession;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.dao.UserNotFoundException;
import de.meningococcus.episcangis.db.model.User;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ManageUsersActionTest extends AbstractStrutsTestCase
{
  public ManageUsersActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(ManageUsersActionTest.class);
  }

  public void testRegisterNewUser()
  {
    // TODO make testcase work
    setRequestPathInfo("/ManageUsers");
    
    addRequestParameter("method", "register");
    addRequestParameter("username", "strutstest");
    addRequestParameter("confirmPassword", "password");
    addRequestParameter("password", "password");
    addRequestParameter("title", "Dr. med.");
    addRequestParameter("forename", "Hans");
    addRequestParameter("lastname", "Moleman");
    addRequestParameter("email", "hans@test.de");
    addRequestParameter("phone", "0931-9932881903");
    addRequestParameter("organisation", "NRZM");
    addRequestParameter("department", "University Würzburg");
    addRequestParameter("domain", "Public Health");
    addRequestParameter("message", "Hi folks");
    addRequestParameter("street", "Testdrive 10");
    addRequestParameter("zip", "9832489");
    addRequestParameter("city", "Testme");
    addRequestParameter("organisation", "Testme");
    addRequestParameter("department", "Testme");
    addRequestParameter("domain", "Testme");
    actionPerform();
    
    System.err.println(getActualForward());
    verifyNoActionErrors();
    
    try
    {
      UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
      User hans = userDAO.getUser("strutstest");
      assertEquals(hans.getStreet(), "Testdrive 10");
    }
    catch (UserNotFoundException e)
    {
      e.printStackTrace();
      fail("User 'test' could not be created.");
    }
  }
}
