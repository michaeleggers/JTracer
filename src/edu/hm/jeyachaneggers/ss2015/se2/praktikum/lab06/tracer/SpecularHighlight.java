package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Primitive;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Surface.Property;

/**
 * Specular highlight computes a specular on the primitive.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 16:29:11
 */
public class SpecularHighlight implements LightModel{

	/** We need to know if actual intersection is in shadow or not. */
	private final BooleanPromise isShadowed;
	
	/** We need a ray to reflect it. */
	private final Ray ray;

	/**
	 * Makes a specular shader ready for computation.
	 * 
	 * @param isShadowed Is this intersection in shadow?
	 * @param ray Ray that may be reflected (if the surface property qualifies for reflection.
	 */
	SpecularHighlight(final BooleanPromise isShadowed, final Ray ray){
		this.isShadowed = isShadowed;
		this.ray = ray;
	}
	
	private Ray getRay(){
		return ray;
	}

	@Override
	public double calculate(final Scene scene, final Optional<Intersection> intersection){
		double result = 0;
		if(!intersection.isPresent())
			return result;
		final Primitive intersectionObject = intersection.get().getIntersectionObject();
		final double specularRatio = intersectionObject.getSurface().get(Property.SpecularRatio);
		if(specularRatio != 0 && scene.getLight().isPresent() && !isShadowed.get()){			
			final Point intersectionPoint = intersection.get().getIntersectionPoint();
			final Vector lightVector = intersectionPoint.vectorTo(scene.getLight().get()).normalize();
			final Ray incomingRay = getRay();
			final Ray reflectedRay = reflectRay(intersection, incomingRay);
			
			final double cosineML = lightVector.dotProduct(reflectedRay.getRayVector().normalize());
			
			if(cosineML >= 0 && specularRatio > 0)
				result = Math.pow(cosineML, intersection.get().getSurface().get(Property.SpecularExponent)) * specularRatio;
		}
		return result;
	}
}
