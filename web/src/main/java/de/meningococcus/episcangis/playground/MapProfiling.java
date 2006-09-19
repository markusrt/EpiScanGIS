package de.meningococcus.episcangis.playground;

import java.io.IOException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.InvalidParameterValueException;
import de.meningococcus.episcangis.map.LayerNotFoundException;
import de.meningococcus.episcangis.map.MapInitializationException;
import de.meningococcus.episcangis.map.NrzmMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.AbstractWmsMap.Exporter;
import de.meningococcus.episcangis.map.exporter.WmsImageExporter;
import de.meningococcus.episcangis.map.exporter.XmlExporter;

public class MapProfiling
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    try
    {
      //XmlExporter ex = new XmlExporter();
      AbstractWmsMap map = new NrzmMap(640, 480);
      //map.export(ex);

        //map.setParameter("SEROGROUPS", "\"B\"");
        ParameterComponent pc = map.getParameter("SEROGROUPS");
        try
        {
          pc.selectValue("\"B\"");
        }
        catch (InvalidParameterValueException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        WmsImageExporter iEx = new WmsImageExporter(null);
        map.export(iEx);
        try
        {
          byte [] test = IOUtils.toByteArray(iEx.getInputStream());
        }
        catch (IOException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        catch (LayerNotFoundException e)
        {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

    }
    catch (MapInitializationException e)
    {
      e.printStackTrace();
    }
    System.exit(0);
  }

}
