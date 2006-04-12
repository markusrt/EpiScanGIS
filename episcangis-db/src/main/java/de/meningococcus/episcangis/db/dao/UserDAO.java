package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.util.Collection;

import de.meningococcus.episcangis.db.model.User;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface UserDAO
{
  /**
   * @param username search for specific user by name
   * @return User object or null if not found
   */
  public User getUser(String username);

  /**
   * @param user User to insert in datastore 
   * @return number of inserted clusters
   */
  public int createUser(User user);

  /**
   * @param user User to update in datastore 
   * @return number of updated clusters
   */
  public int updateUser(User user);

  /**
   * @param user User to remove from datastore 
   * @return number of deleted users
   */
  public int deleteUser(User user);

  /**
   * @return collection of all possible roles, never null 
   */
  public Collection<String> getRoles();

  /**
   * @param user User to search roles for
   * @return collection of all matching roles, never null 
   */
  public Collection<String> getUserRoles(User user);
}
