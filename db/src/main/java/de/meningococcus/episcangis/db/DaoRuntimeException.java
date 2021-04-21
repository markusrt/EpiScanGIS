package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * Simple Exception for all possible runtime errors.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class DaoRuntimeException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public DaoRuntimeException()
  {
  }

  /**
   * @param message error message
   * @param cause causing exception
   */
  public DaoRuntimeException(String message, Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @param message error message
   */
  public DaoRuntimeException(String message)
  {
    super(message);
  }

  /**
   * @param cause causing exception
   */
  public DaoRuntimeException(Throwable cause)
  {
    super(cause);
  }
}
