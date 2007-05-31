package de.meningococcus.episcangis.web;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class GlobalSettings
{
  private static Log log = LogFactory.getLog(GlobalSettings.class);

  public static final String FORWARD_ERROR = "error",
  FORWARD_SUCCESS = "success", FORWARD_INDEX = "index", FORWARD_INPUT = "input", FORWARD_LIST="list",
  FORWARD_MAPBROWSER = "mapbrowser", MAPBROWSER_SITE_URL="mapbrowser.vm",
  FORWARD_DENIED="denied", ROLE_ADMIN="admin", ROLE_NRZM="nrzm",
  ROLE_PUBLIC_HEALTH="public_health";

  public static final String CONFIGFILE = "conf/epidegis-web.properties";

  public static String getConfigurationProperty(String property)
      throws ConfigurationException
  {
    Configuration config;
    String propertyValue = null;
    try
    {
      config = new PropertiesConfiguration(CONFIGFILE);
      propertyValue = config.getString(property, "");
    }
    catch (ConfigurationException e)
    {
      log.warn("Exception while loading " + CONFIGFILE + ": " + e.getMessage());
      throw e;
    }
    return propertyValue;
  }
}
