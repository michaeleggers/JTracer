package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;

/**
 * Does antialiasing. It is not a senseful class by itself. It needs an array rasters brightness array
 * to compute an average of four.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 16:49:33
 */
public class Supersampled implements Raster{
	
	/** The raster we get from another Sumpersampled or ArrayRaster. */
	private final Raster superRaster;
	
	/**
	 * Build a supersampled raster.
	 * 
	 * @param raster the original raster.
	 */
	public Supersampled(final Raster raster){
		if(raster.getHeight() % 2 != 0 || raster.getWidth() % 2 != 0)
			throw new IllegalArgumentException();
		this.superRaster = raster;
		
	}
	
	@Override public int getWidth(){
		return superRaster.getWidth()/2;
	}
	
	@Override public int getHeight(){
		return superRaster.getHeight()/2;
	}
	
	@Override public int getPixel(final int rowIndex, final int columnIndex){
		final int brightness;
		brightness = superRaster.getPixel(2*rowIndex, 2*columnIndex)
				   + superRaster.getPixel(2*rowIndex + 1, 2*columnIndex)
				   + superRaster.getPixel(2*rowIndex, 2*columnIndex + 1)
				   + superRaster.getPixel(2*rowIndex + 1, 2*columnIndex + 1);
		return brightness / 4;
	}
	
	@Override public Raster render(final Raytracer raytracer){
		 
		superRaster.render(raytracer);
		return this;
	}
}
