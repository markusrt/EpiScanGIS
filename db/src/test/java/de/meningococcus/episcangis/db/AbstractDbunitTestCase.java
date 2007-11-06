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
        .setConfigFile("src/test/resources/epidegis-db.properties");
  }

  @Override
  protected IDatabaseConnection getConnection() throws Exception
  {
    return new DatabaseConnection(DaoFactory.getDaoFactory().getConnection());
  }

  @Override
  protected IDataSet getDataSet() throws Exception
  {
    return new FlatXmlDataSet(new FileInputStream(
        "src/test/resources/tomcat_dataset.xml"));
  }

}
