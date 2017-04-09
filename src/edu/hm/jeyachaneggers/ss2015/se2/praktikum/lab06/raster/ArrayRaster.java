package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster;

import java.util.stream.Stream;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;

/**
 * Builds the raster object that contains all the brightness values for the image.
 * ArrayRaster invokes tracerPrimary so the absolute brightness values can be calculated.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
 class ArrayRaster implements Raster {
	
	/** max. brightness value of PGM.*/
	private static final int MAXBRIGHTNESS = 255;

	/** The array with all pixels in it.*/
	private final int[][] raster;


	/**
	 * Contains a int Array to fill in the pixel.
	 * 
	 * @param width of the resolution.
	 * @param height of the resolution.
	 * @throws IllegalArgumentException if width or height is zero or negative.
	 */
	ArrayRaster(final int width,final int height){
		if(width<=0 || height<=0)
			throw new IllegalArgumentException();
		this.raster = new int[height][width];
	}

	@Override
	public int getWidth() {
		return raster[0].length;
	}

	@Override
	public int getHeight() {
		return raster.length;
	}
	
	/**
	 * Copies objects raster array.
	 * 
	 * @return copy of raster array.
	 */
	int[][] getArray(){
		final int[][] returnRaster = new int[getHeight()][getWidth()];
		for(int rowIndex=0; rowIndex<getHeight(); rowIndex++){
			for(int columnIndex=0; columnIndex<getWidth(); columnIndex++){
				returnRaster[rowIndex][columnIndex] = raster[rowIndex][columnIndex];
			}
		}
		return returnRaster;
	}


	@Override
	public int getPixel(final int rowIndex,final int columnIndex) {
		// values bigger than array size
		if(rowIndex>=getHeight() || columnIndex>=getWidth())
			throw new IllegalArgumentException();
		// negative values
		if(rowIndex<0 || columnIndex<0)
			throw new IllegalArgumentException();
		return raster[rowIndex][columnIndex];
	}

	@Override
	public Raster render(final Raytracer raytracer) {
		final double viewportBoundary = 1.0;
		Stream.iterate(0, rowIndex -> rowIndex + 1)
		//.parallel() // TODO parallel und courseware geht nicht
		.limit(getHeight())
		//.peek(System.out::println)
		.forEach(rowIndex -> {
				Stream.iterate(0, columnIndex -> columnIndex + 1)
				//.peek(System.out::println)
				.limit(getWidth())
				.forEach(columnIndex -> {
					final double viewportrelativeX = 2.0*columnIndex/getWidth()-viewportBoundary+(1.0/getWidth());
					final double viewportrelativeY = 2.0*rowIndex/getHeight()-viewportBoundary+(1.0/getHeight());
					final double relativeBrightness = raytracer.tracePrimary(viewportrelativeX, viewportrelativeY);
					raster[rowIndex][columnIndex]=(int)(MAXBRIGHTNESS*relativeBrightness);
				});
		});
		return this;
	}
}
