package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive.Intersection;

/**
 * Camera and viewport (encapsulated in a Looker) and a primitve together make a scene. They are connected and
 * cannot be treated separately. Thus a scene must provide the following methods.
 *  
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public interface Scene {

  /**
   * Finds the closest intersection (intersection with shortest distance).
   * 
   * @param ray The ray that shall hit a primitive.
   * @return Closest intersection point if present.
   */
  Optional<Intersection> findIntersection(Ray ray);
  
  /**
   * A getter for a Looker.
   * 
   * @return Looker of this scene.
   */
  Looker getLooker();
  
  /**
   * A getter for a nice shiny light.
   * 
   * @return Point object that represents a point light.
   */
  Optional<Point> getLight();
  
	/**
	 * Factory method to fabricate nice scene objects.
	 * 
	 * @param args Concrete scene class we want to create with its corresponding params.
	 * @return A scene object, if everything went well ;)
	 * @throws ClassNotFoundException If there is no such concrete Scene class.
	 * @throws IOException if LoadedScene was not able to load a scenefile from disk.
	 */
	 static Scene make(final String... args) throws ClassNotFoundException, IOException{
		for(final String argument : args)
			assert argument != null:"should not be null";
		int arg = 0;
		switch(args[arg++]){
		case "ScriptedScene" : return new ScriptedScene(Arrays.copyOfRange(args, arg, args.length));
		
		case "LoadedScene" : return new LoadedScene(args[arg]);
		
		default : 
			throw new ClassNotFoundException();
		}
	}

}
