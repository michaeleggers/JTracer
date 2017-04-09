package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;
import java.util.stream.Stream;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;

/**
 * The Raytracer class sends a primary ray to relative viewport coordinates. Depending on
 * if primary ray hits another intersection with specific surface properties the Raytracer will
 * run recursively. 
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 15:48:05
 */
public class Raytracer {

	/** Scene with Looker and Primitive. */
	private final Scene scene;

	/** 
	 * Generates a new Raytracer that has all data about the scene.
	 * 
	 * @param scene Scene to be rendered.
	 */
	public Raytracer(final Scene scene){
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

	/**
	 * A raytrace for a pixel will always start here. If there are reasons (eg ray hits reflecting surface) to
	 * follow the ray further through the scene the trace method will run recursively.
	 * 
	 * @param relativeH vertical relative viewport coordinate, -1<=h<=1.
	 * @param relativeV horizontal relative viewport coordinate, -1<=v<=1.
	 * @return brightness value from 0.0 (black) to 1.0 (white).
	 * @throws IllegalArgumentException if relativeH or relativeV is not between 1 and -1.
	 */
	public double tracePrimary(final double relativeH, final double relativeV){
		if(Math.abs(relativeH)>1 || Math.abs(relativeV)>1)
			throw new IllegalArgumentException();
		
		// generate new ray to shoot
		final Ray ray = getScene().getLooker().getPrimaryRay(relativeH, relativeV);

		return trace(ray);
	}
	
	/**
	 * Trace a ray through the scene.
	 * 
	 * @param ray the Ray to shoot through the scene.
	 * @return Pixel brightness value between 0 and 1.
	 */
	double trace(final Ray ray){
		// compute distances of intersection ray/sphere nearest to camera
		final Optional<Intersection> optionalIntersection = getScene().findIntersection(ray);
		
		final BooleanPromise isShadowed = new BooleanPromise(() -> new Shadowed().calculate(scene, optionalIntersection) > 0);
		final double result = Stream.of(new Ambient(),
										new Diffuse(isShadowed),
										new SpecularHighlight(isShadowed, ray),
										new Reflexion(ray, this))
								.map(lightModel -> lightModel.calculate(getScene(), optionalIntersection))
								.mapToDouble(Double::doubleValue)
								.sum();
		if(result > 1)
			return 1;
		return result;
	}
}
