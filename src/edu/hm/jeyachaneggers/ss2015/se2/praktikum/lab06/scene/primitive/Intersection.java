package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;

/**
 * Encapsulates data about an intersection point (between ray and primitive).
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 *
 */
public class Intersection implements Comparable<Intersection> {
  
  /** Intersection Point between ray and primitive in the scene. */
  private final Point intersectionPoint;
  
  /** the Primitive (e.g. Plane, Sphere, ...) that may be hit by a ray. */
  private final Primitive intersectionObject;
  
  /** distance between the rays start point and the primitive. */
  private final double distance;
  
  /** true, if ray goes into primitive. false, if ray goes out of the primitive. */
  private final boolean inOrOut;
  
  /** Surface data of a primitive. */
  private final Surface surface;
  
  /**
   * Generates a new Intersection. An instance of Intersection contains valid data about a ray-primitive intersection.
   * 
   * @param intersectionPoint Where does the ray hit?
   * @param intersectionObject What does the ray hit?
   * @param distance How far away is the intersection point from the rays origin?
   * @param inOrOut Does the ray penetrate (true) or escape (false) the primitive?
   */
  public Intersection(final Point intersectionPoint,final Primitive intersectionObject,final double distance,final boolean inOrOut){
	  assert intersectionPoint != null: "should not be null";
	  assert intersectionObject != null: "should not be null";
	if(distance<0)
		throw new IllegalArgumentException();
	this.intersectionPoint = intersectionPoint;
    this.intersectionObject = intersectionObject;
    this.distance = distance;
    this.inOrOut = inOrOut;
    this.surface = intersectionObject.getSurface();
  }

  public Point getIntersectionPoint() {
    return intersectionPoint;
  }

  public Primitive getIntersectionObject() {
    return intersectionObject;
  }

  public double getDistance() {
    return distance;
  }

  public boolean isInOrOut() {
    return inOrOut;
  }
  
  public Surface getSurface(){
	  return surface;
  }
  
	/** 
	 * hashCode is not implemented in the right way. 
	 * 
	 * @throw UnsupportedOperationException if a method try to use it.
	 */
	@Override
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	/** This equals method was modified manually.
	 * Is no longer transitive:  
	 * Two double distances are the same, when their difference 
	 * is lower than 10^-12. 
	 */
	@Override // TODO eventuell nochmal pruefen!!! 
	public boolean equals(final Object obj) {
		boolean result = false;
	    if (obj == this) result = true;
	    if (!(obj instanceof Intersection))result = false;
	    final Intersection otherIntersection = (Intersection)obj;
	    final double schiedermeierEps = 1E-12;
	    if(this.getIntersectionPoint().equals(otherIntersection.getIntersectionPoint())
	        && this.getIntersectionObject().equals(otherIntersection.getIntersectionObject())
	        && Math.abs(this.getDistance() - otherIntersection.getDistance()) < schiedermeierEps
	        && this.isInOrOut() == otherIntersection.isInOrOut()
	        ) result = true; 
	    return result;
	}
  
  /**
   * String representation of an Intersection. 
   * 
   * @return String containing an Intersections data.
   */
  public String toString(){
    final String inOut;
    if(isInOrOut())
      inOut = "ray penetrates from the outside.";
    else
      inOut = "ray escapes from the inside.";
    
    return String.format("Intersection with: %s%n"
                       + "at Point: %s%n"
                       + "at distance: %f%n"
                       + "%s%n", getIntersectionObject(), getIntersectionPoint().toString(), getDistance(),inOut);
  }

  @Override
  public int compareTo(final Intersection other) {
	  final Double thisdouble = this.getDistance();
	  final Double otherdouble = other.getDistance();
	  return thisdouble.compareTo(otherdouble);
  }  
  
}
