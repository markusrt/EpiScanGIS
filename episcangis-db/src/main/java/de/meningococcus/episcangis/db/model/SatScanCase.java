package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class SatScanCase implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int areaId;

  private long count;

  private Date incidenceDate;

  public int getAreaId()
  {
    return areaId;
  }

  public void setAreaId(int areaId)
  {
    this.areaId = areaId;
  }

  public long getCount()
  {
    return count;
  }

  public void setCount(long count)
  {
    this.count = count;
  }

  public Date getIncidenceDate()
  {
    return incidenceDate;
  }

  public void setIncidenceDate(Date incidenceDate)
  {
    this.incidenceDate = incidenceDate;
  }

}
