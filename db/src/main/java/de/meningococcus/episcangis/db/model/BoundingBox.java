package de.meningococcus.episcangis.db.model;

/* ====================================================================
 *   Copyright ©2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

/**
 * @author Markus Reinhardt <m.reinhardt[at]bitmap-friends.de>
 */
public class BoundingBox
{
  private double minx, miny, maxx, maxy;

  // private static Log log = LogFactory.getLog(BoundingBox.class);

  /**
   * 
   */
  public BoundingBox()
  {
  }


  /**
   * @param minx
   * @param miny
   * @param maxx
   * @param maxy
   */
  public BoundingBox(final double minx, final double miny, final double maxx,
      final double maxy)
  {
    this.maxx = maxx;
    this.maxy = maxy;
    this.minx = minx;
    this.miny = miny;
  }

  public BoundingBox(final String boxString)
  {
    String[] bbox = boxString.split(",");
    minx = Double.parseDouble(bbox[0]);
    miny = Double.parseDouble(bbox[1]);
    maxx = Double.parseDouble(bbox[2]);
    maxy = Double.parseDouble(bbox[3]);
  }

  /**
   * @return
   */
  public double getMaxx()
  {
    return maxx;
  }

  /**
   * @param pMaxx
   */
  public void setMaxx(final double pMaxx)
  {
    maxx = pMaxx;
  }

  /**
   * @return
   */
  public double getMaxy()
  {
    return maxy;
  }

  /**
   * @param pMaxy
   */
  public void setMaxy(final double pMaxy)
  {
    maxy = pMaxy;
  }

  /**
   * @return
   */
  public double getMinx()
  {
    return minx;
  }

  /**
   * @param pMinx
   */
  public void setMinx(final double pMinx)
  {
    minx = pMinx;
  }

  /**
   * @return
   */
  public double getMiny()
  {
    return miny;
  }

  /**
   * @param pMiny
   */
  public void setMiny(final double pMiny)
  {
    miny = pMiny;
  }

  /**
   * @return
   */
  public double getWidth()
  {
    return maxx - minx;
  }

  /**
   * @return
   */
  public double getHeight()
  {
    return maxy - miny;
  }

  /**
   * @param imgWidth
   * @param imgHeight
   * @return
   */
  public void mapToPixels(final int imgWidth, final int imgHeight)
  {
    double correctHeight = imgWidth / getWidth() * getHeight();
    double diffHeight = imgHeight - correctHeight;
    double boxPerPixelHeight = getHeight() / correctHeight;
    double correctWidth = imgHeight / getHeight() * getWidth();
    double diffWidth = imgWidth - correctWidth;
    double boxPerPixelWidth = getWidth() / correctWidth;
    boolean adjustHeight = false;

    if ((getWidth() <= getHeight() && diffHeight > 0) || diffWidth <= 0)
    {
      adjustHeight = true;
    }
    if (adjustHeight)
    {
      miny -= diffHeight / 2 * boxPerPixelHeight;
      maxy += diffHeight / 2 * boxPerPixelHeight;
    }
    else
    {
      minx -= diffWidth / 2 * boxPerPixelWidth;
      maxx += diffWidth / 2 * boxPerPixelWidth;
    }
  }

  public void move(double xOffset, double yOffset)
  {
    double moveX = xOffset * getWidth();
    double moveY = yOffset * getHeight();
    this.minx += moveX;
    this.maxx += moveX;
    this.miny += moveY;
    this.maxy += moveY;
  }

  /**
   * @return
   */
  public boolean isValid()
  {
    return (getWidth() > 0 && getHeight() > 0);
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return minx + "," + miny + "," + maxx + "," + maxy;
  }

}
