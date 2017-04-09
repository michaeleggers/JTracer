package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;


/** Creates a Vector in 3D Space.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public final class Vector extends ThreeTuple{
  

  /** 
   * Creates vector in 3D space.
   * 
   * @param x x direction.
   * @param y y direction.
   * @param z z direction
   */
  public Vector(final double x,final double y,final double z) {
    super(x,y,z);
  }
  
  /**
   * Creates vector in 3D space.
   * 
   * @param coordinates double array consisting of 3 doubles.
   */
  public Vector(final double... coordinates){
    this(coordinates[0], coordinates[1], coordinates[2]);
  }
  
  @Override public int hashCode(){
    throw new UnsupportedOperationException();
  }
  
  	/**
	 * Equals method is modified. Is no longer transitive. 
	 */
  @Override
  public boolean equals(final Object obj) {
    if (getClass() != obj.getClass())
      return false;
    Vector other = (Vector) obj;
    other = other.normalize();
    final Vector thisnormal = this.normalize();
    final double gap = 1e-12;
    boolean result = true;
    if (Math.abs(thisnormal.getX() - other.getX()) > gap
     || Math.abs(thisnormal.getY() - other.getY()) > gap
     || Math.abs(thisnormal.getZ() - other.getZ()) > gap)
      result = false;
    return result;
  }

  
  /**
   * Calculates vectors length.
   * 
   * @return length of vector.
   */
  public double length(){
    return Math.sqrt(getX()*getX() 
                   + getY()*getY()
                   + getZ()*getZ());
  }

  /**
   * Normalizes vector (vector with length = 1).
   * 
   * @return normalized vector.
   * @exception IllegalArgumentException if this is null vector.
   */
  public Vector normalize(){
    final double length = this.length();
    if(length == 0.0)
      throw new IllegalArgumentException("cannot normalize null-vector!");
    return new Vector(getX()/length,
                      getY()/length,
                      getZ()/length);
  }
  
  /**
   * Calculates dot product of two vectors.
   * 
   * @param vector Vector to be multiplied.
   * @return Dot product of the two vectors.
   */
  public double dotProduct(final Vector vector) {
	assert vector != null: "null vector is not possible!";
  	return getX()*vector.getX()+getY()*vector.getY()+getZ()*vector.getZ();
  }
  
  /**
   * Calculates the cross product of two vectors.
   * 
   * @param vector Vector to be multiplied.
   * @return A new vector (the cross product).
   */
  public Vector crossProduct(final Vector vector){
	assert vector != null: "shuld not be null";
    if(this.length()==0 || vector.length()==0)
		throw new IllegalArgumentException("one of two vector is (0,0,0)");
    return new Vector(this.getY()*vector.getZ() - this.getZ()*vector.getY(),
                      this.getZ()*vector.getX() - this.getX()*vector.getZ(),
                      this.getX()*vector.getY() - this.getY()*vector.getX());
  }
  
  /**
   * Generates a vector that is perpendicular to this and parallel to XZ-plane.
   * 
   * @return a Vector perpendicular to this.
   */
  public Vector perpendicularXZ(){
    return new Vector(-this.getZ(), 0, this.getX());
  }
  
  /**
   * Adds one vector to another.
   * 
   * @param vector Vector to add.
   * @return sum of two vectors.
   */
  public Vector add(final Vector vector){
	assert vector != null: "shuld not be null";
    return new Vector(this.getX() + vector.getX(),
                      this.getY() + vector.getY(),
                      this.getZ() + vector.getZ());
  }
  
  /**
   * Multiplies a vector with a scalar.
   * 
   * @param scalar Scalar value.
   * @return This vector with a new length.
   */
  public Vector scalarMult(final double scalar){
    return new Vector(this.getX() * scalar,
                      this.getY() * scalar,
                      this.getZ() * scalar);
  }
  
  /**
   * Computes the inverse of a vector.
   * 
   * @return Vector that points to the opposite direction.
   */
  public Vector inverseVector(){
	  return scalarMult(-1);
  }
  
  /**
   * Checks if this and another vector are parallel.
   * 
   * @param other The other vector.
   * @return true if they are parallel, false if not.
   */
  public boolean isParallel(final Vector other){
	  final double epsilon = 1E-10;
	  if(this.crossProduct(other).length() < epsilon)
		  return true;
	  return false;
  }
}
