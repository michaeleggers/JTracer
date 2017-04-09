package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Primitive;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Surface.Property;

/**
 * Diffuse shading model. It uses a light source to compute its lambert shader.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 15:23:22
 */
public class Diffuse implements LightModel{

	/** BooleanPromise stores some code in its object variables to figure out if there is a shadow or not. */
	private final BooleanPromise isShadowed;

	/**
	 * Create a new Diffuse shader.
	 * 
	 * @param isShadowed the (presumably) lambda expression.
	 */
	Diffuse(final BooleanPromise isShadowed){
		this.isShadowed = isShadowed;
	}

	@Override
	public double calculate(final Scene scene, final Optional<Intersection> intersection){
		double result = 0;
		if(!intersection.isPresent())
			return result;
		final Primitive intersectionObject = intersection.get().getIntersectionObject();
		final double diffuseRatio = intersectionObject.getSurface().get(Property.DiffuseRatio);
		if(diffuseRatio != 0  && scene.getLight().isPresent() && !isShadowed.get()){
			final Point intersectionPoint = intersection.get().getIntersectionPoint();
			final Vector normalVector = intersectionObject.getNormal(intersectionPoint).normalize();
			final Optional<Point> pointLight = scene.getLight();
			final Vector lightVector = intersectionPoint.vectorTo(pointLight.get()).normalize();

			// Dot-product between light and normal vector. Used for diffuse brightness, see lambertian rule.
			final double cosine = normalVector.dotProduct(lightVector);
			
			if(cosine >= 0)
				result = diffuseRatio*cosine;
		}
		return result;
	}
}
