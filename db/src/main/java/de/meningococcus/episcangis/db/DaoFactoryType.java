package de.meningococcus.episcangis.db;

/*
 * ====================================================================
 * Copyright �2006 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public enum DaoFactoryType {
  /**
   * Dpcp drive database connections are defined by this constant. The
   * implementing Factory is DbcpDaoFactory
   * @see DbcpDaoFactory
   */
  DBCP,
  /**
   * The Tomcat servlet container offers JNDI data sources. The JNDIDaoFactory
   * uses this feature.
   * @see JNDIDaoFactory
   */
  JNDI
}
