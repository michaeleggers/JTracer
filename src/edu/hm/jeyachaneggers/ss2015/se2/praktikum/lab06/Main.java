package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06;


import java.io.IOException;


import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.image.Image;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster.Raster;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer.Raytracer;


/* (C) 2015, Vithya Jeyachandra & Michael Eggers, jeyachan@hm.edu, eggers@hm.edu
 * Java 1.8.0_40, Windows 8.1, x64
 * rakon (AMD A8 processor 2.0 GHz/2,4 GHz, 4 Cores, 8 GB RAM)
 */


/**
 * Main Class is the class you need in order to run the black magic!
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public class Main {

	/**
	 * Entry point.
	 * The program prints an ASCII portable gray map (pgm) image to System.out.
	 *
	 * @param args Command line args: Resolution of pgm image.
	 * @see <a href="http://en.wikipedia.org/wiki/Netpbm_format">http://en.wikipedia.org/wiki/Netpbm_format</a>
	 * @throws IOException if something went wrong.
	 * @throws ClassNotFoundException 
	 */
	public static void main(final String... args) throws IOException, ClassNotFoundException {

		final Scene scene = Scene.make("LoadedScene","vielKugelnScene.txt");
		final Raytracer tracer = new Raytracer(scene);
		final Raster raster = Raster.make("Supersampled","Supersampled","ArrayRaster","2400","2400").render(tracer);
		//Image.make("PGMOut").save(raster);
		Image.make("PNGImage","dingens.png").save(raster);
	}
}