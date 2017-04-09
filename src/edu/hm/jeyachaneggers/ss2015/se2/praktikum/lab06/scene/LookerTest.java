package edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.scene;

import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Point;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Ray;
import edu.hm.jeyachaneggers.ss2015.se2.praktikum.lab06.geometry.Vector;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Looker class.
 * 
 *  @author Vithya Jeyachandran,jeyachan@hm.edu
 *  @author Michael Eggers, eggers@hm.edu
 *  @version 2015-04-18
 */
public class LookerTest {
	
	// CHECKSTYLE:OFF MagicNumber
	// CHECKSTYLE:OFF MultipleStringLiteralsCheck
	// CHECKSTYLE:OFF MethodNameCheck
	
	/** Camera Pos for one Looker.*/
	private final Point cameraPos = new Point(0, 0, 5);

	/** ViewportCenterPos for one Looker. */
	private final Point viewportCenterPos = new Point(0, 0, 0);

	/** A Random Looker. */
	private final Looker looker = new Looker(cameraPos, viewportCenterPos, 2, 2);

	/** ViewVector. */
	private final Vector centerRayVector = cameraPos.vectorTo(viewportCenterPos);

	/** Ray Points to the CenterPoint. */
	private final Ray centerRay = new Ray(cameraPos, centerRayVector);

	/** First random Test looker .*/
	private final Looker looker1 = new Looker(new Point(9,4,5),new Point(3,7,1),5,2);

	/** Second random Test looker.*/
	private final Looker looker2 = new Looker(new Point(0,0,1),new Point(0,0,0),4,5); 

	/** Right vector for looker1. */
	private final Vector rightLooker1 = new Vector(4,0,-6);


	/** Up vector for looker1. */
	private final Vector upLooker1 = new Vector(18,52,12);

	/** Up vector for looker2.*/
	private final Vector upLooker2 = new Vector(0,1,0);



	/**
	 * Are the correct right-vectors are being computed?
	 */
	@Test
	public void testLookerRight(){
		final Vector expected1 = rightLooker1;
		final Vector actual1 = looker1.getRightVector();
		assertEquals(expected1,actual1);
	}

	/**
	 * Are the correct up-vectors are being computed?
	 */
	@Test
	public void testLookerUp(){
		final Vector expected1 = upLooker1;
		final Vector actual1 = looker1.getUpVector();
		final Vector expected2 = upLooker2;
		final Vector actual2 = looker2.getUpVector();
		assertEquals(expected1,actual1);
		assertEquals(expected2,actual2);
	}

	/**
	 * Is the correct ray being computed?
	 */
	@Test
	public void testGetPrimaryRayCenter(){
		final Vector expected = centerRay.getRayVector();
		final Vector actual = looker.getPrimaryRay(0, 0).getRayVector();
		assertEquals(expected, actual);
	}
	
	// CHECKSTYLE:ON MagicNumber
	// CHECKSTYLE:ON MultipleStringLiteralsCheck
	// CHECKSTYLE:ON MethodNameCheck

}
