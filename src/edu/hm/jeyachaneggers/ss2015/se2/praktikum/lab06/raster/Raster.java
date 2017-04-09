package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster;

import java.util.Arrays;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;

/**
 * Every class that can render a scene (via a Raytracer) must provide the following methods.
 * Raster is only (!) responsible for calculating the new relative coordinates for the Raytracer.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-05-01
 */
public interface Raster {
	
	/** 
	 * Return width of the resolution. 
	 * 
	 * @return width.
	 */
	int getWidth();
	
	/** 
	 * Return height of the resolution. 
	 * 
	 * @return height.
	 */
	int getHeight();
	
	/** 
	 * Return the pixel value on a specific position on the ray.
	 * 
	 * @param rowIndex is y.
	 * @param columnIndex is x.
	 * @return The pixel on the wanted position.
	 * @throws IllegalArgumentException if rowIndex and columnIndex is out of array index.
	 */
	int getPixel(final int rowIndex,final int columnIndex);
	
	/**
	 * Generates information for PGMFile.
	 * 
	 * @param raytracer Raytracer contains all data about the scene
	 * @return a Raster with information for PGM.
	 */
	Raster render(final Raytracer raytracer);
	
	/**
	 * Factory method to fabricate new raster objects.
	 * 
	 * @param args Concrete raster class with its necessary arguments.
	 * @return A nice Raster object.
	 * @throws ClassNotFoundException if there is no such concrete Raster class.
	 */
	 static Raster make(final String... args) throws ClassNotFoundException{
		for(final String argument : args)
			assert argument != null: "shuld not be null";
		
		int arg = 0;
		if("ArrayRaster".equals(args[arg]))
			return new ArrayRaster(Integer.parseInt(args[++arg]), Integer.parseInt(args[++arg]));
//		if("ArrayRasterLive".equals(args[arg]))
//			return new ArrayRasterLive(Integer.parseInt(args[++arg]), Integer.parseInt(args[++arg]));
		if("Supersampled".equals(args[arg])){   
			 return new Supersampled(make(Arrays.copyOfRange(args, ++arg, args.length)));
		}
		throw new ClassNotFoundException();
	}
}
