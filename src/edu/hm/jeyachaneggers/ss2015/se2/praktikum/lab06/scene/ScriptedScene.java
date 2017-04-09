package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;


import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Plane;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Primitive;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Sphere;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Surface.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ScriptedScene contains at least one Looker, an optional Light and as many Primitives as needed.
 * It represents a self-contained scene.
 * 
 * @author Michi
 *
 */
class ScriptedScene implements Scene {

	/** Contains all primitives of the scene (Spheres, Planes, etc...). */
	private final List<Primitive> primitivesList = new ArrayList<>();

	/** All primitives will be seen through the same looker. */
	private final Looker looker;

	/** A light is optional. */
	private final Optional<Point> light;

	


	/**
	 * Creates a looker, an optional light and a bunch of primitives.
	 * 
	 * @param script Parameters to create the scene.
	 */
	ScriptedScene(final String... script) {
		Optional<Looker> intermediateLooker = Optional.empty();
		Optional<Point> intermediateLight = Optional.empty();
//		final List<String> scriptStrings = Arrays.asList(script);
//		scriptStrings.stream()
//		.map(ScriptedScene::sentenceFilter)
//		.filter(sentence -> sentence.size() > 0)
//		.map(List::toString)
//		.map(ScriptedScene::cleanSentence)
//		.map(ScriptedScene.SentenceIterator::make)
//		.forEach(iterator -> build(iterator));
		
		for(final String string : sentenceFilter(script)){
			final Iterator<String> stringIterator = ScriptedScene.SentenceIterator.make(cleanSentence(string));
			final String word = stringIterator.next();

			if(!intermediateLooker.isPresent())
				intermediateLooker = setLooker(word, stringIterator);
			
			if(!intermediateLight.isPresent())
				intermediateLight = setLight(word, stringIterator);

			setPrimitive(word, stringIterator);
			setSurface(word, stringIterator);
			
			if(stringIterator.hasNext())
				throw new IllegalArgumentException();
		}

		if(!intermediateLooker.isPresent())
			throw new IllegalArgumentException();
		this.looker = intermediateLooker.get();
		this.light = intermediateLight;
	}

	@Override public Optional<Point> getLight(){
		return light;
	}

	@Override public Looker getLooker(){
		return looker;
	}
	
//	/**
//	 * Builds a scene element if possible.
//	 * 
//	 * @param iterator The current Iterator that will provide the build-methods setLooker, setLight, setPrimitive and setSurface with needed parameters.
//	 */
//	private void build(final Iterator<String> iterator){
//		final String word = iterator.next();
//
//		if(!getIntermediateLooker().isPresent())
//			intermediateLooker = setLooker(word, iterator);
//
//		if(!getIntermediateLight().isPresent())
//			intermediateLight = setLight(word, iterator);
//
//		setPrimitive(word, iterator);
//		setSurface(word, iterator);
//
//		if(iterator.hasNext())
//			throw new IllegalArgumentException();
//	}

	/**
	 * Creates a looker.
	 * 
	 * @param word has to be "looker" to create a Looker object.
	 * @param stringIterator Iterator to get more words out of the sentence.
	 * @return a Looker object.
	 */
	private static Optional<Looker> setLooker(final String word, final Iterator<String> stringIterator){
		Optional<Looker> tmpLooker = Optional.empty();
		if("looker".equals(word)){
			tmpLooker = Optional.of(new Looker(
					new Point(Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next())),
					new Point(Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next())),
					Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next())));
		}
		return tmpLooker;
	}

	/**
	 * Sets light if any.
	 * 
	 * @param word has to be "light" if we want a light in the scene.
	 * @param stringIterator 
	 * @return a Light object.
	 */
	private static Optional<Point> setLight(final String word, final Iterator<String> stringIterator){
		final Optional<Point> lightPoint;
		if("light".equals(word)){
			lightPoint = Optional.of(new Point(Double.parseDouble(stringIterator.next()),
					Double.parseDouble(stringIterator.next()),
					Double.parseDouble(stringIterator.next())));
		}
		else
			lightPoint = Optional.empty();
		return lightPoint;
	}

	/**
	 * Sets the surface properties.
	 * 
	 *	@param word This word has to be either ambient, diffuse, specular,...
	 * @param stringIterator Iterator to get more words out of the sentence.
	 */
	private void setSurface(final String word, final Iterator<String> stringIterator){
		if(!primitivesList.isEmpty()){
			if("ambient".equals(word))
				primitivesList.get(primitivesList.size()-1).getSurface().set(Property.AmbientRatio, Double.parseDouble(stringIterator.next()));
			if("diffuse".equals(word))
				primitivesList.get(primitivesList.size()-1).getSurface().set(Property.DiffuseRatio, Double.parseDouble(stringIterator.next()));
			if("specular".equals(word)){
				primitivesList.get(primitivesList.size()-1).getSurface().set(Property.SpecularRatio, Double.parseDouble(stringIterator.next()));
				primitivesList.get(primitivesList.size()-1).getSurface().set(Property.SpecularExponent, Double.parseDouble(stringIterator.next()));
			}
			if("reflexion".equals(word)){
				primitivesList.get(primitivesList.size()-1).getSurface().set(Property.ReflexionRatio, Double.parseDouble(stringIterator.next()));
			}
			 
		}
	}

	/**
	 * Creates a primitive and adds it to the list.
	 * 
	 * @param word This word has to be one of the primitives names, if one of them shall be created.
	 * @param stringIterator Iterator to get more words out of the sentence.
	 */
	private void setPrimitive(final String word, final Iterator<String> stringIterator){
		if("sphere".equals(word))
			primitivesList.add(
					new Sphere(new Point(Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next())),
							Double.parseDouble(stringIterator.next())));
		if("plane".equals(word))
			primitivesList.add(
					new Plane(new Point(Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next())),
							new Vector(Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()),Double.parseDouble(stringIterator.next()))));
	}

	@Override
	public Optional<Intersection> findIntersection(final Ray ray) {
		final List<Intersection> intersections = new ArrayList<>();
		Optional<Intersection> resultIntersection = Optional.empty();
		
		primitivesList.stream()
		.map(primitive -> primitive.intersections(ray))
		.forEach(actualIntersections -> intersections.addAll(actualIntersections));
		
		Collections.sort(intersections);
		if(intersections.isEmpty())
			return resultIntersection;
		resultIntersection = Optional.of(intersections.get(0));
		return resultIntersection;
	}

	/**
	 * Filters sentences that begin with '#' or only consist of whitespaces.
	 * @param sentences Sentence to filter.
	 * @return Filtered sentence.
	 */
	private static List<String> sentenceFilter(final String... sentences){
		final List<String> filteredStrings;
		
		filteredStrings = Stream.of(sentences)
		.filter(next -> !(next.trim().isEmpty() || next.trim().charAt(0) == '#'))
		.collect(Collectors.toList());
		
		return filteredStrings;
	}

	/**
	 * Cleans a string from parentheses and too many whitespaces.
	 * 
	 * @param string String to be cleaned.
	 * @return cleaned String.
	 */
	private static String cleanSentence(final String string){
		String cleanString = string.replace("]","").replace("[","").replace(">","").replace("<","");
		
		while(cleanString.contains("  "))
			cleanString = cleanString.replace("  ", " ");
		
		cleanString = cleanString.trim();
		return cleanString;
	}

	/**
	 * Iterator that cycles through a Sentence and returns word after word.
	 * 
	 *  @author Vithya Jeyachandran,jeyachan@hm.edu
	 *  @author Michael Eggers, eggers@hm.edu
	 *  @version 2015-05-01
	 */
	private static final class SentenceIterator implements Iterator<String> {

		/** Counter for indexing words in a sentence. */
		private int counter;

		/** Contains words of the sentence. */
		private final List<String> words;

		/**
		 * Initializes the counter variable. 
		 * 
		 * @param string String to Iterate over.
		 */
		private SentenceIterator(final String string){
			counter = 0;
			words = stringToArrayList(string);
		}

		/**
		 * Factory method.
		 * 
		 * @param string String to Iterator over.
		 * @return new Iterator<String>
		 */
		public static Iterator<String> make(final String string){
			return new SentenceIterator(string);
		}


		/**
		 * Chops a string along its spaces and puts each word into a List.
		 * 
		 * @param string Strong to convert.
		 * @return List with words of the string (sentence).
		 */
		private List<String> stringToArrayList(final String string){
			final char[] sentenceChars = string.toCharArray();
			final List<String> wordList = new ArrayList<>();
			
			int start = 0;
			for(int index = 0; index < sentenceChars.length; index++){
				if(sentenceChars[index] == ' '){
					final String nextWord = string.substring(start, index);
					wordList.add(nextWord);
					start = index+1;
				}
				else if(index == sentenceChars.length-1){
					final String nextWord = string.substring(start, index+1);
					wordList.add(nextWord);
				}
			}

			return wordList;
		}

		@Override public boolean hasNext(){
			return counter < words.size();
		}

		@Override public String next() throws NoSuchElementException {
			if(hasNext())
				return words.get(counter++);
			throw new NoSuchElementException();
		}
	}	

}

