package de.meningococcus.episcangis.db;

import java.sql.Date;
import java.util.Collection;

import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;
import junit.framework.Test;
import junit.framework.TestSuite;

public class UserDAOTest extends AbstractDbunitTestCase
{
  UserDAO userDAO;

  public UserDAOTest(String testName)
  {
    super(testName);
    userDAO = DaoFactory.getDaoFactory().getUserDAO();
  }

  public static Test suite()
  {
    return new TestSuite(UserDAOTest.class);
  }

  private User getMockUser()
  {
    User user = new User();
    user.setUsername("mock");
    user.setForename("Hans");
    user.setLastname("Mock");
    user.setDepartment("Institut für Hygiene und Mikrobiologie");
    user.setDomain("NRZM");
    user.setEmail("mock@test.der");
    user.setTitle("Dr.");
    user.setLastLogin(Date.valueOf("2006-05-11"));
    user.setOrganisation("Universität Würzburg");
    user.setMessage("Ich will rein");
    user.setRegistrationDate(Date.valueOf("2006-05-01"));
    user.setPassword("geheim");
    user.setPhone("(+49) 931 201 46714");
    return user;
  }

  private User createMockUser()
  {
    User mock = getMockUser();
    if (userDAO.getUser(mock.getUsername()) == null)
    {
      userDAO.createUser(mock);
    }
    return mock;
  }

  public void testUserDAO()
  {
    assertNotNull(userDAO);
  }

  public void testCreateUser()
  {
    User mock = createMockUser();

    User stillMock = userDAO.getUser("mock");
    assertEquals(mock.getUsername(), stillMock.getUsername());
  }

  public void testDeleteUser()
  {
    User mock = createMockUser();
    userDAO.deleteUser(mock);

    assertNull(userDAO.getUser("mock"));
  }

  public void testUpdateUser()
  {
    User mock = createMockUser();
    mock.setDomain("newDomain");
    mock.setDepartment("newDep");
    mock.addRole("nrzm");
    userDAO.updateUser(mock);
    mock = userDAO.getUser("mock");

    assertEquals("newDomain", mock.getDomain());
    assertEquals("newDep", mock.getDepartment());
    assertTrue(mock.isInRole("nrzm"));
  }

  public void testDuplicateCreate()
  {
    try
    {
      User mock = createMockUser();
      userDAO.createUser(mock);
    }
    catch (Exception e)
    {
      assertEquals(e.getClass(), DaoRuntimeException.class);
    }
  }

  public void testGetRoles()
  {
    User mock = createMockUser();
    mock.addRole("role1");
    mock.addRole("role 2");
    userDAO.updateUser(mock);

    Collection<String> roles = userDAO.getRoles();
    assertTrue("Role 'nrzm' exist", roles.contains("nrzm"));
    assertTrue("Role 'role1' exist", roles.contains("role1"));
    assertTrue("Role 'role 2' exist", roles.contains("role 2"));
  }

}
