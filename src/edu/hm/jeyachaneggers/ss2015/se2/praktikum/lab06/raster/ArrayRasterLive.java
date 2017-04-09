package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.raster;
//package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab05.raster;
//
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//
//import javax.swing.JComponent;
//import javax.swing.JFrame;
//
//import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab05.tracer.Raytracer;
//
//public class ArrayRasterLive extends JComponent  implements Raster{
//
//	/** max. brightness value of PGM.*/
//	private static final int MAXBRIGHTNESS = 255;
//
//	/** The array with all pixels in it.*/
//	private final int[][] raster;
//
//	private final BufferedImage canvas;
//
//	public ArrayRasterLive(final int width,final int height){
//		if(width<=0 || height<=0)
//			throw new IllegalArgumentException();
//		this.raster = new int[height][width];
//		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//		JFrame window = new JFrame("render window");
//		window.setSize(width, height);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.add(this);
//		window.setVisible(true);
//	}
//
//	@Override
//	public int getWidth() {
//		return raster[0].length;
//	}
//
//	@Override
//	public int getHeight() {
//		return raster.length;
//	}
//
//	@Override
//	public int getPixel(final int rowIndex,final int columnIndex) {
//		// values bigger than array size
//		if(rowIndex>=getHeight() || columnIndex>=getWidth())
//			throw new IllegalArgumentException();
//		// negative values
//		if(rowIndex<0 || columnIndex<0)
//			throw new IllegalArgumentException();
//		return raster[rowIndex][columnIndex];
//	}
//
//	@Override
//	public Raster render(final Raytracer raytracer) {
//		// TODO 
//
//		// the Value if viewport size is 1x1.
//		final double viewportBoundary = 1.0;
//
//		// Trace rays through viewport pixels from top to bottom, each row left to right ...
//		for(int rowIndex = 0; rowIndex<getHeight(); rowIndex++) {
//			for(int columnIndex = 0; columnIndex < getWidth(); columnIndex++) {
//
//				// viewport with size 1 relative x and y.
//				final double viewportrelativeX = 2.0*columnIndex/getWidth()-viewportBoundary+(1.0/getWidth());
//				final double viewportrelativeY = 2.0*rowIndex/getHeight()-viewportBoundary+(1.0/getHeight());
//
//				// brightness of the point from 0.0 to 1.0.
//				final double relativeBrightness = raytracer.tracePrimary(viewportrelativeX, viewportrelativeY);
//
//				final int absoluteBrightness = (int)(MAXBRIGHTNESS*relativeBrightness);
//
//				raster[rowIndex][columnIndex] = absoluteBrightness;
//
//				canvas.setRGB(columnIndex , getHeight()-1-rowIndex, byteToARGB(absoluteBrightness));
//				repaint();
//			}
//		}
//		return this;
//	}
//
//	@Override public void paintComponent(final Graphics g){
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.drawImage(canvas, null, null);
//	}
//
//	private static int byteToARGB(final int brightness) {
//		final int bitsInByte = 8;
//		final int opaqueBitmask = 0xFF;
//		return ((opaqueBitmask << bitsInByte | brightness) << bitsInByte | brightness) << bitsInByte | brightness;
//	}
//}
//
