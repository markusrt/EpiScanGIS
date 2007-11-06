package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import de.meningococcus.episcangis.db.dao.AreaDAO;
import de.meningococcus.episcangis.db.dao.AreaTypeDAO;
import de.meningococcus.episcangis.db.dao.CaseTypeAttributeDAO;
import de.meningococcus.episcangis.db.dao.CaseTypeDAO;
import de.meningococcus.episcangis.db.dao.ClusterFeedbackDAO;
import de.meningococcus.episcangis.db.dao.ImportDAO;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.dao.UserDAO;

/**
 * This abstract factory uses a DataSource to build it's DAOs. Subclasses need
 * to implement the getDataSource() method.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
abstract class DataSourceDaoFactory extends DaoFactory
{
  /**
   * Each subclass needs to provide a DataSource object for database
   * connections. This field should be static to be shared by all DaoFactory
   * objects.
   * @return DataSource for database connection
   */
  abstract protected DataSource getDataSource();

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getReportedCaseDAO()
   */
  public ReportedCaseDAO getReportedCaseDAO()
  {
    return new PgSQLReportedCaseDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getAreaTypeDAO()
   */
  @Override
  public AreaTypeDAO getAreaTypeDAO()
  {
    return new PgSQLAreaTypeDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getCaseTypeDAO()
   */
  @Override
  public CaseTypeDAO getCaseTypeDAO()
  {
    return new PgSQLCaseTypeDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getCaseTypeAttributeDAO()
   */
  @Override
  public CaseTypeAttributeDAO getCaseTypeAttributeDAO()
  {
    return new PgSQLCaseTypeAttributeDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getSatScanDAO()
   */
  @Override
  public SatScanDAO getSatScanDAO()
  {
    return new PgSQLSatScanDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getAreaDAO()
   */
  @Override
  public AreaDAO getAreaDAO()
  {
    return new PgSQLAreaDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getImportDAO()
   */
  @Override
  public ImportDAO getImportDAO()
  {
    return new PgSQLImportDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getImportDAO()
   */
  @Override
  public UserDAO getUserDAO()
  {
    return new PgSQLUserDAO(getDataSource());
  }

  /*
   * (non-Javadoc)
   * @see de.meningococcus.episcangis.db.DaoFactory#getImportDAO()
   */
  @Override
  public ClusterFeedbackDAO getClusterFeedbackDAO()
  {
    return new PgSQLClusterFeedbackDAO();
  }
  
  @Override
  public Connection getConnection() throws SQLException {
  	return getDataSource().getConnection();
  }

}
