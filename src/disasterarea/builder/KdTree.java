package disasterarea.builder;

import java.util.ArrayList;

public class KdTree {
    private static final boolean VERT = true;
    private Node root;
	private RectA fullMap;
	private static final double maxLat = 180;
	private static final double maxLon = 180;
	private static final double minLat = -180;
	private static final double minLon = -180;


	/**
	 * Node object representing node in the kd binary tree with 
	 * representative point in 2d space, an orientation, showing
	 * if its dividing line is horizontal or vertical. Node also has a
	 * left or right node, which could also represent up or down if its
	 * line is horizontal. RectA inside node represents the Rectangle it 
	 * sits in and divides.
	 * @author OussamaSaoudi
	 *
	 */
	private class Node{
		private Node left, right;
		private DisasterPoint p;
		private boolean orientation; //true == vertical, false == horizontal
		private int size;
		private RectA rect;
		/**
		 * Constructor for node taking all the information Node stores
		 * @param p DisasterPoint in 2D space the node represents
		 * @param size Size of the node's subtree
		 * @param orientation Orientation of the node's dividing line. True for
		 * 					vertical and false for horizontal
		 * @param rect Rectangle representing the space the point resides in and 
		 * the space the node divides.
		 */
		public Node(DisasterPoint p, int size, boolean orientation, RectA rect) {
			this.size = size;
			this.rect = rect;
			setPoint(p,orientation);
		}
		/**
		 * Resets the Node's point p and orientation values
		 * @param p DisasterPoint in 2D space the node represents
		 * @param orientation Orientation of the node's dividing line. True for
		 * 					vertical and false for horizontal
		 */
		public void setPoint(DisasterPoint p, boolean orientation) {
			this.p = p;
			this.orientation = orientation;
		}
		
		/**
		 * Getter for the point stored in the node.
		 * @return returns DisasterPoint stored in the node.
		 */
		private DisasterPoint getPoint() {
			return this.p;
		}
	}
	/**
	 * Rectangular Area data type which represents area in
	 * 2D space with minimum and maximum x and y values.
	 * @author OussamaSaoudi
	 *
	 */
	private class RectA{
		private double xmin,xmax,ymin,ymax;
		private Node root;
		
		/**
		 * Constructor for RectA, sets minimum and maximum x and y values
		 * @param xmin Minimum x value
		 * @param xmax Maximum x value
		 * @param ymin Minimum y value
		 * @param ymax Maximum y value
		 */
		private RectA(double xmin, double xmax, double ymin,double ymax) {
			
		}
		/**
		 * Checks if given point p is in the RectA object, ie if it is within
		 * the bounds of being between xmin and xmax, and ymin and ymax (inclusive).
		 * @param p DisasterPoint to check if in the Rectangle contains the point p
		 * @return boolean to represent if the point is inside the RectA
		 */
		private boolean contains(DisasterPoint p) {
			return xmin <= p.x() && p.x() <= xmax && ymin <= p.y() && p.y() <= ymax; 
		}
		
		/**
		 * Returns the distance squared from the given point to the
		 * RectA object.
		 * @param p DisasterPoint to check distance to the rectangle
		 * @return Double type distance squared from point to rectangle
		 */
		private double distanceSquaredTo(DisasterPoint p) {
			if(contains(p)) return 0;
			if(p.x() < xmin) {
				if(p.y() < ymin) {
					return (p.y()-ymin)*(p.y()-ymin) + (p.x()-xmin)*(p.y()-xmin);
				} else if(p.y() > ymax) {
					return (p.y()-ymax)*(p.y()-ymax) + (p.x()-xmin)*(p.y()-xmin);
				} else{
					return xmin - p.x();
				}
			} else if(p.x() > xmax) {
				if(p.y() < ymin) {
					return (p.y()-ymin)*(p.y()-ymin) + (p.x()-xmax)*(p.y()-xmax);
				} else if(p.y() > ymax) {
					return (p.y()-ymax)*(p.y()-ymax) + (p.x()-xmax)*(p.y()-xmax);
				} else{
					return p.x() - xmax;
				}
			} else {
				if(p.y() < ymin) {
					return ymin - p.y();
				} else {
					return p.y() - ymax;
				}
			}
		}
		
		private double xmin() {
			return xmin;
		}
		private double ymin() {
			return ymin; 
		}
		private double ymax() {
			return ymax;
		}
		private double xmax() {
			return xmax;
		}
	}
	private double distanceSquaredTo(DisasterPoint p1, DisasterPoint p2) {
		return (p1.x() - p2.x())*(p1.x() - p2.x()) + (p1.y() - p2.y()) *(p1.y() - p2.y());
	}
	
	/**
	 * Inserts DisasterPoint p into the kd-tree
	 * @param p DisasterPoint in 2D space to insert
	 */
	public void insert(DisasterPoint p) {
		if(p == null) throw new IllegalArgumentException();
		this.root = insert(root,p,VERT,minLat,minLon,maxLat,maxLon);
	}
	private Node insert(Node node, DisasterPoint p, boolean orientation, double xmin, double ymin, double xmax, double ymax) {
		if(node == null) {
			return new Node(p,1,orientation,new RectA(xmin, ymin, xmax, ymax));
		}
		int cmp;
		
		//Vertical line, check if point resides on left or right
		if(orientation) {
			cmp = compareX(p,node.p);
			
			if(cmp >= 0) {
				node.right = insert(node.right,p,!orientation,node.getPoint().x(),ymin,xmax,ymax);
			} else{
				node.left = insert(node.left,p,!orientation,xmin,ymin,node.getPoint().x(),ymax);
			}
		} 
		// Horizontal line, check if point resides above or below
		else {
			cmp = compareY(p,node.p);
			
			if(cmp >= 0) {
				node.right = insert(node.right,p,!orientation,xmin,node.getPoint().y(),xmax,ymax);
			} else {
				node.left = insert(node.left,p,!orientation,xmin,ymin,xmax,node.getPoint().y());
			}
		}
		node.size = 1 + size(node.right)+ size(node.left); 
		return node;
	}
	
	private int size(Node n) {
		if (n == null) return 0;
		return n.size;
	}
	
	private int compareX(DisasterPoint p1, DisasterPoint p2) {
		if( p2.x() > p1.x()) return 1;
		else if(p1.x() > p2.x() ) return -1;
		else return 0;
	}
	private int compareY(DisasterPoint p1, DisasterPoint p2) {
		if( p2.y() > p1.y()) return 1;
		else if(p1.y() > p2.y() ) return -1;
		else return 0;
	}
	
	/**
	 * Finds the Nearest point in the KDTree to the given query point,
	 * and return that point.
	 * @param query DisasterPoint that is closest to return point
	 * @return DisasterPoint in the KDTree that is nearest to the query point
	 */
	public DisasterPoint nearestPoint(DisasterPoint query) {
		if(query == null) throw new IllegalArgumentException();
		if (root == null) return null;
		DisasterPoint nearest = null;
		ArrayList<DisasterPoint> not = new ArrayList<>();
		return nearestPoint(root,query,nearest,not);
	}
	
	private DisasterPoint nearestPoint(Node node, DisasterPoint query, DisasterPoint nearestPoint, ArrayList<DisasterPoint> not) {
		if(nearestPoint == null && !not.contains(node.getPoint())) nearestPoint = node.getPoint();
		if(node == null) return nearestPoint;
		if(distanceSquaredTo(node.getPoint(),query) < distanceSquaredTo(nearestPoint,query)
				&& !not.contains(node.getPoint())) nearestPoint = node.getPoint();
		
		double distanceToLine = -1;
		int cmp;
		if(node.orientation) {
			cmp = compareX(query,node.getPoint());
		} else {
			cmp = compareY(query,node.getPoint());
		}
		
		if(cmp < 0) {
			nearestPoint = nearestPoint(node.left,query,nearestPoint, not);
		} else {
			nearestPoint = nearestPoint(node.right,query,nearestPoint, not);
		}
		
		if(cmp >= 0 && node.left != null ) {
			distanceToLine = node.left.rect.distanceSquaredTo(query); 
		} else if(node.right != null) {
			distanceToLine = node.right.rect.distanceSquaredTo(query);

		}
		
		if(distanceToLine != -1 && distanceToLine < distanceSquaredTo(nearestPoint,query)) {
			nearestPoint = nearestPoint(cmp < 0 ? node.right : node.left,query,nearestPoint, not);
		}
		return nearestPoint;
	}
	
	/**
	 * Finds all points that are within a certain radius rad from the
	 * given query point
	 * @param query DisasterPoint to search for closeby points, the center of the
	 * 				radius of search
	 * @param rad Radius from query point to search for close by points
	 * @return Iterable of all the points within the radius of query.
	 */
	@SuppressWarnings("unused")
	public Iterable<DisasterPoint> closePionts(DisasterPoint query, double rad){
		ArrayList<DisasterPoint> not = new ArrayList<>();
		while(true) {
			DisasterPoint nearest = null;
			DisasterPoint p = nearestPoint(root,query,nearest,not);
			if (nearest == null) break;
			if(distanceSquaredTo(p,query) <= rad * rad)not.add(p);
			else break;	
		}
		return not;
	}
	
}
