package de.meningococcus.episcangis.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.Configurable;
import org.hibernate.service.spi.Stoppable;

public class HibernateConnectionProvider implements ConnectionProvider, Configurable, Stoppable
{

  public static DataSourceDaoFactory connectionPool = (DataSourceDaoFactory) DaoFactory
      .getDaoFactory();

  @Override
  public void configure(Map configurationValues)
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

  @Override
  public Connection getConnection() throws SQLException
  {
    return connectionPool.getDataSource().getConnection();
  }

  @Override
  public void closeConnection(Connection conn) throws SQLException
  {
    conn.close();
  }

  @Override
  public void stop()
  {
  }

  @Override
  public boolean supportsAggressiveRelease()
  {
    return false;
  }

  @Override
  public boolean isUnwrappableAs(Class unwrapType)
  {
    return ConnectionProvider.class.equals(unwrapType) ||
        HibernateConnectionProvider.class.isAssignableFrom(unwrapType);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T unwrap(Class<T> unwrapType)
  {
    if (isUnwrappableAs(unwrapType))
    {
      return (T) this;
    }
    throw new IllegalArgumentException("Cannot unwrap to " + unwrapType);
  }

}
