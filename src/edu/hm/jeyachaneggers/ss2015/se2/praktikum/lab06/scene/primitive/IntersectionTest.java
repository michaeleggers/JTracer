package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;


/**
 * Tests Intersection class.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 *
 */
public class IntersectionTest {
	
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck

	/** A random Plane used for equals.*/	
	private final Primitive plane1 = new Plane(new Point(0, 0, 0), new Vector(0, 1, 0));

	/** Is the same like random plane used for equals.*/	
	private final Primitive plane2 = new Plane(new Point(0, 0, 0), new Vector(0, 1, 0));

	/** A Sphere to Test if equals fail. */
	private final Sphere sphere = new Sphere(new Point(0, 0, -5), 1);

	/** A Random Intersection object. */
	private final Intersection insectobject = new Intersection(new Point(0,0,0),plane1,1,true);

	/** Is same like random Intersection object to test equals. */
	private final Intersection insectobject2 = new Intersection(new Point(0,0,0),plane2,1,true);

	/** Is different from random intersection object. */
	private final Intersection insectobject3 = new Intersection(new Point(0,0,-6),sphere,1,false);

	/**
	 * Checks if Constructor throws Exception if point is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testIntersectionPointNull() {
		new Intersection(null,plane2,45,true);
	}
	
	/**
	 * Checks if Constructor throws Exception if primitive is null.
	 */
	@Test(expected = NullPointerException.class)
	public void testIntersectionPrimitiveNull(){
		new Intersection(new Point(0,0,0),null,75,false);
	}

	/**
	 * Checks if equals works properly.
	 */
	@Test
	public void testEqualsObject() {
		final Intersection expected = insectobject2;
		final Intersection actual = insectobject;
		assertEquals(expected,actual);
	}

	/**
	 * Checks if equals works properly.
	 */
	@Test
	public void testEqualsFail(){
		final Intersection expected = insectobject;
		final Intersection actual = insectobject3;
		assertFalse(expected.equals(actual));
	}
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck

}
