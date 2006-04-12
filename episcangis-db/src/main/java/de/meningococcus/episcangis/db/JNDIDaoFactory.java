package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * This is a concrete factory which extends the abstract class DaoFactory. It
 * uses JNDI (<a href="http://java.sun.com/products/jndi/">
 * http://java.sun.com/products/jndi/</a>) to create a datasource.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
final class JNDIDaoFactory extends DataSourceDaoFactory
{
  /**
   * This classes logger
   */
  private static Log log = LogFactory.getLog(JNDIDaoFactory.class);
  
  /**
   * One static datasource for all instances
   */
  private static DataSource dataSource = null;

  /**
   * The constructor searches for the context with the name jdbc/dao and gets
   * the DataSource object, which is bound to this name. This is only done on
   * the first call of this constructor. TODO improve error handling
   */
  public JNDIDaoFactory()
  {
    try
    {
      if (dataSource == null || dataSource.getConnection() == null)
      {
        String contextName = "jdbc/dao";

        log.info("Initializing the JNDI datasource...");
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        log.info("Looking up resource '" + contextName + "'");
        dataSource = (DataSource) envCtx.lookup(contextName);

        if (dataSource.getConnection() == null)
        {
          throw new NullPointerException("Connection object is null.");
        }
        log
            .info("JNDI datasource connected success to db "
                + dataSource.getConnection().getMetaData()
                    .getDatabaseProductName());
      }
    }
    catch (SQLException e)
    {
      throw new DaoRuntimeException("Tomcat JNDI setup failed", e);
    }
    catch (NamingException e)
    {
      throw new DaoRuntimeException("Tomcat JNDI setup failed", e);
    }
    catch (NullPointerException e)
    {
      throw new DaoRuntimeException("Tomcat JNDI setup failed", e);
    }
  }

  @Override
  protected DataSource getDataSource()
  {
    return dataSource;
  }
}
