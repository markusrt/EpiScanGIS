package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ManageUsersException extends Exception
{
  private static final long serialVersionUID = 1L;

  public ManageUsersException()
  {
  }

  public ManageUsersException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public ManageUsersException(String message)
  {
    super(message);
  }

  public ManageUsersException(Throwable cause)
  {
    super(cause);
  }
}
