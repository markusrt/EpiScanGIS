package de.meningococcus.episcangis.web;

import javax.servlet.http.HttpSession;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.dao.UserNotFoundException;
import de.meningococcus.episcangis.db.model.User;

import junit.framework.Test;
import junit.framework.TestSuite;

public class CRUDUserActionTest extends AbstractStrutsTestCase
{
  public final String USERNAME = "strutstest", PASSWORD = "password",
      TITLE = "Dr. med.", FORENAME = "Hans", LASTNAME = "Moleman",
      EMAIL = "hans@test.de", PHONE = "0931-9932881903", ORGANISATION = "NRZM",
      DEPARTMENT = "University Würzburg", DOMAIN = "Public Health",
      MESSAGE = "Hi folks", STREET = "Testdrive 10", ZIP = "9832489",
      CITY = "Testme";

  public CRUDUserActionTest(String testName)
  {
    super(testName);
  }

  public static Test suite()
  {
    return new TestSuite(CRUDUserActionTest.class);
  }

  public void testCreateNewUser()
  {
    try
    {
      UserDAO userDao = DaoFactory.getDaoFactory().getUserDAO();
      User strutstest = userDao.getUser(USERNAME);
      userDao.deleteUser(strutstest);
    }
    catch (UserNotFoundException e)
    {
    }

    setRequestPathInfo("/ManageUsers");

    addRequestParameter("method", "register");
    addRequestParameter("username", USERNAME);
    addRequestParameter("confirmPassword", PASSWORD);
    addRequestParameter("password", PASSWORD);
    addRequestParameter("title", TITLE);
    addRequestParameter("forename", FORENAME);
    addRequestParameter("lastname", LASTNAME);
    addRequestParameter("email", EMAIL);
    addRequestParameter("phone", PHONE);
    addRequestParameter("organisation", ORGANISATION);
    addRequestParameter("department", DEPARTMENT);
    addRequestParameter("domain", DOMAIN);
    addRequestParameter("message", MESSAGE);
    addRequestParameter("street", STREET);
    addRequestParameter("zip", ZIP);
    addRequestParameter("city", CITY);

    actionPerform();

    verifyNoActionErrors();

    try
    {
      UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
      User hans = userDAO.getUser(USERNAME);
      assertEquals(hans.getUsername(), USERNAME);
      assertEquals(hans.getTitle(), TITLE);
      assertEquals(hans.getForename(), FORENAME);
      assertEquals(hans.getLastname(), LASTNAME);
      assertEquals(hans.getEmail(), EMAIL);
      assertEquals(hans.getPhone(), PHONE);
      assertEquals(hans.getOrganisation(), ORGANISATION);
      assertEquals(hans.getDepartment(), DEPARTMENT);
      assertEquals(hans.getDomain(), DOMAIN);
      assertEquals(hans.getMessage(), MESSAGE);
      assertEquals(hans.getStreet(), STREET);
      assertEquals(hans.getZip(), ZIP);
      assertEquals(hans.getCity(), CITY);
    }
    catch (UserNotFoundException e)
    {
      e.printStackTrace();
      fail("User 'test' could not be created.");
    }
  }

  public void testErrrorCreateUser()
  {
    setRequestPathInfo("/ManageUsers");

    addRequestParameter("method", "register");
    actionPerform();
    verifyActionErrors(new String[] { "errors.required", "errors.required",
        "errors.required", "errors.required", "errors.required",
        "errors.required", "errors.required", "errors.required",
        "errors.required", "errors.required", "errors.required",
        "errors.required" });
    addRequestParameter("confirmPassword", PASSWORD);
    addRequestParameter("password", PASSWORD);
    addRequestParameter("title", TITLE);
    addRequestParameter("forename", FORENAME);
    addRequestParameter("lastname", LASTNAME);
    addRequestParameter("email", EMAIL);
    addRequestParameter("phone", PHONE);
    addRequestParameter("organisation", ORGANISATION);
    addRequestParameter("department", DEPARTMENT);
    addRequestParameter("domain", DOMAIN);
    addRequestParameter("message", MESSAGE);
    addRequestParameter("street", STREET);
    addRequestParameter("zip", ZIP);
    addRequestParameter("city", CITY);
    addRequestParameter("username", USERNAME);
    actionPerform();
    verifyActionErrors(new String[] { "errors.username" });
    verifyForwardPath("/registerUser.vm");
    addRequestParameter("username", "unmöglicher name");
    actionPerform();
    verifyActionErrors(new String[] { "errors.username.mask" });
    addRequestParameter("username", USERNAME + "2");
    addRequestParameter("zip", "203948098430980948209384091820938091283092813");
    actionPerform();
    verifyActionErrors(new String[] { "errors.maxlength" });
    addRequestParameter("zip", ZIP);
    addRequestParameter("username", "kurz");
    actionPerform();
    verifyActionErrors(new String[] { "errors.minlength" });
  }

  public void testDeleteUserRestricted()
  {
//    setRequestPathInfo("/DeleteUser");
//
//    request.setUserRole("public_health");
//    request.setUserPrincipal(new MockPrincipal("rki101"));
//
//    addRequestParameter("username", "strutstest");
//    actionPerform();
//
//
//    try
//    {
//      UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
//      User mreinhardt = userDAO.getUser("strutstest");
//
//    }
//    catch (UserNotFoundException e)
//    {
//      fail("User 'rki101' deleted the account '" + USERNAME
//          + "' but is no administrator.");
//      e.printStackTrace();
//    }
  }

  public void testDeleteUser()
  {
    setRequestPathInfo("/DeleteUser");

    request.setUserRole("admin");
    request.setUserPrincipal(new MockPrincipal("mreinhardt"));

    addRequestParameter("username", "strutstest");
    actionPerform();

    try
    {
      UserDAO userDAO = DaoFactory.getDaoFactory().getUserDAO();
      User mreinhardt = userDAO.getUser("strutstest");
      fail("User " + mreinhardt.getUsername() + " was not deleted.");
    }
    catch (UserNotFoundException e)
    {
    }
  }
}
