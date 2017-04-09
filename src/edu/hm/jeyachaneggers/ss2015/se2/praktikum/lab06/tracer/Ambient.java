package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.Scene;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Surface.Property;

/**
 * Ambient shading model. It even shines without a light.
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 15:04:41
 */
public class Ambient implements LightModel{
	
	@Override
	public double calculate(final Scene scene, final Optional<Intersection> intersection){
		if(!intersection.isPresent())
			return 0;
		return intersection.get().getIntersectionObject().getSurface().get(Property.AmbientRatio);
	}
}
