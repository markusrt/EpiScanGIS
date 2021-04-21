package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class SessionBeanNotFoundException extends Exception
{
  private static final long serialVersionUID = 1L;

  public SessionBeanNotFoundException()
  {
  }

  public SessionBeanNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public SessionBeanNotFoundException(String message)
  {
    super(message);
  }

  public SessionBeanNotFoundException(Throwable cause)
  {
    super(cause);
  }
}
