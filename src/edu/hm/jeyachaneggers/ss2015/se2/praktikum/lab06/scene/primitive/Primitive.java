package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene.primitive;

import java.util.List;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;

/**
 * A primitive must provide a method (intersections) in order to
 * return all intersections that may occur between a ray and a primitive.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public interface Primitive {
  
	/**
	 * calculates intersections between a ray and a primitive.
	 * 
	 * @param ray
	 *            A ray object that may hit a primitive.
	 * @return a List of Intersection Objects. 
	 * @see <a href=
	 *         "http://www.siggraph.org/education/materials/HyperGraph/raytrace/rtinter1.htm"
	 *         >http://www.siggraph.org/education/materials/HyperGraph/raytrace/
	 *         rtinter1.htm</a>
	 * @see <a
	 *      href="http://en.wikipedia.org/wiki/Ray-sphere_intersection">http://en.wikipedia.org/wiki/Ray-sphere_intersection</a>
	 */
  List<Intersection> intersections(final Ray ray);
  
  /**
   * Calculates the normal vector of a primitive at a given point.
   * 
   * @param point Point on the primitives surface the normal shall be computet.
   * @return Normal vector at given point.
   */
  Vector getNormal(final Point point);
  
  /**
   * Gets the surface properties of a primitive.
   * 
   * @return the surface property (enum).
   */
  Surface getSurface();

}
