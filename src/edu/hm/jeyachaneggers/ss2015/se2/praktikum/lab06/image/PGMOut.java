package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.image;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.Raster;

/** 
 * Methods for creating PGM image.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
class PGMOut implements Image {

	/** Placeholder new line. */
	private static final String NEWLINE = "\n";

	/** Placeholder blank. */
	private static final String SPACE = " ";

	/** Placeholder PGM File header magic number. */
	private static final String MAGICNUMBER = "P2";

	/** Placeholder maximum brightness. */
	private static final String MAXBRIGHTNESS = "255";

	/** 
	 * creates PGM data.
	 * 
	 * @param pixelRaster two-dimensional array storing brightness values.
	 * @return String that holds the complete PGM data, including header and brightness values.
	 * @exception NullPointerException if intArray has no values.
	 */
	@SuppressWarnings("PMD.UseVarargs") 
	String asString(final Raster pixelRaster) { 
		assert pixelRaster != null: "shuld not be null";
		if(pixelRaster.getHeight() == 0) throw new IllegalArgumentException();
		
		final StringBuilder output = new StringBuilder();
		final int height = pixelRaster.getHeight();
		final int width = pixelRaster.getWidth();
		// create PGM file header // TODO nicht quad. Groesse ermoeglichen!
		output.append(MAGICNUMBER+NEWLINE 											// magic number of ASCII portable gray map
				+ width + SPACE + height +NEWLINE		// pixel resolution of image
				+MAXBRIGHTNESS+NEWLINE);											// maximum brightness

		// adds the actual data for brightness to output. // TODO Fragen: Array gespiegelt auslesen, oder in toRaster anders schreiben?
		for(int rowIndex=height-1; rowIndex >= 0; rowIndex--){
			for(int columnIndex=0; columnIndex<width; columnIndex++){
				output.append(String.format("%3d ", pixelRaster.getPixel(rowIndex, columnIndex)));
			}
			output.append(NEWLINE);
		}
		return output.toString();
	}

	/** 
	 * Prints the PGM data.
	 * 
	 * @param pixelRaster brightness values from left to right, bottom to top.
	 */
	@SuppressWarnings("PMD.UseVarargs") 
	public void save(final Raster pixelRaster){
		assert pixelRaster != null: "shuld not be null";
		final String result = asString(pixelRaster);
		System.out.println(result);
	}

}
