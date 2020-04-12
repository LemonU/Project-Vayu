package cas.vayu.disasterarea.builder;
import cas.vayu.fileio.DisasterPoint;

/**
 * KdTree is a binary tree representing points in 2d space with methods for inserting,
 * finding nearest point, and finding point within a given range of a point.
 * @author Oussama Saoudi
 *
 */
public class KdTree {
    private static final boolean VERT = true;
    private Node root;
	private static final double maxLat = 180;
	private static final double maxLon = 180;
	private static final double minLat = -180;
	private static final double minLon = -180;


	/**
	 * DisasterPoint object representing node in the kd binary tree with 
	 * representative point in 2d space, an orientation, showing
	 * if its dividing line is horizontal or vertical. DisasterPoint also has a
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
		 * Constructor for node taking all the information DisasterPoint stores
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
		 * Resets the DisasterPoint's point p and orientation values
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
		
		/**
		 * Constructor for RectA, sets minimum and maximum x and y values
		 * @param xmin Minimum x value
		 * @param xmax Maximum x value
		 * @param ymin Minimum y value
		 * @param ymax Maximum y value
		 */
		private RectA(double xmin, double xmax, double ymin,double ymax) {
			this.xmin = xmin;
			this.xmax = xmax;
			this.ymin = ymin;
			this.ymax = ymax;
		}
		/**
		 * Checks if given point p is in the RectA object, ie if it is within
		 * the bounds of being between xmin and xmax, and ymin and ymax (inclusive).
		 * @param p DisasterPoint to check if in the Rectangle contains the point p
		 * @return boolean to represent if the point is inside the RectA
		 */
		private boolean contains(DisasterPoint p) {
			return xmin <= p.getLat() && p.getLat() <= xmax && ymin <= p.getLon() && p.getLon() <= ymax; 
		}
		
		/**
		 * Returns the distance squared from the given point to the
		 * RectA object.
		 * @param p DisasterPoint to check distance to the rectangle
		 * @return Double type distance squared from point to rectangle
		 */
		private double distanceSquaredTo(DisasterPoint p) {
			if(contains(p)) return 0;
			if(p.getLat() < xmin) {
				if(p.getLon() < ymin) {
					return (p.getLon()-ymin)*(p.getLon()-ymin) + (p.getLat()-xmin)*(p.getLon()-xmin);
				} else if(p.getLon() > ymax) {
					return (p.getLon()-ymax)*(p.getLon()-ymax) + (p.getLat()-xmin)*(p.getLon()-xmin);
				} else{
					return xmin - p.getLat();
				}
			} else if(p.getLat() > xmax) {
				if(p.getLon() < ymin) {
					return (p.getLon()-ymin)*(p.getLon()-ymin) + (p.getLat()-xmax)*(p.getLon()-xmax);
				} else if(p.getLon() > ymax) {
					return (p.getLon()-ymax)*(p.getLon()-ymax) + (p.getLat()-xmax)*(p.getLon()-xmax);
				} else{
					return p.getLat() - xmax;
				}
			} else {
				if(p.getLon() < ymin) {
					return ymin - p.getLon();
				} else {
					return p.getLon() - ymax;
				}
			}
		}
		
		/**
		 * Method to check if this rectangle RectA intersects at any point,
		 * including right at the bounds, to the passed other RectA
		 * @param other RectA to check if intersecting with this RectA
		 * object
		 * @return Boolean representing whether this and the other RectA
		 * rectangles interscet
		 */
		private boolean intersects(RectA other) {
	        return this.xmax >= other.xmin() && this.ymax >= other.ymin()
	                && other.xmax() >= this.xmin && other.ymax() >= this.ymin;
		}
		/**
		 * Getter for minimum x value of the rectangle RectA
		 * @return minimum x value of rectangle
		 */
		private double xmin() {
			return xmin;
		}
		/**
		 * Getter for minimum y value of the rectangle RectA
		 * @return minimum y value of rectangle
		 */
		private double ymin() {
			return ymin; 
		}
		/**
		 * Getter for maximum y value of the rectangle RectA
		 * @return maximum y value of rectangle
		 */
		private double ymax() {
			return ymax;
		}
		/**
		 * Getter for maximum x value of the rectangle RectA
		 * @return maximum x value of rectangle
		 */
		private double xmax() {
			return xmax;
		}
	}
	/**
	 * Returns the direct distance squared between two points
	 * @param p1 Beginning of line to find distance squared of
	 * @param p2 End of line to find distance squared of
	 * @return distance squared of line from p1 to p2
	 */
	private double distanceSquaredTo(DisasterPoint p1, DisasterPoint p2) {
		return (p1.getLat() - p2.getLat())*(p1.getLat() - p2.getLat()) + (p1.getLon() - p2.getLon()) *(p1.getLon() - p2.getLon());
	}
	
	/**
	 * Inserts DisasterPoint p into the kd-tree
	 * @param p DisasterPoint in 2D space to insert
	 */
	public void insert(DisasterPoint p) {
		if(p == null) throw new IllegalArgumentException();
		this.root = insert(root,p,VERT,minLat,minLon,maxLat,maxLon);
	}
	/**
	 * Inserts the DisasterPoint p at the kdTree by analyzing the node and orientation
	 * to find placement of the point. xmin, ymin, xmax, and ymax represent the bounds
	 * of the area being checked by the kdtree for the position to place the point.
	 * When the new node is made with its associated point, it will occupy a RectA area
	 * with bounds xmin, ymin, xmax, and ymax.
	 * @param node Node on KdTree to check where point should be placed
	 * @param p Point to be placed into the kdtree
	 * @param orientation Orientation of the dividing line of the node, representing if
	 * the node divides the space it occupies with a vertical or horizontal line
	 * @param xmin Minimum x value of space occupied by the node
	 * @param ymin Minimum y value of space occupied by the node
	 * @param xmax Maximum x value of space occupied by the node
	 * @param ymax Maximum x value of space occupied by the node
	 * @return Reference to the node occupying the space
	 */
	private Node insert(Node node, DisasterPoint p, boolean orientation, double xmin, double ymin, double xmax, double ymax) {
		if(node == null) {
			return new Node(p,1,orientation,new RectA(xmin, xmax, ymin, ymax));
		}
		int cmp;
		
		//Vertical line, check if point resides on left or right
		if(orientation) {
			cmp = compareX(p,node.p);
			
			if(cmp >= 0) {
				node.right = insert(node.right,p,!orientation,node.getPoint().getLat(),ymin,xmax,ymax);
			} else{
				node.left = insert(node.left,p,!orientation,xmin,ymin,node.getPoint().getLat(),ymax);
			}
		} 
		// Horizontal line, check if point resides above or below
		else {
			cmp = compareY(p,node.p);
			
			if(cmp >= 0) {
				node.right = insert(node.right,p,!orientation,xmin,node.getPoint().getLon(),xmax,ymax);
			} else {
				node.left = insert(node.left,p,!orientation,xmin,ymin,xmax,node.getPoint().getLon());
			}
		}
		node.size = 1 + size(node.right)+ size(node.left); 
		return node;
	}
	
	/**
	 * Returns the size of the node's subtree if it is not null,
	 * else returns 0
	 * @param n size of the node's subtree
	 * @return the size of the node's subtree if it is not null,
	 * else returns 0
	 */
	private int size(Node n) {
		if (n == null) return 0;
		return n.size;
	}
	
	/**
	 * Compares the x value between two DisasterPoints p1 and p2.
	 * Returns 1 if p1 has greater x value, returns -1 if p2 has
	 * greater value, and 0 otherwise.
	 * @param p1 First point to compare
	 * @param p2 Second point to compare
	 * @return Comparison between the two points p1 and p2
	 */
	private int compareX(DisasterPoint p1, DisasterPoint p2) {
		if( p1.getLat() > p2.getLat()) return 1;
		else if(p2.getLat() > p1.getLat() ) return -1;
		else return 0;
	}
	/**
	 * Compares the y value between two DisasterPoints p1 and p2.
	 * Returns 1 if p1 has greater y value, returns -1 if p2 has
	 * greater value, and 0 otherwise.
	 * @param p1 First point to compare
	 * @param p2 Second point to compare
	 * @return Comparison between the two points p1 and p2
	 */
	private int compareY(DisasterPoint p1, DisasterPoint p2) {
		if( p1.getLon() > p2.getLon()) return 1;
		else if(p2.getLon() > p1.getLon() ) return -1;
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
		return nearestPoint(root,query,nearest);
	}
	/**
	 * Finds the nearest point in the kdTree to the query DisasterPoint.
	 * @param node Node occupying space being analyzed
	 * @param query Node to find nearest neighbor in the kdTree
	 * @param nearestPoint The nearest point found so far in the search
	 * @return returns the nearest point found so far in the kd tree.
	 */
	private DisasterPoint nearestPoint(Node node, DisasterPoint query, DisasterPoint nearestPoint) {
		if(nearestPoint == null) nearestPoint = node.getPoint();
		if(node == null) return nearestPoint;
		if(distanceSquaredTo(node.getPoint(),query) < distanceSquaredTo(nearestPoint,query))
				nearestPoint = node.getPoint();
		
		double distanceToLine = -1;
		int cmp;
		if(node.orientation) {
			cmp = compareX(query,node.getPoint());
		} else {
			cmp = compareY(query,node.getPoint());
		}
		
		if(cmp < 0) {
			nearestPoint = nearestPoint(node.left,query,nearestPoint);
		} else {
			nearestPoint = nearestPoint(node.right,query,nearestPoint);
		}
		
		if(cmp >= 0 && node.left != null ) {
			distanceToLine = node.left.rect.distanceSquaredTo(query); 
		} else if(node.right != null) {
			distanceToLine = node.right.rect.distanceSquaredTo(query);

		}
		
		if(distanceToLine != -1 && distanceToLine < distanceSquaredTo(nearestPoint,query)) {
			nearestPoint = nearestPoint(cmp < 0 ? node.right : node.left,query,nearestPoint);
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
	public Iterable<DisasterPoint> closePionts(DisasterPoint query, double rad){
		RectA area = new RectA(query.getLat()-rad,query.getLat()+rad,query.getLon()-rad,query.getLon()+rad);
		Stack<DisasterPoint> output = new Stack<>();
		range(root,area,output);
		return output;
	}

	/**
	 * Finds all DisasterPoints existing in the RectA rectangle passed to the
	 * method
	 * @param node Node occupying space being analyzed
	 * @param rect RectA rectangular area to find all points within, bounds 
	 * included
	 * @param stack Stack of DisasterPoints which are found to reside inside the
	 * rectangular area RectA
	 */
    private void range(Node node, RectA rect, Stack<DisasterPoint> stack) {
        if (node == null) return;

        if (node.getPoint().getLat() <= rect.xmax() && node.getPoint().getLat() >= rect.xmin() && node.getPoint().getLon() <= rect.ymax() && node.getPoint().getLon() >= rect.ymin()) {
            stack.push(node.getPoint());
        }
        if (node.right != null && rect.intersects(node.right.rect)) {
            range(node.right, rect, stack);
        }
        if (node.left != null && rect.intersects(node.left.rect)) {
            range(node.left, rect, stack);
        }
        return;
    }
    /**
     * Main method used for unit testing of the KdTree
     * @param args Arguments to be passed to the main method.
     */
	public static void main(String[] args) {
		KdTree tree = new KdTree();
		for(int i = 0; i < 10; i++) {
			DisasterPoint p = new DisasterPoint(i);
			p.setLat(i*1.0);
			p.setLon(i*1.0);
			tree.insert(p);
		}
		DisasterPoint a = new DisasterPoint(11);
		a.setLat(0.499999);
		a.setLon(0.50001);
		//System.out.println(tree.nearestPoint(a));
		
		KdTree tree2 = new KdTree();
		DisasterPoint p = new DisasterPoint(2);
		p.setLat(-1.0);
		p.setLon(-1.0);
		DisasterPoint p1 = new DisasterPoint(39);
		p1.setLat(1.0);
		p1.setLon(-1.0);
		DisasterPoint p2 = new DisasterPoint(4);
		p2.setLat(-1.0);
		p2.setLon(1.0);
		DisasterPoint p3 = new DisasterPoint(5);
		p3.setLat(1.0);
		p3.setLon(1.0);
		tree2.insert(p);
		tree2.insert(p3);
		tree2.insert(p1);
		tree2.insert(p2);

		DisasterPoint c = new DisasterPoint(2);
		c.setLat(0);
		c.setLon(0);
		//System.out.println(tree2.closePionts(c, 0.1));
		for(DisasterPoint e : tree2.closePionts(c, 12)) {
			System.out.println(e);
		}
		
		
	}
}
