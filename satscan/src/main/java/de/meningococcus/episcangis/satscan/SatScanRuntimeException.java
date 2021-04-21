package de.meningococcus.episcangis.satscan;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 *   All Rights Reserved.
 * ====================================================================
 */

/**
 * Simple Exception for all possible runtime errors. Allows the specification of
 * the causing exception.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class SatScanRuntimeException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public SatScanRuntimeException()
  {
  }

  /**
   * @param message
   * @param cause
   */
  public SatScanRuntimeException(String message, Throwable cause)
  {
    super(message, cause);
  }

  /**
   * @param message
   */
  public SatScanRuntimeException(String message)
  {
    super(message);
  }

  /**
   * @param cause
   */
  public SatScanRuntimeException(Throwable cause)
  {
    super(cause);
  }
}
