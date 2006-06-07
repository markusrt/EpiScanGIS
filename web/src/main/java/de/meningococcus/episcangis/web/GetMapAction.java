package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.exporter.WmsImageExporter;

/**
 * @author Markus Reinhardt
 * 
 * @struts.action 
 *    name="activateUser" 
 *    path="/ActivateUser"
 *    scope="request"
 *    validate="false"
 *
 * @struts.action-forward 
 *    name="success" 
 *    path="/ListUsers.do"
 */
public class GetMapAction extends DownloadAction implements
    DownloadAction.StreamInfo
{
  private InputStream imageInputStream;

  private String imageContentType;

  protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    AbstractWmsMap mapBean = (AbstractWmsMap) request.getSession()
        .getAttribute("map");
    if (mapBean != null)
    {
      WmsImageExporter imageExporter = new WmsImageExporter(new File(
          getServlet().getServletContext().getRealPath("/WEB-INF/cache/")));
      mapBean.export(imageExporter);
      imageInputStream = imageExporter.getInputStream();
      imageContentType = imageExporter.getContentType();
    }
    else
    {
      throw new SessionBeanNotFoundException(
          "The session bean 'map' was not found.");
    }
    return this;
  }

  public String getContentType()
  {
    return imageContentType;
  }

  public InputStream getInputStream() throws IOException
  {
    return imageInputStream;
  }
}