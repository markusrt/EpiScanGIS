package de.meningococcus.episcangis.db.dao;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.File;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public interface ImportDAO
{
  /**
   * Imports CSV-file containing cases into the datastore
   * @param csvFile file to import
   * @return 0 on success, -1 on error
   */
  public int importCsvFile(File csvFile);
}
