package de.meningococcus.episcangis.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.db.dao.SatScanDAO;
import de.meningococcus.episcangis.db.model.SatScanCluster;
import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.ClusterMap;
import de.meningococcus.episcangis.map.exporter.WmsImageExporter;

/* ====================================================================
 *   Copyright © 2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class ClusterMapAction extends DownloadAction implements
    DownloadAction.StreamInfo
{
  private InputStream imageInputStream;

  private String imageContentType;

  protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    int clusterId = Integer.parseInt(request.getParameter("id"));
    ClusterMap clusterMap = ContextAttributes.getClusterMap();
    clusterMap.setClusterId(clusterId);

    if (clusterMap != null)
    {
      WmsImageExporter imageExporter = new WmsImageExporter(new File(
          getServlet().getServletContext().getRealPath("/WEB-INF/cache/")));
      clusterMap.export(imageExporter);
      imageInputStream = imageExporter.getInputStream();
      imageContentType = imageExporter.getContentType();
    }
    else
    {
      throw new SessionBeanNotFoundException(
          "The global map was not found in servlet context.");
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

