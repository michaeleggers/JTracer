package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.Raster;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

import javax.imageio.ImageIO;

/**
 * Generates PNG file.
 * 
 * @author Michi
 *
 */
class PNGFile implements Image{
	
	/** PNGs filename (what about looking at the variables name by the way??). */
	private final String filename;
	
	/** Makes new PNG file.
	 * 
	 * @param filename A file needs a name!
	 */
    PNGFile(final String filename){
    	this.filename = filename;
    }
    
    String getFilename(){
    	return filename;
    }
	
	/**
	 * Writes a PNG file to disk.
	 * 
	 * @param raster Raster containing brightness values.
	 * @throws IOException If there was an error.
	 */
    void writePNG(final Raster raster) throws IOException {
        final int width = raster.getWidth();
        final int height = raster.getHeight();
		
        final BufferedImage image = new BufferedImage(width, height, TYPE_INT_ARGB);

        for(int rowIndex=0; rowIndex<height; rowIndex++)
        	for(int columnIndex=0; columnIndex<width; columnIndex++){
        		final int brightness = raster.getPixel(rowIndex, columnIndex);
        		image.setRGB(columnIndex, height-1-rowIndex, byteToARGB(brightness));
        	}
        ImageIO.write(image, "png", new File(getFilename()));
    }
    
    /**
     * Does some blackmagic. No time atm to figure out what it does. 
     * @param brightness brightness value from 0 to 255.
     * @return return color value in ARGB format.  
     */
    private static int byteToARGB(final int brightness) {
        final int bitsInByte = 8;
        final int opaqueBitmask = 0xFF;
        return ((opaqueBitmask << bitsInByte | brightness) << bitsInByte | brightness) << bitsInByte | brightness;
    }
    
    @Override public void save(final Raster raster) throws IOException{
    	assert raster != null: "shuld not be null";
    	writePNG(raster);
    }
}