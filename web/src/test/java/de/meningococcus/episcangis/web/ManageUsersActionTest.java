package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpSession;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
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
    //TODO make testcase work
//    setRequestPathInfo("/ManageUsers");
//    addRequestParameter("method", "register");
//    addRequestParameter("username", "test");
//    addRequestParameter("password", "pass");
//    addRequestParameter("title", "Dr. med.");
//    addRequestParameter("forename", "Hans");
//    addRequestParameter("lastname", "Moleman");
//    addRequestParameter("email", "hans@test.de");
//    addRequestParameter("phone", "0931-9932881903");
//    addRequestParameter("organisation", "NRZM");
//    addRequestParameter("department","University Würzburg");
//    addRequestParameter("domain", "Public Health");
//    addRequestParameter("message", "Hi folks");
//    addRequestParameter("street", "Testdrive 10");
//    addRequestParameter("zip", "9832489" );
//    addRequestParameter("city" , "Testme");
//    actionPerform();
//    UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
//    User hans = userDAO.getUser("test");
//    assertEquals(hans.getStreet(), "Testdrive 10");
  }
}
