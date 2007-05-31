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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.exporter.WmsImageExporter;

/**
 * @author Markus Reinhardt
 */
public class GetMapLayerAction extends DownloadAction implements
    DownloadAction.StreamInfo
{
  private static Log log = LogFactory.getLog(GetMapLayerAction.class);

  private InputStream imageInputStream;

  private String imageContentType;

  protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    DynaActionForm dynaForm = (DynaActionForm) form;

    if (dynaForm.get("layer") != null)
    {
      AbstractWmsMap map = (AbstractWmsMap) request.getSession().getAttribute(
          "map");
      if (map == null)
      {
        throw new SessionBeanNotFoundException(
            "The session bean 'map' was not found.");
      }
      WmsImageExporter imageExporter = new WmsImageExporter(new File(
          getServlet().getServletContext().getRealPath("/WEB-INF/cache/")),
          (String) dynaForm.get("layer"));
      map.export(imageExporter);
      imageInputStream = imageExporter.getInputStream();
      imageContentType = imageExporter.getContentType();
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