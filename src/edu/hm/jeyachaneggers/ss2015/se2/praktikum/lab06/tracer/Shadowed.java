package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;

/**
 * Shadowed checks if there the intersection is in shadow or not.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 16:16:24
 */
public class Shadowed implements LightModel{

	@Override
	public double calculate(final Scene scene, final Optional<Intersection> intersection){
		if(!intersection.isPresent() || !scene.getLight().isPresent())
			return 0;
		final Point intersectionPoint = intersection.get().getIntersectionPoint();
		final Point camPoint = scene.getLooker().getCameraPosition();
		final Optional<Point> pointLight = scene.getLight();
		final double shiftValue = 1E-10;
		final Point shiftedIntersectionPoint = intersectionPoint.shiftPoint(intersectionPoint.vectorTo(camPoint).normalize().scalarMult(shiftValue));
		final Optional<Intersection> secondaryIntersection = scene.findIntersection(new Ray(shiftedIntersectionPoint, shiftedIntersectionPoint.vectorTo(pointLight.get()).normalize()));
		double result = 0;
		final double intersectionToLightLength = intersection.get().getIntersectionPoint().vectorTo(scene.getLight().get()).length();
		if(secondaryIntersection.isPresent() && intersectionToLightLength >= secondaryIntersection.get().getDistance()){
			result = 1;
		}
		return result;
	}
}
