package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;


/**
 * Class for generating a Point in 3D space.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public final class Point extends ThreeTuple{

	/** The origin Point.*/
	public static final Point ORIGIN = new Point(0,0,0);

	/**
	 * Creates a Point in 3D space defined by its components x,y,z.
	 * 
	 * @param x x direction
	 * @param y y direction
	 * @param z z direction
	 */
	public Point(final double x,final double y,final double z) {
		super(x,y,z);
	}

	@Override
	public int hashCode() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Equals method has been modified manually so is no longer transitive.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (getClass() != obj.getClass())
			return false;
		assert obj != null:"Point class: tried to invoke equals with null";
		final Point other = (Point) obj;
		final double gap = 1e-12;
		boolean result = true;
		if (Math.abs(this.getX() - other.getX()) > gap
		 || Math.abs(this.getY() - other.getY()) > gap
		 || Math.abs(this.getZ() - other.getZ()) > gap)
			result = false;
		return result;
	}

	/**
	 * Computes a Vector from this Point to a given Point.
	 * 
	 * @param point Point the new Vector points to.
	 * @return Vector.
	 */
	public Vector vectorTo(final Point point) {
		assert point != null:"vector to: tried to invoke equals with null";
		return new Vector(point.sub(this));
	}

	/** TODO !
	 * Goes from one point to another using a vector.
	 * 
	 * @param vector Direction to go.
	 * @return Target point we have reached using the vector.
	 */
	public Point shiftPoint(final Vector vector){
		assert vector != null:"shift point: tried to invoke equals with null";
		return new Point(getX() + vector.getX(), getY() + vector.getY(), getZ()
				+ vector.getZ());
	}
}
