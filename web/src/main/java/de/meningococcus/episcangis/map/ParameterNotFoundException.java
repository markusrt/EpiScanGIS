package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ParameterNotFoundException extends Exception
{
  private static final long serialVersionUID = 1L;

  public ParameterNotFoundException()
  {
  }

  public ParameterNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public ParameterNotFoundException(String message)
  {
    super(message);
  }

  public ParameterNotFoundException(Throwable cause)
  {
    super(cause);
  }
}
