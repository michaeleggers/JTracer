package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;

import java.util.List;
import java.util.ArrayList;

/**
 * a plane is an infinite set of points being defined by a point which tells
 * us how far the plane is away from the origin and a normal vector standing
 * perpendicular to the plane which defines the planes orientation.
 *
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public class Plane implements Primitive{
  
  /** a Point in the Plane. */
  private final Point planePoint;
  
  /** a Vector perpendicular to the Plane. */
  private final Vector normalVector;
  
  /** Surface properties of this primitive. */
  private final Surface surface = new Surface();
  
  /**
   * Creates a plane.
   * @param planePoint Point in the plane.
   * @param planeNormalVector Vector perpendicular to the plane.
   */
  public Plane(final Point planePoint,final Vector planeNormalVector){
	  assert planePoint != null:"plane point should not be null";
	  assert planeNormalVector != null:"plane normal vector should not be null";
	  if(planeNormalVector.length() == 0)
		  throw  new IllegalArgumentException();
    this.planePoint = planePoint;
    this.normalVector = planeNormalVector.normalize();
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
	 *  Is no longer transitive.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Plane))
			return false;
		final Plane other = (Plane) obj;
		return this.planeContainsPlane(other);
	}
  
  public Point getPlanePoint() {
    return planePoint;
  }



  public Vector getNormalVector() {
    return normalVector;
  }
  
  /**
   * Reveals if a Point contains in the plane.
   * 
   * @param point to test.
   * @return true if contains and false if not.
   */
  private boolean planeContainsPoint(final Point point) {
	  assert point != null:"plane contains point: should not be null";
	  final Vector inter = getPlanePoint().vectorTo(point);
	  final double result = getNormalVector().dotProduct(inter);
	  boolean contains = false;
	  if (result == 0)
		  contains = true;
	  return contains;

  }
  
  /**
   * Reveals if a Plane contains in another Plane.
   * 
   * @param plane to check with.
   * @return If it contains true else false.
   */
  private boolean planeContainsPlane(final Plane plane){
	  assert plane != null:"plane contains plane: should not be null";
	  boolean result = false;
	  final Vector planeVectorNormalized = plane.getNormalVector().normalize();
	  final Vector thisPlaneVectorNormalized = getNormalVector().normalize();
	  final double product = thisPlaneVectorNormalized.dotProduct(planeVectorNormalized); 
	  if(product == 1 || product == -1){
		  result = this.planeContainsPoint(plane.getPlanePoint());
	  }
	  return result;
  }

  /**
   * Computes intersection data when a ray hits the plane.
   * 
   * @see <a href="http://www.scratchapixel.com/old/lessons/3d-basic-lessons/lesson-7-intersecting-simple-shapes/ray-plane-and-ray-disk-intersection/">Intersection of a ray with plane or disk</a>
   * @see <a href="https://www.cs.princeton.edu/courses/archive/fall00/cs426/lectures/raycast/sld017.htm">https://www.cs.princeton.edu/courses/archive/fall00/cs426/lectures/raycast/sld017.htm</a>
   */
  @Override public List<Intersection> intersections(final Ray ray){
	  assert ray != null:"intersection: should not be null";
    
    final List<Intersection> intersections = new ArrayList<>();
    
    final Point rayPoint = ray.getRayPoint();
    
    final Vector rayVector = ray.getRayVectorNormalized();
    
    final Vector planeVector = getNormalVector().normalize();
    
    // Compute vector from ray base point to given point in the plane.
    final Vector baseToPlane = rayPoint.vectorTo(getPlanePoint());
    
    // Compute the dot product between ray vector and planes normal vector.
    final double denom = rayVector.dotProduct(planeVector);
    
    // if denom is close to zero plane and ray are parallel compute all relevant data for Intersection. // TODO calculate right inOrOut
    if(denom != 0){
      final double distance = baseToPlane.dotProduct(planeVector)/denom;
      if(distance >= 0){
        final Point intersectionPoint = rayPoint.shiftPoint(rayVector.scalarMult(distance));
        final Intersection intersection = new Intersection(intersectionPoint, this, distance, true);
        intersections.add(intersection);
      }
    }
    return intersections; 
  }
  
  @Override public Vector getNormal(final Point point){
	  return getNormalVector();
  }
  
  @Override public String toString(){
    return String.format("Plane with point from origin at: %s and orientation: %s", getPlanePoint().toString(), 
                                                                                    getNormalVector().toString());
  }

}
