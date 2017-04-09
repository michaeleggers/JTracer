package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;

/**
 * All light models (eg Diffuse, Ambient, ...) have the same assignment: Compute its contribution
 * to the final pixel value depending on the intersections surface property.
 * By the way: Interfaces are great things! We love them!
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 15:31:39
 */
public interface LightModel {
	
	/**
	 * Compute its contribution to the final pixel value.
	 * 
	 * @param scene the actual scene.
	 * @param intersection We want to compute a brightness-value for an intersection, so that we hand over an intersection is pretty obvious, isn't it?
	 * @return the light models brightness-value contribution.
	 */
	double calculate(final Scene scene, final Optional<Intersection> intersection);
	
	/**
	 * Computes a reflected ray.
	 * 
	 * @param intersection The intersection the ray should reflect at.
	 * @param ray The incoming ray to reflect.
	 * @return the new reflected ray.
	 */
	default Ray reflectRay(final Optional<Intersection> intersection, final Ray ray){
		final Point intersectionPoint = intersection.get().getIntersectionPoint();
		final Vector incomingRayVector = ray.getRayVector().normalize();
		final Vector normalVector = intersection.get().getIntersectionObject().getNormal(intersectionPoint).normalize();
		final Point shiftedIntersectionPoint = intersectionPoint.shiftPoint(normalVector.scalarMult(1E-12));
		
		final Point innerPoint = shiftedIntersectionPoint.shiftPoint(incomingRayVector);
		final Vector innerVector = shiftedIntersectionPoint.vectorTo(innerPoint).normalize();
		final double lengthRatio = normalVector.inverseVector().dotProduct(innerVector);
		final Vector goOutsideVector = normalVector.scalarMult(lengthRatio*2);
		final Point reflectionPoint = innerPoint.shiftPoint(goOutsideVector);
		final Vector reflectionVector = shiftedIntersectionPoint.vectorTo(reflectionPoint).normalize();
		return new Ray(shiftedIntersectionPoint, reflectionVector);
	}
}
