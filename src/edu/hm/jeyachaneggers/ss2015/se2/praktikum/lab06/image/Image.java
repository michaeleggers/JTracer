package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.image;

import java.io.IOException;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.Raster;

/**
 * Provides methods to process image data to an output format.
 * 
 * @author Michi
 *
 */
public interface Image {

	/** 
	 * Prints the PGM data.
	 * 
	 * @param raster contains all data for the output file.
	 * @throws IOException if png-file can not be created.
	 */
	void save(final Raster raster)  throws IOException;
	
	/**
	 * Factory method that can create certain types of images.
	 * 
	 * @param args Accepts Strings in the form: "PNGImage","dingens.png" which will put the image in a PNG File with filename dingens.png.
	 * @return Concrete Image class that represents the raytraced image in its own way.
	 * @throws ClassNotFoundException If there is no concrete class that fits the first argument.
	 */
	 static Image make(final String... args) throws ClassNotFoundException{
		for(final String argument : args)
			assert argument != null: "shuld not be null";
		int arg = 0;
		switch(args[arg++]){
		case "PNGImage" : return new PNGFile(args[arg]);
		
		case "PGMOut" : return new PGMOut();
		
		default :
			throw new ClassNotFoundException();
		}
	}
}
