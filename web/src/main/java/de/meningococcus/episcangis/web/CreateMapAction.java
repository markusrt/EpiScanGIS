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
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.db.DaoFactory;
import de.meningococcus.episcangis.db.dao.ReportedCaseDAO;
import de.meningococcus.episcangis.db.model.User;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.NrzmMap;
import de.meningococcus.episcangis.map.PublicHealthMap;
import de.meningococcus.episcangis.map.PublicMap;
import de.meningococcus.episcangis.map.exporter.XmlExporter;

/**
 * @author Markus Reinhardt
 * 
 * @struts.action 
 *    name="createMap" 
 *    path="/Map2"
 *    scope="request"
 *    validate="false"
 *    
 * @struts.action-exception
 *    type="java.lang.Exception"
 *    key="error"
 *    path="/WEB-INF/errors/xmlExceptionResult.jsp"
 *
 * @struts.action-forward 
 *    name="success" 
 *    path="/ListUsers.do"
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
    HttpSession session = request.getSession();
    CreateMapFormBean createMap = (CreateMapFormBean)form;
    int width = createMap.getWidth();
    int height = createMap.getHeight();

    if (map == null || width != map.getWidth() || height != map.getHeight())
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
      
      User user = (User)session.getAttribute("user");
      if( user != null && user.isInRole("nrzm")) {
        map = new NrzmMap(width,height);
      }
      else if( user != null && user.isInRole("public_health")) {
        map = new PublicHealthMap(width,height);
      }
      else {
        map = new PublicMap(width,height);
      }
      
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
