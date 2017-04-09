package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;

import java.util.List;
import java.util.ArrayList;

/** 
 *  Defines a sphere consisting of radius and center.
 *  provides method intersections that will return all the intersections with a sphere, if any.
 *  
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 *
 */
public class Sphere implements Primitive {
	// CHECKSTYLE:OFF MagicNumber
  /** Radius of Sphere. */
  private final double sphereRadius;
  
  /** Center of Sphere. */
  private final Point sphereCenter;
  
  /** Surface properties of this primitive. */
  private final Surface surface  = new Surface();

  /** 
   * creates a new Sphere.
   * 
   * @param sphereCenter Coordinate in 3D space of spheres center.
   * @param sphereRadius Spheres radius.
   * @exception if sphere radius is zero or less than zero.
   */
  public Sphere(final Point sphereCenter,final double sphereRadius) {
	  assert sphereCenter != null:"should not be null";
    if(sphereRadius<=0)
      throw new IllegalArgumentException();
    this.sphereCenter = sphereCenter;
    this.sphereRadius = sphereRadius;
  }
  
  @Override public int hashCode(){
    throw new UnsupportedOperationException();
  }
  
  @Override public boolean equals(final Object other){
	    if (other == this) return true;
	    if (!(other instanceof Sphere))return false;
	    final Sphere otherSphere = (Sphere)other;
	    final double gap = 1e-12;
	    boolean result = false;
	    if(Math.abs(this.getSphereRadius() - otherSphere.getSphereRadius()) < gap
	        && this.getSphereCenter().equals(otherSphere.getSphereCenter())) result = true; 
	    return result;
	  }

  public double getSphereRadius() {
    return sphereRadius;
  }

  public Point getSphereCenter() {
    return sphereCenter;
  }
  
  public Surface getSurface(){
	  return surface;
  }

  /**
   * Nearest intersection of ray (point and vector) with the sphere.
   *
   * @param ray Direction of ray.
   * @return Empty List, if there is no intersection. One List entry, if the ray touches. Two List entries, if the ray hits.
   * @see <a href="http://www.siggraph.org/education/materials/HyperGraph/raytrace/rtinter1.htm">http://www.siggraph.org/education/materials/HyperGraph/raytrace/rtinter1.htm</a>
   * @see <a href="http://en.wikipedia.org/wiki/Ray-sphere_intersection">http://en.wikipedia.org/wiki/Ray-sphere_intersection</a>
   */
  @Override 
  public List<Intersection> intersections(final Ray ray) { // TODO auslagern
	  assert ray != null:"should not be null";
    
    final List<Intersection> intersectionList = new ArrayList<>();
    
    // Vector from Center of sphere to the the Ray Point.
    final Vector rayCenterVector = getSphereCenter().vectorTo(ray.getRayPoint());
    
    // Normalized ray Vector.
    final Vector rayVector = ray.getRayVectorNormalized();
    final double quadEquationB = 2*(rayVector.dotProduct(rayCenterVector));
    final double quadEquationC = rayCenterVector.dotProduct(rayCenterVector) - getSphereRadius()*getSphereRadius();
    final double discriminant = quadEquationB*quadEquationB - 4*quadEquationC;
    
    if(discriminant==0){
      final double distance = -quadEquationB/2;
      final Point intersectionPoint = ray.getRayPoint().shiftPoint(rayVector.scalarMult(distance));
      final Intersection intersection = new Intersection(intersectionPoint, this, distance, true);
      intersectionList.add(intersection);
    }
    else if(discriminant>0){ 
      final double droot = Math.sqrt(discriminant);
      final double distanceNear = (-quadEquationB - droot)/2;
      final double distanceFar = (-quadEquationB + droot)/2;
      if(distanceNear > 0){
        final Point intersectionPointNear = ray.getRayPoint().shiftPoint(rayVector.scalarMult(distanceNear));
        final Intersection intersectionNear = new Intersection(intersectionPointNear, this, distanceNear, true);
        intersectionList.add(intersectionNear);
      }
      if(distanceFar > 0){
        final Point intersectionPointFar = ray.getRayPoint().shiftPoint(rayVector.scalarMult(distanceFar));
        final Intersection intersectionFar = new Intersection(intersectionPointFar, this, distanceFar, false);
        intersectionList.add(intersectionFar);
      }
    }
    return intersectionList;
  }
  
  @Override public Vector getNormal(final Point point){
	  return getSphereCenter().vectorTo(point);
  }
  
  @Override public String toString(){
    return String.format("Sphere of radius: %f with center at %s", getSphereRadius(), getSphereCenter().toString());
  }
//CHECKSTYLE:ON MagicNumber
}
