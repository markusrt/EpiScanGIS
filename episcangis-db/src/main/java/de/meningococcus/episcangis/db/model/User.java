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

  private String username, password, title, forename, lastname, email, phone, 
    organisation, department, domain, message;

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
  
  /**
   * @return Returns the roles.
   */
  public Collection<String> getRoles()
  {
    return roles;
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

  /**
   * @return Returns the department.
   */
  public String getDepartment()
  {
    return department;
  }

  /**
   * @param department The department to set.
   */
  public void setDepartment(String department)
  {
    this.department = department;
  }

  /**
   * @return Returns the domain.
   */
  public String getDomain()
  {
    return domain;
  }

  /**
   * @param domain The domain to set.
   */
  public void setDomain(String domain)
  {
    this.domain = domain;
  }

  /**
   * @return Returns the email.
   */
  public String getEmail()
  {
    return email;
  }

  /**
   * @param email The email to set.
   */
  public void setEmail(String email)
  {
    this.email = email;
  }

  /**
   * @return Returns the forename.
   */
  public String getForename()
  {
    return forename;
  }

  /**
   * @param forename The forename to set.
   */
  public void setForename(String forename)
  {
    this.forename = forename;
  }

  /**
   * @return Returns the lastLogin.
   */
  public Date getLastLogin()
  {
    return lastLogin;
  }

  /**
   * @param lastLogin The lastLogin to set.
   */
  public void setLastLogin(Date lastLogin)
  {
    this.lastLogin = lastLogin;
  }

  /**
   * @return Returns the lastname.
   */
  public String getLastname()
  {
    return lastname;
  }

  /**
   * @param lastname The lastname to set.
   */
  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  /**
   * @return Returns the message.
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * @param message The message to set.
   */
  public void setMessage(String message)
  {
    this.message = message;
  }

  /**
   * @return Returns the organisation.
   */
  public String getOrganisation()
  {
    return organisation;
  }

  /**
   * @param organisation The organisation to set.
   */
  public void setOrganisation(String organisation)
  {
    this.organisation = organisation;
  }

  /**
   * @return Returns the phone.
   */
  public String getPhone()
  {
    return phone;
  }

  /**
   * @param phone The phone to set.
   */
  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  /**
   * @return Returns the registrationDate.
   */
  public Date getRegistrationDate()
  {
    return registrationDate;
  }

  /**
   * @param registrationDate The registrationDate to set.
   */
  public void setRegistrationDate(Date registrationDate)
  {
    this.registrationDate = registrationDate;
  }

  /**
   * @return Returns the title.
   */
  public String getTitle()
  {
    return title;
  }

  /**
   * @param title The title to set.
   */
  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * @return Returns the username.
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * @param username The username to set.
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

  /**
   * @return Returns the password.
   */
  public String getPassword()
  {
    return password;
  }
}
