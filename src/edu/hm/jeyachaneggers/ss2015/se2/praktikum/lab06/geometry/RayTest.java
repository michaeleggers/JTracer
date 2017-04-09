package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 *	Tests if Ray class works properly.
 *
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public class RayTest {
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	/** Random ray. */
	private final Ray ray1 = new Ray(new Point(9,5.7,4.8798738528117), new Vector(0.0000000000019, -3.2849302910342, 1));
	
	/** Test against random ray.*/
	private final Ray ray2 = new Ray(new Point(9,5.7,4.8798738528112), new Vector(0.0000000000011, -3.2849302910346, 1));
	
	/** Test against random ray and should fail.  */
	private final Ray ray3 = new Ray(new Point(9,5.7,4.87987385281), new Vector(0.0000000000019, -3.2849302910342, 1));
	
	/**
	 * Checks if constructor throws correct Exception when calling with null-pointers.
	 */	
	@Test(expected = NullPointerException.class)
	public void testRayNull() {
		new Ray(null,null);
	}
	
	/**
	 * Checks if constructor throws correct Exception when rayvector is zerovector.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testRayVectorLengthZero(){
		new Ray(new Point(0,1,0),new Vector(0,0,0));
	}
	
	/**
	 * Checks if two rays are the same.
	 */
	@Test
	public void testEquals(){
		final Ray expected = ray1;
		final Ray actual = ray2;
		assertEquals(expected, actual);
	}
	
	/**
	 * Checks if two rays are not the same.
	 */
	@Test 
	public void testEqualsFail(){
		final Ray expected = ray1;
		final Ray actual = ray3;
		assertFalse(expected.equals(actual));
	}
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
	

}
