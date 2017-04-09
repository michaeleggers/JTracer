package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;

/**
 * a Raytracer always needs both camera and viewport which are scene independent. 
 * Looker encapsulates data defining them (camera, viewport).
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */

public class Looker {

  /** Camera location. */
  private final Point cameraPosition;
  
  /** Center of viewport. */
  private final Point viewportCenter;
  
  /** Viewports width. */
  private final double viewportWidth;
  
  /** Viewports height. */
  private final double viewportHeight;
  
  /** Vector of viewports grid facing upwards from cameras POV. */
  private final Vector upVector;
  
  /** Vector of viewports grid facing to the right from cameras POV and is parallel to x-z plane. */
  private final Vector rightVector;
  
  /**
   * Generates a Looker. 
   * 
   * @param cameraPosition Camera position.
   * @param viewportCenter Center of viewport.
   * @param viewportWidth viewports width.
   * @param viewportHeight viewports height.
   * @throws IllegalArgumentException if viewports dimensions are 0 or negative.
   */
  public Looker(final Point cameraPosition,final Point viewportCenter,final double viewportWidth,final double viewportHeight) throws IllegalArgumentException {
	  assert cameraPosition != null: "shuld not be null";
	  assert viewportCenter != null: "shuld not be null";
    if(viewportWidth <= 0 || viewportHeight <= 0)
      throw new IllegalArgumentException();
    this.cameraPosition = cameraPosition;
    this.viewportCenter = viewportCenter;
    this.viewportWidth = viewportWidth;
    this.viewportHeight = viewportHeight;
    final Vector viewVector = cameraPosition.vectorTo(viewportCenter);
	if(viewVector.isParallel(new Vector(0, 1, 0)))
		throw new IllegalArgumentException();
    rightVector = viewVector.perpendicularXZ().normalize().scalarMult(viewportWidth/2);
    upVector = rightVector.crossProduct(viewVector).normalize().scalarMult(viewportHeight/2);
  }
  
  
  
  public Point getCameraPosition() {
    return cameraPosition;
  }

  public Point getViewportCenter() {
    return viewportCenter;
  }

  public double getViewportWidth() {
    return viewportWidth;
  }

  public double getViewportHeight() {
    return viewportHeight;
  }

  public Vector getUpVector() {
    return upVector;
  }

  public Vector getRightVector() {
    return rightVector;
  }


/**
 * Computes the ray depening on relative h (horizontal) and v (vertical) values. 
 * 
 * @param relativeH horizontal shift relative to center of the viewport. -1 <= h <= 1.
 * @param relativeV vertical shift relative to center of the viewport. -1 <= v <= 1.
 * @return a ray starting at the cameras position heading to the relative viewport coordinate.
 * @throws IllegalArgumentException if v or h aren't within their defined range from -1 to 1.
 */
  public Ray getPrimaryRay(final double relativeH,final double relativeV) throws IllegalArgumentException {
    if(relativeH < -1 || relativeH > 1 || relativeV < -1 || relativeV > 1)
      throw new IllegalArgumentException();
    final Vector hShift = getRightVector().scalarMult(relativeH);
    final Vector vShift = getUpVector().scalarMult(relativeV);
    final Vector shiftVector = hShift.add(vShift);
    final Point rayPoint = getCameraPosition();
    final Vector rayVector = rayPoint.vectorTo(getViewportCenter()).add(shiftVector);
    return new Ray(rayPoint, rayVector);
  }
  
  @Override
  public String toString(){
	  
	  return String.format("camera position: %s%n"
	  					 + "viewport center: %s%n"
	  					 + "viewport width: %f%n"
	  					 + "viewport height: %f%n"
	  					 + "up vector: %s%n"
	  					 + "right vector: %s%n", getCameraPosition(), getViewportCenter(), 
	  					 						 getViewportWidth(), getViewportHeight(),
	  					 						 getUpVector(), getRightVector());
  }
  
}
