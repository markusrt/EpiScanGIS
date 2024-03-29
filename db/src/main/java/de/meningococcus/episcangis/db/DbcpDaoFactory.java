package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.ClusterFeedbackDAO;

/**
 * This is a concrete factory which extends the abstract class DaoFactory. It
 * uses the Jakarta Commons DBCP library (<a
 * href="http://jakarta.apache.org/commons/dbcp">
 * http://jakarta.apache.org/commons/dbcp</a>) to create a datasource.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class DbcpDaoFactory extends DataSourceDaoFactory
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(DbcpDaoFactory.class);

  /**
   * One static datasource for all instances
   */
  private static DataSource dataSource = null;

  /**
   * The constructor establishes a database connection. The configuration
   * parameters are read from the config file epidegis-db.properties. A new
   * datasource is created on the first call of this constructor. TODO improve
   * error handling
   */
  public DbcpDaoFactory()
  {
    String dsUrl = "", user = "", password = "", driver = "";
    try
    {
      if (dataSource == null || dataSource.getConnection() == null)
      {
        Configuration configuration;
        // read the configuration
        configuration = new PropertiesConfiguration(configFile);
        dsUrl = configuration.getString("DbcpDaoFactory.url", dsUrl);
        user = configuration.getString("DbcpDaoFactory.user", user);
        password = configuration.getString("DbcpDaoFactory.password", password);
        driver = configuration.getString("DbcpDaoFactory.driver", driver);

        log.debug("Initializing the datasource... ('" + dsUrl + "')");
        BasicDataSource bds = new BasicDataSource();
        // TODO driver class name in config file
        bds.setDriverClassName(driver);
        bds.setUsername(user);
        bds.setPassword(password);
        bds.setUrl(dsUrl);

        if (bds.getConnection() != null)
        {
          dataSource = bds;
          log.debug("Initialization SUCCESS!");
        }
      }
    }
    catch (SQLException e)
    {
      log.error("Exception while connecting to database " + dsUrl + ": "
          + e.getMessage());
      throw new DaoRuntimeException("Exception while connecting to database "
          + dsUrl, e);
    }
    catch (ConfigurationException e)
    {
      log.error("Exception while loading configuration " + configFile + ": "
          + e.getMessage());
      throw new DaoRuntimeException("Exception while loading configuration "
          + configFile, e);
    }
  }

  @Override
  protected DataSource getDataSource()
  {
    return dataSource;
  }
}
