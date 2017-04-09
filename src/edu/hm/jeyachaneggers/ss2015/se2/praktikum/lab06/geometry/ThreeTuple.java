package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;

/**
 *	3-Tupel
 *	Kennzeichnet die x,y,z Komponenten eines Punktes oder Vektors.
 *
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */

public abstract class ThreeTuple {
  
	/** represents x component of a coordinate in 3D space. */
	private final double xCoordinate;
	
	/** represents y component of a coordinate in 3D space. */
	private final double yCoordinate;
	
	/** represents z component of a coordinate in 3D space. */
	private final double zCoordinate;
	
	/**
	 * Initializes three new double values.
	 * 
	 * @param x component.
	 * @param y component.
	 * @param z component.
	 */
	ThreeTuple(final double x,final double y,final double z){
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.zCoordinate = z;
	}

	public double getX() {
		return xCoordinate;
	}

	public double getY() {
		return yCoordinate;
	}

	public double getZ() {
		return zCoordinate;
	}
	
	
  /**
   * Subtracts a triple from another.
   * 
   * @param geom the Point or Vector components to be subtracted from this.
   * @return triple double array.
   */
	double[] sub(ThreeTuple geom) {
		assert geom != null:"ThreeTuple ABC: Tried to invoke method with null";
		return new double[]{this.getX()-geom.getX(),this.getY()-geom.getY(),this.getZ()-geom.getZ()};
	}

  /**
   * String representation of double components x, y, z.
   * 
   * @return String consisting of 1st to 3rd element of components.
   */
  @Override
  public String toString(){
    return String.format("[ %.12f, %.12f, %.12f ]", getX(), getY(), getZ());
  }
}
