package de.meningococcus.episcangis.importer;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ImportDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ImportApp
{
  private static Log log = LogFactory.getLog(ImportApp.class);

  private static final String ABOUT = "EpiScanGIS database import tool v0.6-beta";

  public void importCSV(String fileName) throws FileNotFoundException
  {
    ImportDAO iDao = DaoFactory.getDaoFactory().getImportDAO();
    ReportedCaseDAO rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
    File csvFile = new File(fileName);
    if (csvFile.exists())
    {
      log.info("Database contains " + rcDao.countCases() + " cases");
      log.info("Starting import");
      if (iDao.importCsvFile(csvFile) == 0)
      {
        log.info("Import finished");
      }
      else
      {
        log.error("Import failed");
      }
      log.info("Database now contains " + rcDao.countCases() + " cases");
    }
    else
    {
      throw new FileNotFoundException("File '" + csvFile.getAbsolutePath()
          + "' not found.");
    }
  }

  public static void main(String[] args)
  {
    System.out.println(ABOUT);
    Option help = new Option("help", "print this message");
    Option csvFile = new Option("c", true, "CSV file to import");
    csvFile.setArgName("file");
    csvFile.setRequired(true);

    Options options = new Options();

    options.addOption(help);
    options.addOption(csvFile);

    CommandLineParser parser = new PosixParser();
    boolean printHelp = false;

    try
    {
      CommandLine commandLine = parser.parse(options, args);
      if (commandLine.hasOption("help"))
      {
        printHelp = true;
      }
      ImportApp importer = new ImportApp();
      importer.importCSV(commandLine.getOptionValue('c'));
    }
    catch (MissingOptionException e)
    {
      printHelp = true;
    }
    catch (ParseException e)
    {
      log
          .error("Commandline option parsing failed.  Reason: "
              + e.getMessage());
      e.printStackTrace();
    }
    catch (Exception e)
    {
      log.error(e.getMessage());
      if (log.isDebugEnabled())
      {
        e.printStackTrace();
      }
    }
    finally
    {
      if (printHelp)
      {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("epidegis-transfer", options, true);
      }
    }
  }
}
