package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;

/**
 * Test Raster.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */

public class RaytracerTest {

	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck


	/**
	 * Expected pattern for StaticPlane. (Mirrored). 
	 */
	private static final int[][] STATIC_PLANE_RES_12 = {
		{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{0, 0, 0, 0, 255, 255, 255, 255, 255, 255, 255, 255}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 255, 255, 255}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};

	/** Tracer for Plane scene resolution 12. */
	private Raytracer staticPlaneTracer12;
	
	/** Tracer for Plane scene resolution 1200. */
	private Raytracer staticPlaneTracer1200;
	
	/** Tracer with 0 resolution. */
	private Raytracer illegalTracer0;
	
	/** Tracer with -1 resolution. */
	private Raytracer illegalTracerNeg1;

	/** Plane scene. */
	private final Scene staticPlane = new StaticPlaneScene();

	/**
	 * initialize variables.
	 */
	@Before
	public void setUp(){
		staticPlaneTracer12 = new Raytracer(staticPlane);
		staticPlaneTracer1200 = new Raytracer(staticPlane);
		illegalTracer0 = new Raytracer(staticPlane);
		illegalTracerNeg1 = new Raytracer(staticPlane);
	} 

	/**
	 * look at method name.
	 */
	@Test
	public void testToRasterStaticPlane12() {
		final int[][] expected = STATIC_PLANE_RES_12;
		final int[][] actual = staticPlaneTracer12.toRaster(12);
		assertArrayEquals(expected, actual);
	}

	/**
	 * Is toRaster fast enough?
	 */
	@Test (timeout = 5000)
	public void testToRaster1200(){
		staticPlaneTracer1200.toRaster(1200);
	}
	
	/**
	 * Is correct Exception being thrown?
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testToRaster0(){
		illegalTracer0.toRaster(0);
	}
	
	/**
	 * Is correct Exception being thrown?
	 */
	@SuppressWarnings({"PMD.MethodNamingConventions",
		"PMD.TooManyMethods",
		"PMD.AvoidDuplicateLiterals"})
	@Test(expected = IllegalArgumentException.class)
	public void testToRaster_1(){
		illegalTracerNeg1.toRaster(-1);
	}
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
}

