package de.meningococcus.episcangis.transfer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/* ====================================================================
 *   Copyright (C) 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class TransferApp
{
  private static Log log = LogFactory.getLog(TransferApp.class);
  private static final String ABOUT = "EpiScanGIS file transfer tool v0.6-beta";


  private String username, keypassword, hostname, uploadDirectory;

  private final static String CONFIG_FILE = "epidegis-transfer.properties",
      CONFIG_USERNAME_KEY = "ssh.username",
      CONFIG_PASSWORD_KEY = "ssh.keypassword",
      CONFIG_HOSTNAME_KEY = "ssh.hostname",
      CONFIG_UPLOADDIR_KEY = "upload.directory";

  private File dsaKeyFile;

  public TransferApp(String dsaFileName) throws IllegalArgumentException,
      IOException, ConfigurationException, SQLException
  {
    Configuration config = new PropertiesConfiguration(CONFIG_FILE);
    if (config.containsKey(CONFIG_USERNAME_KEY))
    {
      username = config.getString(CONFIG_USERNAME_KEY, "");
    }
    else
    {
      throw new ConfigurationException("Could not find property '"
          + CONFIG_USERNAME_KEY + "' in " + CONFIG_FILE);
    }

    if (config.containsKey(CONFIG_PASSWORD_KEY))
    {
      keypassword = config.getString(CONFIG_PASSWORD_KEY, "");
    }
    else
    {
      throw new ConfigurationException("Could not find property '"
          + CONFIG_PASSWORD_KEY + "' in " + CONFIG_FILE);
    }

    if (config.containsKey(CONFIG_HOSTNAME_KEY))
    {
      hostname = config.getString(CONFIG_HOSTNAME_KEY, "");
    }
    else
    {
      throw new ConfigurationException("Could not find property '"
          + CONFIG_HOSTNAME_KEY + "' in " + CONFIG_FILE);
    }

    if (config.containsKey(CONFIG_UPLOADDIR_KEY))
    {
      uploadDirectory = config.getString(CONFIG_UPLOADDIR_KEY, "");
    }
    else
    {
      throw new ConfigurationException("Could not find property '"
          + CONFIG_UPLOADDIR_KEY + "' in " + CONFIG_FILE);
    }

    dsaKeyFile = new File(dsaFileName);
    if (!dsaKeyFile.exists())
    {
      throw new FileNotFoundException("DSA key file '" + dsaFileName
          + "' not found.");
    }

  }

  public void transferCsvFile(String fileName) throws FileNotFoundException
  {
    File csvFile = new File(fileName);
    if (csvFile.exists())
    {
      try
      {
        FastDateFormat fdf = FastDateFormat.getInstance("yyyyMMdd");
        final String uploadedFile = uploadDirectory + csvFile.getName();
        final String targetFilename = "epidegis_data-"
            + fdf.format(Calendar.getInstance()) + ".csv";
        final String targetFile = uploadDirectory + targetFilename;
        //final String tempFile = "/tmp/" + targetFilename;
        final String importerCommand = "java -jar " + uploadDirectory
            + "epidegis-importer.jar";

        Connection conn = establishConnection();
        int exitStatus = 0;
        log.info("Uploading to directory: " + uploadDirectory);
        SCPClient scp = new SCPClient(conn);
        scp.put(csvFile.getAbsolutePath(), uploadDirectory, "0400");

        String command = "mv " + uploadedFile + " " + targetFile;
        exitStatus = executeCommand(conn, command);

//        if (exitStatus == 0)
//        {
//          command = "cp " + targetFile + " " + tempFile;
//              //+ ";chcon -t postgresql_tmp_t " + tempFile;
//          exitStatus = executeCommand(conn, command);
//        }

        if (exitStatus == 0)
        {
          log
              .info("Upload finished, starting import - this takes some time...");
          command = importerCommand + " -c " + targetFile;
          exitStatus = executeCommand(conn, command);
        }
//        log.info("Cleaning up");
//        command = "rm " + tempFile;
//        executeCommand(conn, command);

        if (exitStatus != 0)
        {
          log.error("Import FAILED, see log for details");
        }

        conn.close();
        log.info("Connection closed");
      }
      catch (IOException e)
      {
        log.error(e.getMessage());
        if (e.getCause() != null)
        {
          log.error(e.getCause().getMessage());
        }
        if (log.isDebugEnabled())
        {
          log.debug("STACK TRACE:");
          e.printStackTrace();
        }
      }
    }
    else
    {
      throw new FileNotFoundException("File '" + csvFile.getAbsolutePath()
          + "' not found.");
    }
  }

  @SuppressWarnings("unchecked")
  private int executeCommand(Connection conn, String command)
  {
    int exitStatus = -1;
    try
    {
      Session sess = conn.openSession();
      log.debug("Executing command " + command);
      sess.execCommand(command);
      InputStream stdOut = new StreamGobbler(sess.getStdout());
      InputStream stdErr = new StreamGobbler(sess.getStderr());

      List<String> stdOutLines = IOUtils.readLines(stdOut);
      for (String stdOutLine : stdOutLines)
      {
        log.info(stdOutLine);
      }
      List<String> stdErrLines = IOUtils.readLines(stdErr);
      for (String stdErrLine : stdErrLines)
      {
        log.error(stdErrLine);
      }

      exitStatus = sess.getExitStatus();
      log.debug("Command " + command + " finished with exit status "
          + exitStatus);
      sess.close();

    }
    catch (IOException e)
    {
      log.error(e.getMessage());
      if (e.getCause() != null)
      {
        log.error(e.getCause().getMessage());
      }
      if (log.isDebugEnabled())
      {
        log.debug("STACK TRACE:");
        e.printStackTrace();
      }
    }
    return exitStatus;
  }

  private Connection establishConnection() throws IOException
  {
    Connection conn = new Connection(hostname);
    if (dsaKeyFile.exists())
    {
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPublicKey(username,
          dsaKeyFile, keypassword);
      if (isAuthenticated == false)
        throw new IOException("Authentication failed.");
      log.info("Connected to host " + hostname);
    }
    else
    {
      throw new FileNotFoundException("DSA key file '"
          + dsaKeyFile.getAbsolutePath() + "' not found.");
    }
    return conn;
  }

  public static void main(String[] args)
  {
    System.out.println(ABOUT);
    Option help = new Option("help", "print this message");
    Option csvFile = new Option("c", true, "CSV file to transfer on server");
    csvFile.setArgName("file");
    csvFile.setRequired(true);
    Option dsaKeyFile = new Option("d", true,
        "use given private key (DSA, PEM format)");
    dsaKeyFile.setArgName("file");
    dsaKeyFile.setRequired(true);

    Options options = new Options();

    options.addOption(help);
    options.addOption(csvFile);
    options.addOption(dsaKeyFile);

    CommandLineParser parser = new PosixParser();
    boolean printHelp = false;

    try
    {
      CommandLine line = parser.parse(options, args);
      if (line.hasOption("help"))
      {
        printHelp = true;
      }
      else
      {
        TransferApp exporter = new TransferApp(line.getOptionValue('d'));
        exporter.transferCsvFile(line.getOptionValue('c'));
      }

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
      if (log.isDebugEnabled())
      {
        e.printStackTrace();
      }
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
        formatter.printHelp("java -jar epidegis-transfer.jar", options, true);
      }
    }
  }
}
