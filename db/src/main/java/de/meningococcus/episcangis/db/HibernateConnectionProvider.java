package de.meningococcus.episcangis.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.connection.ConnectionProvider;

public class HibernateConnectionProvider implements ConnectionProvider
{

  public static DataSourceDaoFactory connectionPool = (DataSourceDaoFactory) DaoFactory
      .getDaoFactory();

  public void configure(Properties props) throws HibernateException
  {
    if (connectionPool == null)
    {
      throw new HibernateException("Connection pool must be set.");
    }
    if (!(connectionPool instanceof DataSourceDaoFactory))
    {
      throw new HibernateException(
          "Connection pool has to be an instance of DataSourceDaoFactory.");
    }
  }

  public Connection getConnection() throws SQLException
  {
    return connectionPool.getDataSource().getConnection();
  }

  public void closeConnection(Connection conn) throws SQLException
  {
    conn.close();
  }

  public void close()
  {
  }

  public boolean supportsAggressiveRelease()
  {
    return false;
  }

}
