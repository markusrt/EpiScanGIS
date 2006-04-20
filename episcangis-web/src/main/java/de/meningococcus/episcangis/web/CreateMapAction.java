package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.NrzmMap;
import de.meningococcus.episcangis.map.exporter.XmlExporter;

/**
 * @author Markus Reinhardt
 */
public class CreateMapAction extends DownloadAction implements
    DownloadAction.StreamInfo
{
  private InputStream xmlInputStream;

  private String contentType;

  @SuppressWarnings("unchecked")
  protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
        "map");

    if (map == null)
    {
      // Clear cache directory if database has changed
      File cacheDirectory = new File(getServlet().getServletContext()
          .getRealPath("/WEB-INF/cache/"));
      Collection<File> cachedFiles = FileUtils.listFiles(cacheDirectory,
          new PrefixFileFilter("cache"), null);
      long oldestFile = Long.MAX_VALUE;
      for( File file : cachedFiles ) {
        if(file.lastModified() < oldestFile ) {
          oldestFile = file.lastModified();
        }
      }
      ReportedCaseDAO rcDao = DaoFactory.getDaoFactory().getReportedCaseDAO();
      Timestamp lastCaseChange = rcDao.lastChange();
      if(lastCaseChange.after(new Date(oldestFile))) {
        FileUtils.cleanDirectory(cacheDirectory);
      }
      
      map = new NrzmMap();
      request.getSession().setAttribute("map", map);
    }
    XmlExporter xmlExporter = new XmlExporter();
    map.export(xmlExporter);
    xmlInputStream = xmlExporter.getInputStream();
    contentType = xmlExporter.getContentType();

    return this;
  }

  public String getContentType()
  {
    return contentType;
  }

  public InputStream getInputStream() throws IOException
  {
    return xmlInputStream;
  }

}
