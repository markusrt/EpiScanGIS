package de.meningococcus.episcangis.db.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class User
{
  /**
   * The standard name of the digest algorithm to use for password. See Appendix
   * A in the Java <a
   * href="http://java.sun.com/j2se/1.5.0/docs/guide/security/CryptoSpec.html#AppA">Cryptography
   * Architecture API Specification & Reference</a> for information about
   * standard algorithm names.
   */
  public static final String passwordDigestAlgorithm = "SHA-1";

  private String username, password, fullName, email, phone, organization,
      message;

  private Date registrationDate, lastLogin;

  private Collection<String> roles = new Vector<String>();

  private static Log log = LogFactory.getLog(User.class);

  public void setPassword(String password)
  {
    this.password = digestPassword(password);
  }

  public boolean hasPassword(String password)
  {
    return digestPassword(password).equals(this.password);
  }

  /**
   * @param oldPassword
   * @param newPassword
   * @return boolean if password was changed successful
   */
  public boolean changePassword(String oldPassword, String newPassword)
  {
    boolean passwordChanged = false;
    if (hasPassword(oldPassword))
    {
      setPassword(newPassword);
      passwordChanged = true;
    }
    return passwordChanged;
  }

  /**
   * Digest password with specified digest algorithm.
   * @param password
   * @return hex encoded digested password
   */
  private static String digestPassword(String password)
  {
    String digestedPassword = password;
    try
    {
      MessageDigest md = MessageDigest.getInstance(passwordDigestAlgorithm);
      byte[] digestedBytes = md.digest(password.getBytes());
      StringBuilder sb = new StringBuilder(digestedBytes.length * 2);
      for (int i = 0; i < digestedBytes.length; i++)
      {
        sb.append(convertDigit((int) (digestedBytes[i] >> 4)));
        sb.append(convertDigit((int) (digestedBytes[i] & 0x0f)));
      }
      digestedPassword = sb.toString();
    }
    catch (NoSuchAlgorithmException e)
    {
      log.fatal("Unable to digest password with '" + passwordDigestAlgorithm
          + "' digest algorithm", e);
    }
    return digestedPassword;
  }

  /**
   * Method used from org.apache.catalina.util Copyright 1999,2004 The Apache
   * Software Foundation. Licensed under the Apache License, Version 2.0 <a
   * href="http://www.apache.org/licenses/LICENSE-2.0"></a>
   */
  private static char convertDigit(int value)
  {
    value &= 0x0f;
    if (value >= 10)
      return ((char) (value - 10 + 'a'));
    else
      return ((char) (value + '0'));
  }

  public String getPassword()
  {
    return password;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String userName)
  {
    this.username = userName;
  }

  public String getFullName()
  {
    return fullName;
  }

  public void setFullName(String fullName)
  {
    this.fullName = fullName;
  }

  public boolean isInRole(String role)
  {
    return roles.contains(role);
  }

  public void addRole(String role)
  {
    if (!roles.contains(role))
    {
      roles.add(role);
    }
  }

  public Collection<String> getRoles()
  {
    return roles;
  }

  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public String getOrganization()
  {
    return organization;
  }

  public void setOrganization(String organization)
  {
    this.organization = organization;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public Date getLastLogin()
  {
    return lastLogin;
  }

  public void setLastLogin(Date lastLogin)
  {
    this.lastLogin = lastLogin;
  }

  public Date getRegistrationDate()
  {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate)
  {
    this.registrationDate = registrationDate;
  }
}
