package de.meningococcus.episcangis.map;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

public class PeriodParameter extends GroupedParameter
{
  private static Log log = LogFactory.getLog(GroupedParameter.class);

  private OLDSelectParameter fromMonth, fromYear, toMonth, toYear;

  public PeriodParameter(String name, Date from, Date to, String title)
  {
    super(name, title);
    SimpleDateFormat dfLongMonth = new SimpleDateFormat("MMMMM", Locale.ENGLISH);
    SimpleDateFormat dfShortMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance();

    int[] months = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH,
        Calendar.APRIL, Calendar.MAY, Calendar.JUNE, Calendar.JULY,
        Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER,
        Calendar.NOVEMBER, Calendar.DECEMBER };

    fromMonth = new OLDSelectParameter("fromMonth");
    toMonth = new OLDSelectParameter("toMonth");

    for (int i = 0; i < months.length; i++)
    {
      cal.set(Calendar.MONTH, months[i]);

      fromMonth.addValue(new ParameterValue(dfLongMonth.format(cal.getTime()),
          dfShortMonth.format(cal.getTime())));
      toMonth.addValue(new ParameterValue(dfLongMonth.format(cal.getTime()),
          dfShortMonth.format(cal.getTime())));
      log.debug("Added Month: " + dfLongMonth.format(cal.getTime()) + " - "
          + dfShortMonth.format(cal.getTime()));
    }

    SimpleDateFormat dfEnglishYear = new SimpleDateFormat("yyyy",
        Locale.ENGLISH);

    cal.setTime(from);
    int beginYear = cal.get(Calendar.YEAR);

    cal.setTime(to);
    int endYear = cal.get(Calendar.YEAR);

    fromYear = new OLDSelectParameter("fromYear");
    toYear = new OLDSelectParameter("toYear");

    for (int i = beginYear; i <= endYear; i++)
    {
      cal.set(Calendar.YEAR, i);

      fromYear.addValue(new ParameterValue(dfEnglishYear.format(cal.getTime()),
          dfEnglishYear.format(cal.getTime())));
      toYear.addValue(new ParameterValue(dfEnglishYear.format(cal.getTime()),
          dfEnglishYear.format(cal.getTime())));
      log.debug("Added Year: " + dfEnglishYear.format(cal.getTime()));
    }

    addParameter(fromMonth);
    addParameter(fromYear);
    addParameter(toMonth);
    addParameter(toYear);
  }

  public PeriodParameter(String name, Date from, Date to)
  {
    this(name, from, to, "");
  }

  public void setDefaultSelection(Date from, Date to)
  {
    SimpleDateFormat dfMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
    SimpleDateFormat dfYear = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance();

    cal.setTime(from);
    String fromMonth = dfMonth.format(cal.getTime());
    String fromYear = dfYear.format(cal.getTime());
    cal.setTime(to);
    String toMonth = dfMonth.format(cal.getTime());
    String toYear = dfYear.format(cal.getTime());

    log.debug(fromMonth + fromYear + toMonth + toYear);

    this.fromMonth.setValue(fromMonth);
    this.fromYear.setValue(fromYear);
    this.toMonth.setValue(toMonth);
    this.toYear.setValue(toYear);
  }

  public OLDSelectParameter getFromMonth()
  {
    return fromMonth;
  }

  public OLDSelectParameter getFromYear()
  {
    return fromYear;
  }

  public OLDSelectParameter getToMonth()
  {
    return toMonth;
  }

  public OLDSelectParameter getToYear()
  {
    return toYear;
  }

}
