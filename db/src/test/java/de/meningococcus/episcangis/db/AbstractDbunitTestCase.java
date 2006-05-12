package de.meningococcus.episcangis.db;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public abstract class AbstractDbunitTestCase extends DatabaseTestCase
{
  public AbstractDbunitTestCase(String testName)
  {
    super(testName);
    DaoFactory
        .setConfigFile("/home/mreinhardt/episcangis/db/src/test/resources/epidegis-db.properties");
  }

  @Override
  protected IDatabaseConnection getConnection() throws Exception
  {
    Class driverClass = Class.forName("org.postgresql.Driver");
    Connection jdbcConnection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/episcangis_test", "tomcat", "DTh1eP");
    return new DatabaseConnection(jdbcConnection);
  }

  @Override
  protected IDataSet getDataSet() throws Exception
  {
    return new FlatXmlDataSet(new FileInputStream(
        "/home/mreinhardt/episcangis/db/src/test/resources/tomcat_dataset.xml"));
  }

}
