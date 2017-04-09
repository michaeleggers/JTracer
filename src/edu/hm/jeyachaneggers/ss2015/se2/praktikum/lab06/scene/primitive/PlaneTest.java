package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;


import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import java.util.List;
import java.util.ArrayList;

/**
 * Tests Plane class.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 *
 */
public class PlaneTest {
	
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck

/** A random Plane used for equals.*/	
  private final Plane plane1 = new Plane(new Point(0, 0, 0), new Vector(0, 1, 0));
  
  /** Same like plane1 but with other values. Used for equals.*/
  private final Plane plane2 = new Plane(new Point(0,0,1),new Vector(0,-1,0));
  
  /** To test to different planes. */
  private final Plane plane3 = new Plane(new Point(0,0,0),new Vector(1,0,0));
  
  /** List to Test plane intersection with no Intersection objects. */
  private final List<Intersection> intersection0 = new ArrayList<>();
  
  /** List to Test plane intersection with one Intersection object. */
  private final List<Intersection> intersection1 = new ArrayList<>();
  
  /** Ray to test intersections with one Intersection object.*/
  private final  Ray rayIntersection1= new Ray(new Point(0,1,0), new Vector(0,-1,0));
  
  /** Ray to test intersection with zero Intersection objects. */
  private final Ray rayIntersection0 = new Ray(new Point(0,1,0),new Vector(0,0,1));
  
  /** Result of method intersection with one object. */
  private final Intersection insectobject = new Intersection(new Point(0,0,0),plane1,1,true);
  
  /**
   * Prepares objects for tests.
   */
  @Before
  public void setUp(){
	  intersection1.add(insectobject);
  }
  
  /**
   * Checks if there are two values in the list when the ray hits twice.
   */
  @Test
  public void testEqualsIsSame(){
    final Plane expected = plane1;
    final Plane actual = plane2;
    assertEquals(expected, actual);
  }
  
  /**
   * Checks if equals fail.
   */
  @Test 
  public void testEqualsFail(){
	  final Plane expected = plane1;
	  final Plane actual = plane3;
	  assertFalse(expected.equals(actual));
  }
  
  /**
   * Check if intersections works properly. 
   */
  public void testIntersectionOneObject(){
	  final List<Intersection> expected = intersection1;
	  final List<Intersection> actual = plane1.intersections(rayIntersection1);
	  assertEquals(expected,actual);
  }
  
  /**
   * Checks if intersection works properly.
   */
  public void testIntersectionZeroObject(){
	  final List<Intersection> expected = intersection0;
	  final List<Intersection> actual = plane1.intersections(rayIntersection0);
	  assertEquals(expected,actual);
  }
  
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
}
