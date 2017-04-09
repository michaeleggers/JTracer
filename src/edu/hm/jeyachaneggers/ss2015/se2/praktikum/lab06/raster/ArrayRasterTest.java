package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.SceneFactory;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;
/**
 * Test ArrayRaster
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public class ArrayRasterTest {
	
	/** A raster to Test.*/
	private ArrayRaster raster;
	
	/** A raytracer to Test. */
	private Raytracer tracer;
	
	/** A Array to Test raster. */
	private int[][] toTestRaster;            
	
	@Before
	public void setUp() throws ClassNotFoundException, IOException{
		raster = new ArrayRaster(3,3);
		tracer = new Raytracer(SceneFactory.make("ScriptedScene","looker 0 0 5  0 0 0  2 2","light 0 5 5","sphere 0 0 -5   1","ambient 0","diffuse 1"));
		raster.render(tracer);
		toTestRaster = new int[][]{{0,0,0},{0,222,0},{0,0,0}};
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArrayRaster0() {
		new ArrayRaster(0,0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testArrayRaster_1(){
		new ArrayRaster(-1,-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetPixel_1() {
		raster.getPixel(-1, -1);
	}
	
	@Test
	public void testGetPixel(){
		final int expected = 222;
		final int actual = raster.getPixel(1, 1);
		assertEquals(expected,actual);
	}

	@Test
	public void testRender() {
		assertArrayEquals(raster.getArray(),toTestRaster);
	
	}

}
