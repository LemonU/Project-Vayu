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
	private Stack<DisasterPoint> pointStack;
	public ConvexHullBuilder() {
		
	}
	/**
	 * Returns the second to the top item in the pointStack. The
	 * item has a type DisasterPoint.
	 * @return second to top item in Stack pointStack.
	 */
	private DisasterPoint nextToTop() {
		DisasterPoint temp = pointStack.pop();
		DisasterPoint output = pointStack.top();
		pointStack.push(temp);
		return output; 
	}
	
	/**
	 * Exchanges Points in the two array positions array[p1] and
	 * array[p2]
	 * @param disasterPoints Array to perform swap of index positions p1 and p2
	 * @param point Index of first element to swap with
	 * @param point2 Index of the second element to swap with
	 * @return
	 */
	private void exch(ArrayList<DisasterPoint> disasterPoints, int p1, int p2) {
		DisasterPoint temp = disasterPoints.get(p1);
		disasterPoints.set(p1, disasterPoints.get(p2));
		disasterPoints.set(p2, temp);
	}
	
	/**
	 * Returns the distance squared of the two Points p1 and p2 by calculating the
	 * hypotenuse squared. 
	 * @param p1 First DisasterPoint to calculate distance
	 * @param p2 Second DisasterPoint to calculate distance
	 * @return the distance squared between p1 and p2, of type Double
	 */
	private double distanceSq(DisasterPoint p1,DisasterPoint p2) {
		return (p1.x() - p2.x())*(p1.x() - p2.x()) + (p1.y() - p2.y())*(p1.y() - p2.y());
	}
	
	/**
	 * Checks if points p1, p2, and p3 have a clockwise, collinear, or counterclockwise
	 * orientation. If they are in a counter clock-wise orientation, returns 2, clock-wise
	 * returns 1 and collinear returns 0.
	 * @param p1 First DisasterPoint in orientation
	 * @param p2 Second DisasterPoint in orientation
	 * @param p3 Third DisasterPoint in orientation
	 * @return Integer representing orientation of the Points.
	 */
	private int orientation(DisasterPoint p1, DisasterPoint p2, DisasterPoint p3) {
		double val = (p2.y() - p1.y())*(p3.x() - p2.x()) -
					(p2.x() - p1.x()) * (p3.y() - p2.y());
		if(val == 0) return 0;
		return (val > 0) ? 1 : 2;
	}
	
	/**
	 * Comparator for comparing points by angle to a given anchor DisasterPoint,
	 * used in performing Graham Scacn algorithm.
	 * @author OussamaSaoudi
	 *
	 */
	private class PointComparator implements Comparator<DisasterPoint>{

		private DisasterPoint anchor;
		private PointComparator(DisasterPoint anchor) {
			this.anchor = anchor;
		}
		@Override
		public int compare(DisasterPoint p1, DisasterPoint p2) {
			int ori = orientation(anchor,p1,p2);
			
			// if collinear, return -1 if p2 farther than p1, p1 otherwise
			if(ori == 0) {
				return (distanceSq(anchor,p2) >= distanceSq(anchor,p1) ? -1 : 1);
			}
			
			// return -1 if orientation is counter clock-wise, 1 if clock-wise
			return (ori == 2) ? -1 : 1;
		}
		}
	
	// TEMPORARY METHOD
	private void qsort(ArrayList<Object> list,Comparator k ) {}
	
	/**
	 * Computes the convex hull of the list of points provided
	 * @param disasterPoints List of points in the set to compute convex set
	 * @return Set of Points representing the convex hull of the points parameter
	 */
	private HashSet<DisasterPoint> convexHull(ArrayList<DisasterPoint> disasterPoints) {
		double ymin = disasterPoints.get(0).y(); 
		int min = 0;
		
		//Find lowest y, if there are multiple points with same y value, pick leftmost of them.
		for(int i =1; i < disasterPoints.size(); i++) {
			double y = disasterPoints.get(i).y();
			if(y < ymin || (ymin == y && disasterPoints.get(i).x() < disasterPoints.get(min).x())) {
				ymin = disasterPoints.get(i).y();
				min = i;
			}
		}
		
		
		// Place bottom-most point at the first position
		exch(disasterPoints,0,min);
		
		qsort(disasterPoints.subList(1, disasterPoints.size()-1),new PointComparator(disasterPoints.get(0)));
		
		ArrayList<DisasterPoint> aux = new ArrayList<>();
		aux.add(disasterPoints.get(0));
		
		// Recreates array without collinear points existing
		for(int i = 1; i < disasterPoints.size(); i++) {
			while(i < disasterPoints.size() -1 && 
					orientation(disasterPoints.get(0),
							disasterPoints.get(i),
							disasterPoints.get(i+1))
					== 0){
				i++;
			}
			aux.add(disasterPoints.get(i));
		}
		if (aux.size()< 3) {
			return null;
		}
		
		pointStack.push(aux.get(0));
		pointStack.push(aux.get(1));
		pointStack.push(aux.get(2));
		
		for(int i = 0; i < aux.size(); i++) {
			while(orientation(nextToTop(),pointStack.top(),aux.get(i)) != 2) {
				pointStack.pop();
			}
			pointStack.push(aux.get(i));	
		}
		
		HashSet<DisasterPoint> output = new HashSet<>();
		for(DisasterPoint e : pointStack) {
			output.add(e);
		}
		return output;
	}
}
