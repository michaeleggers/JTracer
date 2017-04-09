package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Surface.Property;

/**
 * The reflexion light model computes surfaces that reflect other surfaces.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 16:01:57
 */
public class Reflexion implements LightModel{
	
	/** Threshold value. If a ray weights below this value the recursion will be stopped. */
	private static final int THRESHOLD = 256;
	
	/** A tacer object is needed to start the recursion. */
	private final Raytracer tracer;
	
	/** Needed to compute the reflection ray and to get actual ray weight. */
	private final Ray ray;
	

	/**
	 * A reflexion always needs the ray that shall be reflected on a surface.
	 * Also a Raytracer object is required to start a recursion.
	 * 
	 * @param ray Ray that is (maybe) reflected.
	 * @param tracer Actual raytracer.
	 */
	Reflexion(final Ray ray, final Raytracer tracer){
		this.ray = ray;
		this.tracer = tracer;
	}

	public Raytracer getTracer() {
		return tracer;
	}

	public Ray getRay() {
		return ray;
	}

	@Override
	public double calculate(final Scene scene, final Optional<Intersection> intersection){
		if(!intersection.isPresent())
			return 0;
		
		// Is enum property r > 0? If so we can continue, otherwise there will be no relfexion contribution to the pixel.
		final double reflexionRatio = intersection.get().getSurface().get(Property.ReflexionRatio);
		if(reflexionRatio == 0)
			return 0;
		
		// Compute weight for the potential new reflected ray.
		final Ray actualRay = getRay();
		final double actualRayWeight = actualRay.getMaxWeight();
		final double newRayWeight = actualRayWeight * reflexionRatio;
		
		// If the new rays weight is below a threshold value, we stop the recursion. Without this test the recursion would never stop.
		if(newRayWeight <= 1/THRESHOLD)
			return 0;
		
		// Compute reflected ray and set its new weight.
		final Ray reflectionRay = reflectRay(intersection, actualRay);
		reflectionRay.setMaxWeight(newRayWeight);
		
		// Now pursue the reflected ray (again). The recursion starts here.
		return getTracer().trace(reflectionRay);
	}
}
