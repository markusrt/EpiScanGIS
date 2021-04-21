package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

/**
 * This class wraps a QueryRunner of DbUtils that is used by subclasses to
 * operate on the database. A DataSource is needed to establish database
 * connections.
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
abstract class DbUtilsDAO
{
  /**
   * Executes the SQL queries
   */
  protected QueryRunner run = null;

  DbUtilsDAO(DataSource dataSource)
  {
    run = new QueryRunner(dataSource);
  }
}
