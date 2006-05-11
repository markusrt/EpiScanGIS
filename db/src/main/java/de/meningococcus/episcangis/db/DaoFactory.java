package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.db.dao.AreaDAO;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO;
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.dao.ImportDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.dao.UserDAO;

/**
 * This abstract factory is based on the DAO pattern by Sun (<a
 * href="http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html">
 * http://java.sun.com/blueprints/corej2eepatterns/Patterns/DataAccessObject.html</a>).
 * It allows to change the underlying datastore or connection type without
 * rewriting db dependent code. Each supported datastore type is defined by one
 * constant. There will be a get-method for each DAO that can be created. The
 * concrete factories will have to implement these methods.
 * 
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public abstract class DaoFactory
{
  /**
   * This classes logger.
   */
  private static Log log = LogFactory.getLog(DaoFactory.class);

  /**
   * Stores the default factory returned by getDaoFactory().
   */
  private static DaoFactory singletonDaoFactory;

  protected static String configFile = "conf/epidegis-db.properties";

  /**
   * Creates a new DAOFactory of specified type.
   * 
   * @param whichFactory
   *          possible values DaoFactoryType.PGSQL, DaoFactoryType.JNDI
   * @return DAO factory extending this class
   */
  public static synchronized DaoFactory getDaoFactory(
      DaoFactoryType whichFactory)
  {
    if (singletonDaoFactory == null)
    {
      switch (whichFactory)
      {
      case DBCP:
        singletonDaoFactory = new DbcpDaoFactory();
      case JNDI:
        singletonDaoFactory = new JNDIDaoFactory();
      }
    }
    return singletonDaoFactory;
  }

  /**
   * Creates a new DAOFactory of the default type specified in
   * epidegis-db.properties. TODO Improve error handling
   * 
   * @return DAO factory extending this class
   */
  public static synchronized DaoFactory getDaoFactory()
  {
    if (singletonDaoFactory == null)
    {
      try
      {
        String defaultFactoryName = "";
        Configuration configuration = new PropertiesConfiguration(configFile);
        defaultFactoryName = configuration.getString("default.daoFactory");
        singletonDaoFactory = (DaoFactory) Class.forName(defaultFactoryName)
            .newInstance();
      }
      catch (Exception e)
      {
        log.error("Exception while loading " + configFile + ": "
            + e.getMessage());
        throw new DaoRuntimeException(
            "Unable to get default dao factory, reason: " + e.getMessage(), e);
      }
    }
    return singletonDaoFactory;
  }

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the ReportedCaseDAO interface.
   * 
   * @return A DAO implementing the interface ReportedCaseDAO
   */
  public abstract ReportedCaseDAO getReportedCaseDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the AreaTypeDAO interface.
   * 
   * @return A DAO implementing the interface AreaTypeDAO
   */
  public abstract AreaTypeDAO getAreaTypeDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the CaseTypeDAO interface.
   * 
   * @return A DAO implementing the interface CaseTypeDAO
   */
  public abstract CaseTypeDAO getCaseTypeDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the CaseTypeAttributeDAO interface.
   * 
   * @return A DAO implementing the interface CaseTypeAttributeDAO
   */
  public abstract CaseTypeAttributeDAO getCaseTypeAttributeDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the SatScanDAO interface.
   * 
   * @return A DAO implementing the interface SatScanDAO
   */
  public abstract SatScanDAO getSatScanDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the AreaDAO interface.
   * 
   * @return A DAO implementing the interface AreaDAO
   */
  public abstract AreaDAO getAreaDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the ImportDAO interface.
   * 
   * @return A DAO implementing the interface ImportDAO
   */
  public abstract ImportDAO getImportDAO();

  /**
   * This method needs to be overwritten by subclasses. It constructs a concrete
   * DAO which implements the UserDAO interface.
   * 
   * @return A DAO implementing the interface ImportDAO
   */
  public abstract UserDAO getUserDAO();

  /**
   * @param configFile
   *          The configFile to set.
   */
  public static void setConfigFile(String configFile)
  {
    DaoFactory.configFile = configFile;
  }

}
