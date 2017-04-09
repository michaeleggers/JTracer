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
 * Tests if Sphere class is working properly.
 * 
 * @author Vithya Jeyachandran,jeyachan@hm.edu
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-04-18
 */
public class SphereTest {

	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	
	/** a List that is going to contain two elements. */
	private final List<Intersection> intersections2 = new ArrayList<>();

	/** a List that is going to contain one element. */
	private final List<Intersection> intersections1 = new ArrayList<>();

	/** an empty List. */
	private final List<Intersection> intersections0 = new ArrayList<>();
	
	/** a List with one Intersection when ray is in Sphere2. */
	private final List<Intersection> intersectionRayInSphere = new ArrayList<>();

	/**
	 * a nice ray that points from positive z to negative z. Thay ray "is" the z
	 * axis.
	 */
	private final Ray ray = new Ray(new Point(0, 0, 5),
			new Vector(0, 0, -5));
	
	/** A Ray that is in sphere2. */
	private final Ray rayInSphere = new Ray(new Point(0,0,-5),new Vector(0,0,-1));
	
	/**  An intersection. */
	private final Intersection intsec1 = new Intersection(new Point(0,0,-4),new Sphere(new Point(0, 0, -5), 1),9.0,true);
	
	/**  An intersection. */
	private final Intersection intsec2 = new Intersection(new Point(0,0,-6),new Sphere(new Point(0, 0, -5), 1),11.0,false);
	
	/**  An intersection. */
	private final Intersection intsec3 = new Intersection(new Point(0,0,-5),new Sphere(new Point(0, 1, -5), 1),10.0,true);
	
	/**  An intersection. */
	private final Intersection intsec4 = new Intersection(new Point(0,0,-6),new Sphere(new Point(0, 0, -5), 1),1,false);

	/**
	 * Prepares objects for tests.
	 */
	@Before
	public void setUp() {

		// Intersections
		intersections2.add(intsec1);
		intersections2.add(intsec2);
		intersections1.add(intsec3);
		intersectionRayInSphere.add(intsec4);
	}

	/**
	 * Checks if there are two values in the list when the ray hits twice.
	 */
	@Test
	public void intersectionsTest2() {
		final List<Intersection> actual = new Sphere(new Point(0, 0, -5), 1).intersections(ray);
		final List<Intersection> expected = intersections2;
		assertEquals(expected, actual);
	}

	/**
	 * Checks if there is one value in the list when the ray hits once.
	 */
	@Test
	public void intersectionsTest1() {
		final List<Intersection> actual = new Sphere(new Point(0, 1, -5), 1).intersections(ray);
		final List<Intersection> expected = intersections1;
		assertEquals(actual, expected);
	}
	
	/**
	 * Checks if there is one value in the list when the ray is in the sphere.
	 */
	@Test
	public void intersectionTest1RayInSphere(){
		final List<Intersection> actual = new Sphere(new Point(0, 0, -5), 1).intersections(rayInSphere);
		final List<Intersection> expected = intersectionRayInSphere;
		assertEquals(actual,expected);
		
	}

	/**
	 * Checks if there is no value in the list if the ray misses.
	 */
	@Test
	public void intersectionsTest0() {
		final List<Intersection> actual = new Sphere(new Point(0, 2, -5), 1).intersections(ray);
		final List<Intersection> expected = intersections0;
		assertEquals(expected, actual);
	}
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck

}
