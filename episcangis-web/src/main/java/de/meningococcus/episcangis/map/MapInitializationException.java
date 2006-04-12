package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class MapInitializationException extends Exception
{
  private static final long serialVersionUID = 1L;

  public MapInitializationException()
  {
  }

  public MapInitializationException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public MapInitializationException(String message)
  {
    super(message);
  }

  public MapInitializationException(Throwable cause)
  {
    super(cause);
  }
}
