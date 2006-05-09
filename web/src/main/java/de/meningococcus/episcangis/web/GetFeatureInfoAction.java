package de.meningococcus.episcangis.web;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.exporter.WmsInformationExporter;

/**
 * @author Markus Reinhardt
 */
public class GetFeatureInfoAction extends DownloadAction implements
    DownloadAction.StreamInfo
{
  private InputStream inputStream;

  private String contentType;

  protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception
  {
    AbstractWmsMap mapBean = (AbstractWmsMap) request.getSession()
        .getAttribute("map");
    GetFeatureInfoFormBean formBean = (GetFeatureInfoFormBean) form;
    if (mapBean != null)
    {
      WmsInformationExporter wmsInfoExporter = new WmsInformationExporter(
          formBean.getX(), formBean.getY());
      mapBean.export(wmsInfoExporter);
      inputStream = wmsInfoExporter.getInputStream();
      contentType = wmsInfoExporter.getContentType();
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
    return contentType;
  }

  public InputStream getInputStream() throws IOException
  {
    return inputStream;
  }
}