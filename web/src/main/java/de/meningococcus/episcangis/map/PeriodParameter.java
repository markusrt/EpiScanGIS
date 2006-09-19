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

public class PeriodParameter extends ParameterComposite
{
  private static Log log = LogFactory.getLog(PeriodParameter.class);

  // private SelectParameter fromMonth, fromYear, toMonth, toYear;

  public PeriodParameter(String name, Date from, Date to, String title)
  {
    super(name, title);
    this.elementName = "periodparameter";
    SelectParameter fromMonth, fromYear, toMonth, toYear;
    SimpleDateFormat dfLongMonth = new SimpleDateFormat("MMMMM", Locale.ENGLISH);
    SimpleDateFormat dfShortMonth = new SimpleDateFormat("MM", Locale.ENGLISH);
    Calendar cal = Calendar.getInstance();

    int[] months = { Calendar.JANUARY, Calendar.FEBRUARY, Calendar.MARCH,
        Calendar.APRIL, Calendar.MAY, Calendar.JUNE, Calendar.JULY,
        Calendar.AUGUST, Calendar.SEPTEMBER, Calendar.OCTOBER,
        Calendar.NOVEMBER, Calendar.DECEMBER };

    fromMonth = new SelectParameter("fromMonth");
    toMonth = new SelectParameter("toMonth");

    for (int i = 0; i < months.length; i++)
    {
      cal.set(Calendar.MONTH, months[i]);

      fromMonth.add(new ParameterValue(dfLongMonth.format(cal.getTime()),
          dfShortMonth.format(cal.getTime())));
      toMonth.add(new ParameterValue(dfLongMonth.format(cal.getTime()),
          dfShortMonth.format(cal.getTime())));
    }

    SimpleDateFormat dfEnglishYear = new SimpleDateFormat("yyyy",
        Locale.ENGLISH);

    cal.setTime(from);
    int beginYear = cal.get(Calendar.YEAR);

    cal.setTime(to);
    int endYear = cal.get(Calendar.YEAR);

    fromYear = new SelectParameter("fromYear");
    toYear = new SelectParameter("toYear");

    for (int i = beginYear; i <= endYear; i++)
    {
      cal.set(Calendar.YEAR, i);

      fromYear.add(new ParameterValue(dfEnglishYear.format(cal.getTime()),
          dfEnglishYear.format(cal.getTime())));
      toYear.add(new ParameterValue(dfEnglishYear.format(cal.getTime()),
          dfEnglishYear.format(cal.getTime())));
    }

    add(fromMonth);
    add(fromYear);
    add(toMonth);
    add(toYear);
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

    try
    {
      selectValue("fromMonth", fromMonth);

      selectValue("fromYear", fromYear);
      selectValue("toMonth", toMonth);
      selectValue("toYear", toYear);
    }
    catch (ParameterNotFoundException e)
    {
      log.error(e.getMessage());
    }
    catch (InvalidParameterValueException e)
    {
      log.error(e.getMessage());
    }
  }

  @Override
  public String getAliasValue()
  {
    return get("fromMonth").getAliasValue() + " "
        + get("fromYear").getAliasValue() + " - "
        + get("toMonth").getAliasValue() + " " + get("toYear").getAliasValue();
  }

  public ParameterComponent getFromMonth()
  {
    return get("fromMonth");
  }

  public ParameterComponent getToMonth()
  {
    return get("toMonth");
  }

  public ParameterComponent getFromYear()
  {
    return get("fromYear");
  }

  public ParameterComponent getToYear()
  {
    return get("toYear");
  }

}
