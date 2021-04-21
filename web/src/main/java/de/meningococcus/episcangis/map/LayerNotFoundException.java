package de.meningococcus.episcangis.map;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class LayerNotFoundException extends Exception
{
  private static final long serialVersionUID = 1L;

  public LayerNotFoundException()
  {
  }

  public LayerNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public LayerNotFoundException(String message)
  {
    super(message);
  }

  public LayerNotFoundException(Throwable cause)
  {
    super(cause);
  }
}
