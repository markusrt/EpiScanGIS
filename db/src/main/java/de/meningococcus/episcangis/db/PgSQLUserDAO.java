package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.dao.UserNotFoundException;
import de.meningococcus.episcangis.db.model.User;

/**
 * Implementation of the DAO SatScanDAO. This class uses Jakarta Commons Dbutils (<a
 * href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
 * 
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLUserDAO extends DbUtilsDAO implements UserDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLUserDAO.class);

  /**
   * The database queries used. TODO create views in the database to hide all
   * database specific query parts
   */
  private static final String UPDATE_USER = "UPDATE users "
      + "SET password = ?, title = ?, forename = ?, lastname = ?, email = ?, "
      + " phone = ?, organisation = ?, department = ?, domain = ?, street = ?, "
      + " zip = ?, city = ?, lastlogin = ? " + " WHERE username = ?",
      GET_USER = "SELECT *, password AS digestedpassword FROM users WHERE username=?",
      GET_USERS = "SELECT *, password AS digestedpassword FROM users",
      DELETE_USER = "DELETE FROM users WHERE username=?",
      CREATE_USER = "INSERT INTO users "
          + "(username, password, title, forename, lastname, email, phone, "
          + "organisation, department, domain, message, street, zip, city ) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
      GET_ROLES = "SELECT rolename FROM user_roles GROUP BY rolename",
      GET_USER_ROLES = "SELECT rolename FROM user_roles WHERE username=?",
      DELETE_USER_ROLES = "DELETE FROM user_roles WHERE username=?",
      CREATE_USER_ROLE = "INSERT INTO user_roles (username, rolename) VALUES (?,?)";

  PgSQLUserDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#getUser(java.lang.String)
   */
  public User getUser(String username) throws UserNotFoundException
  {
    User result = null;
    try
    {
      result = (User) run
          .query(GET_USER, username, new BeanHandler(User.class));
      if (result != null)
      {
        for (String role : getUserRoles(result))
        {
          result.addRole(role);
        }
        log.debug("Found user '" + result.getUsername() + "' with username '"
            + username + "'.");
      }
      else
      {
        log.debug("Found no user with username '" + username + "'.");
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
      e.printStackTrace();
    }
    if (result == null)
    {
      throw new UserNotFoundException("The user '" + username
          + "' was not found in the database.");
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#createUser(de.meningococcus.episcangis.db.model.User)
   */
  public int createUser(User user)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(CREATE_USER, new Object[] { user.getUsername(),
          user.getPassword(), user.getTitle(), user.getForename(),
          user.getLastname(), user.getEmail(), user.getPhone(),
          user.getOrganisation(), user.getDepartment(), user.getDomain(),
          user.getMessage(), user.getStreet(), user.getZip(), user.getCity() });
      updateUserRoles(user);
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#updateUser(de.meningococcus.episcangis.db.model.User)
   */
  public int updateUser(User user)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(UPDATE_USER, new Object[] { user.getPassword(),
          user.getTitle(), user.getForename(), user.getLastname(),
          user.getEmail(), user.getPhone(), user.getOrganisation(),
          user.getDepartment(), user.getDomain(), user.getStreet(),
          user.getZip(), user.getCity(), user.getLastLogin(), user.getUsername() });
      updateUserRoles(user);
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#deleteUser(de.meningococcus.episcangis.db.model.User)
   */
  public int deleteUser(User user)
  {
    int insertCount = 0;
    try
    {
      insertCount = run
          .update(DELETE_USER, new Object[] { user.getUsername() });
      deleteUserRoles(user);
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#getRoles()
   */
  @SuppressWarnings("unchecked")
  public Collection<String> getRoles()
  {
    List<String> result = new ArrayList<String>();
    try
    {
      List<Object[]> resultRows = (List<Object[]>) run.query(GET_ROLES,
          new ArrayListHandler());
      for (Object[] row : resultRows)
      {
        result.add((String) row[0]);
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see de.meningococcus.episcangis.db.dao.UserDAO#getUserRoles(de.meningococcus.episcangis.db.model.User)
   */
  @SuppressWarnings("unchecked")
  public Collection<String> getUserRoles(User user)
  {
    List<String> result = new ArrayList<String>();
    try
    {
      List<Object[]> resultRows = (List<Object[]>) run.query(GET_USER_ROLES,
          user.getUsername(), new ArrayListHandler());
      for (Object[] row : resultRows)
      {
        result.add((String) row[0]);
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

  private void deleteUserRoles(User user)
  {
    try
    {
      run.update(DELETE_USER_ROLES, user.getUsername());
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
  }

  private void updateUserRoles(User user)
  {
    try
    {
      deleteUserRoles(user);
      for (String rolename : user.getRoles())
      {
        run.update(CREATE_USER_ROLE, new Object[] { user.getUsername(),
            rolename });
      }
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
  }

  @SuppressWarnings("unchecked")
  public Collection<User> getUsers()
  {
    List<User> result = new ArrayList<User>();
    try
    {
      result.addAll((List<User>) run.query(GET_USERS, new BeanListHandler(
          User.class)));
      for (User user : result)
      {
        if (user != null)
        {
          for (String role : getUserRoles(user))
          {
            user.addRole(role);
          }
        }
      }
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
    }
    return result;
  }

}
