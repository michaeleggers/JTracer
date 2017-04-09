package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

/** 
 * Tests of Vector classes constructors and algebraic methods.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public class VectorTest {
  
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	
  /** Base of a potential vector. */
  private static final Point BASE = new Point(0, 0, 5);
  
  /** Tip of a potential vector. */
  private static final Point AIM = new Point(0, 0, -5.3422);
  
  /** Vector that will be produced if one goes from base to aim (see above). */
  private static final Vector VECTOR_OUT_OF_TWO_POINTS = new Vector(0, 0, -10.3422);

  /** A random vector, later used for a dot product. */
  private static final Vector RAND_VECTOR1 = new Vector(1, 2, 3);
  
  /** A random vector, later used for a dot product. */
  private static final Vector RAND_VECTOR2 = new Vector(0, -1.5, 10);
  
  /** A random vector, later used for a cross product. */
  private static final Vector RAND_VECTOR3 = new Vector(4,6,-2);
  
  /** A random vector, later used for a cross product. */
  private static final Vector RAND_VECTOR4 = new Vector(7,6,10);
  
  /** Result vector if add RANDVECTOR1 and RANDVECTOR3. */
  private static final Vector ADDVECTOR = new Vector(5,8,1); 
  
  /** Dot product that should be calculated when multiplying the two random vectors above. */
  private static final double DOT_PRODUCT = 27.0;
  
  /** Solution of cross product test. */
  private static final Vector CROSS_PRODUCT = new Vector(72,-54,-18);
  
  /** A vector pointing upwards. */
  private static final Vector UPVECTOR = new Vector(0, 1, 0);
  
  /** Another vector that is pointing upwards, but slightly off the previous one. */
  private static final Vector UPVECTOR2 = new Vector(0.00000000001, 1, 0);
  
  /** Another vector that is point upwards, but isn't within the range where it will be recognized as parallel to the other ones above. */
  private static final Vector UPVECTOR3 = new Vector(0.0000000001, 1, 0);
  
  /** Inverted version of VECTOR2. */
  private static final Vector UPVECTOR4 = new Vector(0.00000000001, -1, 0);

  /**
   * Checks if the custom constructor calculates the right vector out of two given points.
   */
  @Test
  public void testVector(){
    final Vector expected = VECTOR_OUT_OF_TWO_POINTS;
    final Vector actual = BASE.vectorTo(AIM);
    assertEquals(expected, actual);
  }
  
  /**
   * Checks of a dot product between two given vectors is calculated properly.
   */
  @Test
  public void testDotProduct(){
    final double expected = DOT_PRODUCT;
    final double actual = RAND_VECTOR1.dotProduct(RAND_VECTOR2);
    assertEquals(expected, actual, 0);
  }
  
  /**
   * Checks of a cross product between two given vectors is calculated properly.
   */
  @Test 
  public void testCrossProduct(){
	  final Vector expected = CROSS_PRODUCT;
	  final Vector actual = RAND_VECTOR3.crossProduct(RAND_VECTOR4);
	  assertEquals(expected, actual);
  }
  
  /**
   * Check if add a vector to another works properly.
   */
  @Test 
  public void testAdd(){
	  final Vector expected = ADDVECTOR;
	  final Vector actual= RAND_VECTOR1.add(RAND_VECTOR3);
	  assertEquals(expected,actual);
  }
  
  /**
   * Check if recognizing two parallel vectors works.
   */
  @Test
  public void testIsParallelTrue(){
	  final boolean expected = true;
	  final boolean actual = UPVECTOR.isParallel(UPVECTOR2);
	  assertEquals(expected, actual);
  }
  
  /**
   * Check if recognizing two parallel vectors works (fail).
   */
  @Test
  public void testIsParallelFalse(){
	  final boolean expected = false;
	  final boolean actual = UPVECTOR.isParallel(UPVECTOR3);
	  assertEquals(expected, actual);
  }
  
  /**
   * Check if recognizing two parallel vectors works (fail).
   */
  @Test
  public void testIsParallelTrue2(){
	  final boolean expected = true;
	  final boolean actual = UPVECTOR.isParallel(UPVECTOR4);
	  assertEquals(expected, actual);
  }

	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
}