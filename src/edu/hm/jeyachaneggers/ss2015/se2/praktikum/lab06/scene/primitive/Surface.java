package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import java.util.EnumMap;

/**
 * Surfaces contain data about a primitives property.
 * 
 * @author Michi and Vithya.
 *
 */
public class Surface {

	/** Maximum value for speculars exponent. */
	private static final int SPECULARMAXEXP = 1000;
	
	/** Stores the surface property. */
	private final EnumMap<Property, Double> lazybonesMap = new EnumMap<>(Property.class);
	
	/**
	 * Returns surface specific property value.
	 * 
	 * @param property The property we want information about.
	 * @return The properties value.
	 */
	public double get(final Property property){
		double value = property.getValue();
		if(lazybonesMap.containsKey(property))
			value = lazybonesMap.get(property);
		return value;
	}
	
	/**
	 * Sets a specific property to a new value.
	 * 
	 * @param property Property we want to set to a new value.
	 * @param newValue The value to set.
	 */
	public void set(final Property property,final double newValue){
		if(lazybonesMap.containsKey(property))
			throw new IllegalStateException();
		if(newValue < property.getLowerBoundary() || newValue > property.getUpperBoundary())
			throw new IllegalArgumentException();
		lazybonesMap.put(property, newValue);
	}
	
	/**
	 * Property enum types define a specific property of a surface. We can set their values once
	 * to a new one within their defined range.
	 * 
	 * @author Michi
	 *
	 */
	public static enum Property {
		
		/**
		 * Surface properties.
		 */
		AmbientRatio(0, 1, 0.05), DiffuseRatio(0, 1, 0.95), SpecularRatio(0, 1, 0.0), SpecularExponent(0, SPECULARMAXEXP, 30), ReflexionRatio(0, 1, 0);
		
		/** Minimum value of this property. */
		private final int lowerBoundary;
		
		/** Maximum value of this property. */
		private final int upperBoundary;
		
		/** Default setting for a property. */
		private final double value;
		
		/**
		 * Ctor initializes the minimum and maximum values for each property.
		 * 
		 * @param lowerBoundary Lower boundary for a property value.
		 * @param upperBoundary Upper boundary for a property value. 
		 * @param defaultValue The default value every property gets initialized with.
		 */
		private Property(final int lowerBoundary, final int upperBoundary, final double defaultValue){
			this.lowerBoundary = lowerBoundary;
			this.upperBoundary = upperBoundary;
			this.value = defaultValue;
		}
	
		private int getLowerBoundary(){
			return lowerBoundary;
		}
		
		private int getUpperBoundary(){
			return upperBoundary;
		}
		
		private double getValue(){
			return value;
		}
	}
}