package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.tracer;

import java.util.function.BooleanSupplier;

/**
 * This class serves as a helper to prevent the raytracer from redundant calculations.
 * In this case it tells a LightModels Ctor if its actual intersection is shadowed or not.
 * Check the Raytracer class. There you can see that the check for a shadow is only performed once
 * and then its value (true or false) is being stored in this class.
 * 
 * @author Michael Eggers, eggers@hm.edu
 * @author Vithya Jeyachandran, jeyachan@hm.edu
 *
 * @version 04.06.2015 15:07:03
 */
public class BooleanPromise {

	/** Is a static type of the functional interface BooleanSupplier. */
	private final BooleanSupplier supplier;
	
	/** Only if the counter is 0 the lambda expression stored in the supplier  is being performed. */
	private int counter;
	
	/** Stores the actual information if an intersection is in shadow or not. */
	private boolean isShadowed;
	
	/**
	 * You can give the Ctor a lambda expression, because BooleanSupplier is a functional interface.
	 * 
	 * @param supplier A lambda expression that returns a boolean value.
	 */
	BooleanPromise(final BooleanSupplier supplier){
		this.supplier = supplier;
		counter = 0;
	}
	
	/** 
	 * Compute the lambda expression if it hasn't already been computet.
	 * 
	 * @return true if intersection is in shadow, false otherwise.
	 */
	public boolean get(){
		 if(counter == 0){
			 isShadowed = supplier.getAsBoolean();
			 counter++;
		 }
		return isShadowed;
	}
	
	int getCounter(){
		return counter;
	}
}
