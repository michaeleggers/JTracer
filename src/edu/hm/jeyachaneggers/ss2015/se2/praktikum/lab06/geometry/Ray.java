package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry;


/**
 * Represents a ray in 3D space consisting of a Point and a Vector.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-04
 *
 */
public final class Ray {

  /** Start of the ray. */
  private final Point rayPoint;
  
  /** Vector is responsible for the rays direction. */
  private final Vector rayVector;
  
  /** Normalized ray Vector. */
  private final Vector rayVectorNormalized;
  
  /** Initial weight value for a ray. */
  private double maxWeight = 0.99999;

  /**
   * Creates a new ray starting from a point (rayPoint) heading to vectors (rayVector) direction.
   * 
   * @param rayPoint Defines where the ray starts from.
   * @param rayVector Defines the direction of the ray.
   */
  public Ray(final Point rayPoint,final Vector rayVector) {
	  assert rayPoint != null:"Ray class: tried to invoke Ctor with null";
	  assert rayVector != null:"Ray class: tried to invoke Ctor with null";
    if(rayVector.length()==0)
    	throw new IllegalArgumentException();
    this.rayPoint = rayPoint;
    this.rayVector = rayVector;
    this.rayVectorNormalized = rayVector.normalize();
  }

  public Point getRayPoint(){
    return rayPoint;
  }

  public Vector getRayVector(){
    return rayVector;
  }
  
  public Vector getRayVectorNormalized() {
    return rayVectorNormalized;
  }
  
  public void setMaxWeight(final double newMaxWeight){
	  maxWeight = newMaxWeight;
  }
  
  public double getMaxWeight(){
	  return maxWeight;
  }
  
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(final Object obj) {
		if (getClass() != obj.getClass())
			return false;
		assert obj != null: "Ray class: tried to invoke equals with null";
		final Ray other = (Ray) obj;
		boolean result = false;
		if (getRayPoint().equals(other.getRayPoint()) && getRayVector().equals(other.getRayVector()))
			result = true;
		return result;
	}
	
	@Override public String toString(){
	    return String.format("Raypoint: %s%n"
                + "Rayvector: %s%n", getRayPoint(), getRayVector());
	}
}
