package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;

/**
 * Tests if findIntersection returns an Intersection object or null.
 * The outcome (instance or null) depends on whether the intersections method of primitive classes
 * return intersection objects or not. Thus this class kind of serves as a double check.
 * 
 * @author Vithya Jeyachandran,jeyachan@hm.edu
 * @author Michael Eggers, eggers@hm.edu
 * @version 2015-04-18
 *
 */
public class SceneTest {

	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	
	
	/**
	 * Generates a new plane, shifted -1 units in y direction from origin
	 * with an orientation of -1 5 0 for x y z accordingly.
	 */
	private static Scene planeScene;
	
	/**
	 * Generates a static shifted Sphere of radius 1 and its center shifted to  
	 * 2 1 -10 for x y z accordingly.
	 **/
	private static Scene sphereScene;

	
	/** A ray that intersects the plane. */
	private Ray rayToPlaneHit;
	
	/** A ray that misses the plane (parallel). */
	private Ray rayToPlaneMiss;
	
	/** A ray that lies within the plane. */
	private Ray rayInPlane;
	
	/** A ray that intersects the shifted sphere twice. */
	private Ray rayToShiftedSphere2;
	
	/** A ray that touches the shifted sphere. */
	private Ray rayToShiftedSphereTouch;
	
	/** A ray that misses the shifted sphere. */
	private Ray rayToShiftedSphereMiss;
	

	/**
	 * Initialize variables. 
	 */
	@Before
	public void setUp() throws ClassNotFoundException, IOException {
		planeScene = Scene.make("ScriptedScene","looker 0 0 5 0 0 0 16 9","plane 0 -1 0 -1 5 0");
		sphereScene = Scene.make("ScriptedScene","looker 0 0 5 0 0 0 16 9","sphere 2 1 -10 1");
		rayToPlaneHit = new Ray(new Point(-10, 0, 0), new Vector(1, 0 , 0));
		rayToPlaneMiss = new Ray(new Point(0, 0, 10), new Vector(0, 0 , -1));
		rayInPlane = new Ray(new Point(0, -1, 0), new Vector(0, 0, -1));
		rayToShiftedSphere2 = new Ray(new Point(2, 1, 10), new Vector(0, 0, -1));
		rayToShiftedSphereTouch = new Ray(new Point(2, 2, 10), new Vector(0, 0, -1));
		rayToShiftedSphereMiss = new Ray(new Point(2, 2.0000000000001, 10), new Vector(0, 0, -1));
	} 

	/**
	 * Ray hits the plane.
	 */
	@Test
	public void testFindIntersectionPlaneHit() {
		final Optional<Intersection> actual = planeScene.findIntersection(rayToPlaneHit);
		assertTrue(actual.isPresent());
	}
	
	/**
	 * Ray is parallel to the plane.
	 */
	@Test
	public void testFindIntersectionPlaneMiss() {
		final Optional<Intersection> actual = planeScene.findIntersection(rayToPlaneMiss);
		assertTrue(!actual.isPresent());
	}
	
	/**
	 * Ray is in the plane.
	 */
	@Test
	public void testFindIntersectionInPlane(){
		final Optional<Intersection> actual = planeScene.findIntersection(rayInPlane);
		assertTrue(!actual.isPresent());
	}
	
	/**
	 * Ray penetrates and escapes the shifted sphere.
	 */
	@Test
	public void testFindIntersectionShiftedSphere2(){
		final Optional<Intersection> actual = sphereScene.findIntersection(rayToShiftedSphere2);
		assertTrue(actual.isPresent());
	}
	
	/**
	 * Ray touches the shifted sphere.
	 */
	@Test
	public void testFindIntersectionShiftedSphereTouch(){
		final Optional<Intersection> actual = sphereScene.findIntersection(rayToShiftedSphereTouch);
		assertTrue(actual.isPresent());
	}
	
	/**
	 * Ray misses the shifted sphere.
	 */
	@Test
	public void testFindIntersectionShiftedSphereMiss(){
		final Optional<Intersection> actual = sphereScene.findIntersection(rayToShiftedSphereMiss);
		assertTrue(!actual.isPresent());
	}
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck

}
