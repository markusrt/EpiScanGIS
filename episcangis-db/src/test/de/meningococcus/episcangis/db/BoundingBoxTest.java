package de.meningococcus.episcangis.db;

/* ====================================================================
 *   Copyright �2005 Markus Reinhardt - All Rights Reserved.
 * ====================================================================
 */

import junit.framework.Test;
import junit.framework.TestSuite;

import de.meningococcus.episcangis.db.model.BoundingBox;

/**
 * @author Markus Reinhardt
 */
public class BoundingBoxTest extends AbstractTestCase
{
  /**
   *  
   */
  public BoundingBoxTest(String testName)
  {
    super(testName);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite(BoundingBoxTest.class);
  }

  public void testBboxValid()
  {
    BoundingBox box = new BoundingBox("-1,-1,1,1");
    assertTrue(box.isValid());
    box = new BoundingBox("1,-1,1,1");
    assertFalse(box.isValid());
  }

  public void testConstructors()
  {
    BoundingBox box1 = new BoundingBox(-1, -1, 1, 1);
    BoundingBox box2 = new BoundingBox("-1,-1,1,1");
    assertEquals("Both constructors produce equal result", box1.toString(),
        box2.toString());
  }

  public void testBboxAsText()
  {
    BoundingBox box = new BoundingBox(
        "-1.287918729372891,-1.287918729372891,1.287918729372891,1.287918729372");
    assertEquals(
        "String representation equals constructor",
        "-1.287918729372891,-1.287918729372891,1.287918729372891,1.287918729372",
        box.toString());
  }

  public void testMapToPixels()
  {
    BoundingBox box1 = new BoundingBox("-1,-1,1,1");
    BoundingBox box2 = new BoundingBox("-1,-1,1,1");
    box2.mapToPixels(100, 100);
    assertEquals("", box1.toString(), box2.toString());
    box2.mapToPixels(200, 100);
    assertEquals("", "-2.0,-1.0,2.0,1.0", box2.toString());
    box2 = box1;
    box2.mapToPixels(100, 200);
    assertEquals("", "-1.0,-2.0,1.0,2.0", box2.toString());
  }

  public void testMove()
  {
    BoundingBox box = new BoundingBox("-1,-1,1,1");
    box.move(1.0, 0.0);
    assertEquals("1.0,-1.0,3.0,1.0", box.toString());
    box.move(-1.0, 0.0);
    assertEquals("-1.0,-1.0,1.0,1.0", box.toString());
    box.move(0.5, 0.5);
    assertEquals("0.0,0.0,2.0,2.0", box.toString());
    box.move(-0.5, -0.5);
    assertEquals("-1.0,-1.0,1.0,1.0", box.toString());
  }

  public void testSize()
  {
    BoundingBox box = new BoundingBox("-1,-1,1,1");
    assertEquals(box.getWidth(), 2.0);
    assertEquals(box.getHeight(), 2.0);
  }
}
