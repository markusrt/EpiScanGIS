package de.meningococcus.episcangis.db;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DbunitExportDbToFlatXML
{
  public static void main(String[] args) throws Exception
  {
    // database connection
    Class driverClass = Class.forName("org.postgresql.Driver");
    Connection jdbcConnection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/epidegis_works", "tomcat", "DTh1eP");
    IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

    // partial database export
    QueryDataSet partialDataSet = new QueryDataSet(connection);
    partialDataSet.addTable("users");
    partialDataSet.addTable("user_roles");
    FlatXmlDataSet.write(partialDataSet, new FileOutputStream(
        "/home/mreinhardt/episcangis/db/src/test/resources/tomcat_dataset.xml"));

  }
}
