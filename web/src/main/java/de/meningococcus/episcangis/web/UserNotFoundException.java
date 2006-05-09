package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class UserNotFoundException extends Exception
{
  private static final long serialVersionUID = 1L;

  public UserNotFoundException()
  {
  }

  public UserNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public UserNotFoundException(String message)
  {
    super(message);
  }

  public UserNotFoundException(Throwable cause)
  {
    super(cause);
  }
}
