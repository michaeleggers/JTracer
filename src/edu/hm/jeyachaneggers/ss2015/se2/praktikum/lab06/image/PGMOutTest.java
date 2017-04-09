package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.image;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.ArrayRaster;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.Raster;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.ScriptedScene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;


/**
 * Checks if method asString of PGMOut works properly.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public class PGMOutTest {
	
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	
	/** This is the String-pattern asString should produce. */
	private static final String RASTER12X12 = 
			"P2\n"
		+	"12 12\n"
		+	"255\n"
		+	"  0   0   0   0   0   0   0   0   0   0   0   0 \n"
		+	"  0   0   0   0   0   0   0   0   0   0   0   0 \n"
		+	"  0   0   0   0   0   0   0   0   0   0   0   0 \n"
		+	"  0   0   0   0 192 247 247 192   0   0   0   0 \n"
		+	"  0   0   0 122 208 235 235 208 122   0   0   0 \n"
		+	"  0   0   0 108 165 187 187 165 108   0   0   0 \n"
		+	" 11  11  11  45 101 122 122 101  45  11  11  11 \n"
		+	" 36  36  36   0  17  41  41  17   0  36  36  36 \n"
		+	" 62  62  63  63   0   0   0   0  63  63  62  62 \n"
		+	" 89  90  91  91  91  91  91  91  91  91  90  89 \n"
		+	"117 118 118 119 119 119 119 119 119 118 118 117 \n"
		+	"143 144 145 146 146 146 146 146 146 145 144 143 \n";
	
	/** This scene consists of a light a sphere and a plane. It is responsible for RASTER12X12. */
	private Scene scriptedScene1;
	
	/** Raytracer for scriptedScene1. */
	private Raytracer tracer1;
	
	/** Raytracer for scriptedScene1. */
	private Raster raster12;

	/** This one will produce the pattern. */
	private PGMOut pgm12x12;
	
	/** A raster that is a null object. */
	private Raster nullRaster;
	
	
	/**
	 * Initialize variables.
	 */
	@Before
	public void setUp(){
		scriptedScene1 = new ScriptedScene("looker [0 0 5] [0 0 0] 2 2",
											"sphere [0 0 -5] 1",
											"light [0 5 0]",
				   							"plane [0 -3 0] <0 1 0>");
		
		tracer1 = new Raytracer(scriptedScene1);
		
		raster12 = new ArrayRaster(12,12).render(tracer1);
		
		pgm12x12 = new PGMOut();
		
		nullRaster = null;	
	}
	/**
	 * Checks if given raster produces correct string pattern.
	 */
	@Test
	public void testAsString12x12() {
		final String expected = RASTER12X12;
		final String actual = pgm12x12.asString(raster12);
		assertEquals(expected, actual);
	}
	
	/**
	 * Checks if correct exception is thrown if raster is a stupid null object. 
	 */
	@Test (expected = NullPointerException.class)
	public void testAsStringFails(){
		pgm12x12.asString(nullRaster);
	}
	
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck
}


