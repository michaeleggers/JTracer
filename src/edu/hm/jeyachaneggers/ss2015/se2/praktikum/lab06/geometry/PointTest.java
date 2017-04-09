package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;

import static org.junit.Assert.*;

import org.junit.Test;



/**
 * Checks if Point class works properly.
 * 
 * @author Michael Eggers, eggers@hm.edu *  @author Vithya Jeyachandran,jeyachan@hm.edu
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-04-04 *
 */
public class PointTest {
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
  
  /** Start of a potential vector. */
  private static final Point BASE = new Point(1.89, 2.22, 89.3);
  
  /** End (like the tip of an arrow) of a potential vector. */
  private static final Point TIP = new Point(-32.22, -11.22, 32.2);
  
  /** The vector that will be produced when going from base to tip (see above). */
  private static final Vector VECTOR = new Vector(-34.11, -13.44, -57.1);
  
  /**
   * Checks if a new Vector will be created out of two given points.
   */
  @Test
  public void vectorToTest(){
    final Vector expected = VECTOR;
    final Vector actual = BASE.vectorTo(TIP);
    assertEquals(actual.toString(), expected.toString());
  }
  /**
   * Checks if the correct point is computed when going from one point to
   * another via a vector.
   */
	@Test
	public void addVectorToPointTest(){
		final Point expected = TIP;
		final Point actual = BASE.shiftPoint(VECTOR);
		assertEquals(actual,expected);
	}
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
}
