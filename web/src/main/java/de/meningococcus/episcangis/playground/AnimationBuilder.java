package de.meningococcus.episcangis.playground;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import de.meningococcus.episcangis.map.AbstractWmsMap;
import de.meningococcus.episcangis.map.InvalidParameterValueException;
import de.meningococcus.episcangis.map.LayerNotFoundException;
import de.meningococcus.episcangis.map.MapInitializationException;
import de.meningococcus.episcangis.map.NrzmMap;
import de.meningococcus.episcangis.map.ParameterComponent;
import de.meningococcus.episcangis.map.ParameterNotFoundException;
import de.meningococcus.episcangis.map.AbstractWmsMap.Exporter;
import de.meningococcus.episcangis.map.exporter.WmsImageExporter;
import de.meningococcus.episcangis.map.exporter.XmlExporter;

public class AnimationBuilder
{

  /**
   * @param args
   */
  @SuppressWarnings("unchecked")
  public static void main(String[] args)
  {
    try
    {
      AbstractWmsMap map = new NrzmMap(380, 480);
      map.setParameter("CASETYPES", "'C;5;2;3-3'");
      map.toggleLayerState("casetypes", true);
      map.toggleLayerState("serogroups_default", false);
      map.toggleLayerState("cities_default", false);
      WmsImageExporter iEx = new WmsImageExporter(null);
      map.export(iEx);
      FastDateFormat annDateFormat = FastDateFormat.getInstance("MM/yyyy");
      FastDateFormat mmDateFormat = FastDateFormat.getInstance("MM");
      FastDateFormat yyyyDateFormat = FastDateFormat.getInstance("yyyy");

      Calendar from = Calendar.getInstance();
      from.set(2002, Calendar.JANUARY, 1);
      Calendar to = Calendar.getInstance();
      to.set(2006, Calendar.SEPTEMBER, 30);
      Calendar now = Calendar.getInstance();
      now.setTime(from.getTime());
      Vector<File> animation = new Vector<File>();
      Process ls_proc;
      int loop = 0;
      while (now.before(to))
      {
        map.setParameter("fromMonth", mmDateFormat.format(now));
        map.setParameter("toMonth", mmDateFormat.format(now));
        map.setParameter("fromYear", yyyyDateFormat.format(now));
        map.setParameter("toYear", yyyyDateFormat.format(now));
        File f = File.createTempFile("anim", ".gif");
        FileUtils.writeByteArrayToFile(f, IOUtils.toByteArray(iEx
            .getInputStream()));
        String annotation = "convert " + f.getAbsolutePath()
            + " -stroke #999 -fill #00D5 -pointsize 40 -gravity center -annotate 0 "
            + annDateFormat.format(now) + " " + f.getAbsolutePath();
        ls_proc = Runtime.getRuntime().exec(annotation);
        ls_proc.waitFor();
        annotation = "convert " + f.getAbsolutePath()
        + " -fill #F006 -pointsize 20 -gravity south -annotate 0 \"Copyright,NRZM,2006\" " + f.getAbsolutePath();
        ls_proc = Runtime.getRuntime().exec(annotation);
        ls_proc.waitFor();
        if (loop > 0)
        {
          String animate;
          if (loop == 1)
          {
            animate = "convert -delay 100 -page +0+0 "
                + animation.lastElement().getAbsolutePath() + " -page +0+0 "
                + f.getAbsolutePath() + " /tmp/animation.gif";
            System.out.println("First run (" + loop +") " + animate);

          }
          else
          {
            animate = "convert /tmp/animation.gif -delay 100 -adjoin -page +0+0 "
                + f.getAbsolutePath() + " /tmp/animation.gif";
            System.out.println("Else " + animate);
          }

          ls_proc = Runtime.getRuntime().exec(animate);
          ls_proc.waitFor();
          //animation.lastElement().delete();
        }
        animation.add(f);
        now.add(Calendar.MONTH, 1);
        loop++;
      }
    }
    catch (InvalidParameterValueException e)
    {
      e.printStackTrace();
    }
    catch (ParameterNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (LayerNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (MapInitializationException e)
    {
      e.printStackTrace();
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    System.exit(0);
  }
}
