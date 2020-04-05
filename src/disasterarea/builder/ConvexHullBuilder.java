package disasterarea.builder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
/**
 * @brief Builds convex hull from given list of points and returns
 * a set of Integers containing id's for the hull of the given points.
 * @author OussamaSaoudi
 *
 */
public class ConvexHullBuilder {
	private Stack<Point> pointStack;
	public ConvexHullBuilder() {
		
	}
	/**
	 * Returns the second to the top item in the pointStack. The
	 * item has a type Point.
	 * @return second to top item in Stack pointStack.
	 */
	private Point nextToTop() {}
	
	/**
	 * Exchanges Points in the two array positions array[p1] and
	 * array[p2]
	 * @param array Array to perform swap of index positions p1 and p2
	 * @param p1 Index of first element to swap with
	 * @param p2 Index of the second element to swap with
	 * @return
	 */
	private void exch(Point[] array, int p1, int p2) {}
	
	/**
	 * Returns the distance squared of the two Points p1 and p2 by calculating the
	 * hypotenuse squared. 
	 * @param p1 First Point to calculate distance
	 * @param p2 Second Point to calculate distance
	 * @return the distance squared between p1 and p2
	 */
	private int distanceSq(Point p1,Point p2) {}
	
	/**
	 * Checks if points p1, p2, and p3 have a clockwise, collinear, or counterclockwise
	 * orientation. If they are in a counter clock-wise orientation, returns 2, clock-wise
	 * returns 1 and collinear returns 0.
	 * @param p1 First Point in orientation
	 * @param p2 Second Point in orientation
	 * @param p3 Third Point in orientation
	 * @return Integer representing orientation of the Points.
	 */
	private int orientation(Point p1, Point p2, Point p3) {}
	
	/**
	 * Comparator for comparing points by angle to a given anchor Point,
	 * used in performing Graham Scacn algorithm.
	 * @author OussamaSaoudi
	 *
	 */
	private class PointComparator implements Comparator<Point>{

		@Override
		public int compare(Point arg0, Point arg1) {
			// TODO Auto-generated method stub
			return 0;
		}}
	
	/**
	 * Computes the convex hull of the list of points provided
	 * @param points List of points in the set to compute convex set
	 * @return Set of Points representing the convex hull of the points parameter
	 */
	private HashSet<Point> convexHull(ArrayList<Point> points) {}
}
