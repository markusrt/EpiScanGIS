package de.meningococcus.episcangis.map.exporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.meningococcus.episcangis.map.LayerNotFoundException;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class WmsImageExporter extends AbstractWmsExporter
{
  private static Log log = LogFactory.getLog(WmsImageExporter.class);

  private String format = "image/gif";

  String singleLayer = null;

  private boolean doCaching = false;

  private File cacheDir;

  public WmsImageExporter(File cacheDirectory)
  {
    if (cacheDirectory != null && cacheDirectory.exists())
    {
      this.cacheDir = cacheDirectory;
      // TODO fix bug with file caching
      //doCaching = true;
    }
    else
    {
      log.warn("Cache directory '" + cacheDirectory.getName()
          + "' does not exits, caching disabled");
    }
  }

  public WmsImageExporter(File cacheDirectory, String layerName)
  {
    this(cacheDirectory);
    singleLayer = layerName;
  }

  public String getContentType()
  {
    return format;
  }

  public InputStream getInputStream() throws IOException,
      LayerNotFoundException
  {
    StringBuilder urlBuilder = new StringBuilder(100);
    if (singleLayer != null)
    {
      urlBuilder.append(buildLayerUrl(singleLayer));
      urlBuilder.append("&TRANSPARENT=").append(true);
    }
    else
    {
      urlBuilder.append(buildMapUrl());
    }
    urlBuilder.append("&FORMAT=").append(format);

    URL imageUrl = new URL(urlBuilder.toString());

    log.debug("Map URL: " + imageUrl.toString());
    if (doCaching)
    {
      return getCachedFileInputStream(imageUrl);
    }
    else
    {
      HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
      return conn.getInputStream();
    }
  }

  private FileInputStream getCachedFileInputStream(URL imageUrl)
      throws IOException
  {
    File cacheFile = new File(cacheDir + System.getProperty("file.separator")
        + "cache" + imageUrl.hashCode());

    File urlFile = new File(cacheFile.getParent()
        + System.getProperty("file.separator") + cacheFile.getName()
        + ".mapurl");
    log.debug("Cache file: " + cacheFile.getAbsolutePath());

    if (urlFile.createNewFile())
    {
      FileUtils.writeStringToFile(urlFile, imageUrl.toString(), "utf-8");
    }
    if (urlFile.exists())
    {
      log.info("File '" + urlFile.getAbsolutePath()
          + "' exists, comparing mapurl.");
      String mapUrl = FileUtils.readFileToString(urlFile, "utf-8");
      if (!imageUrl.toString().equals(mapUrl))
      {
        log.debug("Deleting old cache file, mapurl doesn't match.");
        cacheFile.delete();
      }
      if (cacheFile.createNewFile())
      {
        log.debug("Creating cache file: " + cacheFile.getAbsolutePath());
        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
        OutputStream fos = new FileOutputStream(cacheFile);
        IOUtils.copy(conn.getInputStream(), fos);
      }
      else
      {
        log.debug("Using cached file.");
      }
    }
    return new FileInputStream(cacheFile);
  }

  @Override
  protected String getRequest()
  {
    return "&REQUEST=GetMap";
  }

}
