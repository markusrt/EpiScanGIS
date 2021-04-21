package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class InvalidParameterValueException extends Exception
{
  private static final long serialVersionUID = 1L;

  public InvalidParameterValueException()
  {}

  public InvalidParameterValueException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public InvalidParameterValueException(String message)
  {
    super(message);
  }

  public InvalidParameterValueException(Throwable cause)
  {
    super(cause);
  }
}
