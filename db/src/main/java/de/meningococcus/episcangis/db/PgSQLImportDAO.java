package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.nio.charset.StandardCharsets;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.ImportDAO;

/**
 * Implementation of the DAO ImportDAO.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class PgSQLImportDAO extends DbUtilsDAO implements ImportDAO
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(PgSQLImportDAO.class);

  private static final int COLUMN_COUNT = 10;

  PgSQLImportDAO(DataSource dataSource)
  {
    super(dataSource);
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.dao.ImportDAO#importCsvFile(java.io.File)
   */
  public int importCsvFile(File csvFile)
  {
    ResultSetHandler h = new ScalarHandler();

    int exitStatus = -1;
    try
    {
      List lines = FileUtils.readLines(csvFile, StandardCharsets.UTF_8);
      int lineCount = 0;
      Iterator lineIterator = lines.iterator();
      log.debug("Clearing temporary import table");
      run.update("DELETE FROM nrzm_imprt");
      log.info("Processing " + lines.size() + " line(s) in CSV file...");
      while (lineIterator.hasNext())
      {
        lineCount++;
        String line = (String) lineIterator.next();
        String[] columns = line.split(";");
        if (columns.length != COLUMN_COUNT)
        {
          throw new IOException("Unexpected column count in line " + lineCount
              + " while reading CSV file " + csvFile.getName() + " ( got "
              + columns.length + ", expected " + COLUMN_COUNT + ").");
        }
        if (run.update("INSERT INTO nrzm_imprt VALUES (?,?,?,?,?,?,?,?,?,?)",
            new Object[] { Integer.valueOf(columns[0]), columns[1], columns[2],
                columns[3], columns[4], columns[5], columns[6], columns[7],
                columns[8], columns[9] }) == 1)
        {
          log.debug("line " + lineCount + " [success]");
        }
        else {
          log.error("line " + lineCount + " [failure]");
        }

      }
      log.info("Refreshing data and recalculating spatial dependencies...");
      exitStatus = (Integer) (run.query("SELECT episcangis_nrzm_import()", h));
    }
    catch (SQLException e)
    {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    catch (IOException e)
    {
      log.error(e.getMessage());
      e.printStackTrace();
    }
    return exitStatus;
  }
}