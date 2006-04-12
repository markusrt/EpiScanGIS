package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.UserDAO;
import de.meningococcus.episcangis.db.model.User;

/**
 * Implementation of the DAO SatScanDAO. This class uses Jakarta Commons Dbutils (<a
 * href="http://jakarta.apache.org/commons/dbutils/">
 * http://jakarta.apache.org/commons/dbutils/</a>) to run queries on the
 * database and fill beans with the results.
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
      + "SET username = ?, password  = ?, fullname  = ?, email  = ?"
      + " WHERE username = ?",
      GET_USER = "SELECT * FROM users WHERE username=?",
      DELETE_USER = "DELETE FROM users WHERE username=?",
      CREATE_USER = "INSERT INTO users "
          + "(username, password, fullname, email ) " + "VALUES (?, ?, ?, ?)",
      GET_ROLES = "SELECT rolename FROM user_roles",
      GET_USER_ROLES = GET_ROLES + " WHERE username=?",
      DELETE_USER_ROLES = "DELETE FROM user_roles WHERE username=?",
      CREATE_USER_ROLE = "INSERT INTO user_roles (username, rolename) VALUES (?,?)";

  PgSQLUserDAO(DataSource dataSource)
  {
    super(dataSource);
    // TODO Auto-generated constructor stub
  }

  public User getUser(String username)
  {
    User result = null;
    try
    {
      result = (User) run
          .query(GET_USER, username, new BeanHandler(User.class));
    }
    catch (SQLException e)
    {
      log.error("SQL Query caused error: " + e.getMessage());
      e.printStackTrace();
    }
    return result;
  }

  public int createUser(User user)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(CREATE_USER, new Object[] { user.getUsername(),
          user.getPassword(), user.getFullName(), user.getEmail() });
      updateUserRoles(user);
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

  public int updateUser(User user)
  {
    int insertCount = 0;
    try
    {
      insertCount = run.update(UPDATE_USER, new Object[] { user.getUsername(),
          user.getPassword(), user.getFullName(), user.getEmail(),
          user.getUsername() });
      updateUserRoles(user);
    }
    catch (SQLException e)
    {
      log.error(e);
      throw new DaoRuntimeException(e);
    }
    return insertCount;
  }

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

  @SuppressWarnings("unchecked")
  public Collection<String> getRoles()
  {
    List<String> result = null;
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

  @SuppressWarnings("unchecked")
  public Collection<String> getUserRoles(User user)
  {
    List<String> result = null;
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

}
