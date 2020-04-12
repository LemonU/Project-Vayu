package cas.vayu.disasterarea.builder;

import java.util.ArrayList;

import cas.vayu.fileio.DisasterPoint;
import cas.vayu.fileio.WeatherTypeEnum;

/**
 * Module representing a disaster area, which contains a set of DisasterPoints,
 * a convexHull which covers all the DisasterPoints it contains, and a weather type
 * that is shared by all the points.
 * @author Oussama Saoudi
 *
 */
public class DisasterArea {
	private ArrayList<DisasterPoint> convexHull;
	private ArrayList<DisasterPoint> allNodes;
	private WeatherTypeEnum disasterType;
	
	/**
	 * Constructor for DisasterArea which takes in the area's convex hull,
	 * all the DisasterPoints contained within the convex hull, and the shared
	 * weather type of all the nodes
	 * @param convexHull Convex hull of the DisasterPOints in the disaster area
	 * @param allNodes all the DisasterPoints contained in the DisasterArea
	 * @param disasterType The weather type common to all the input DisasterPoints
	 */
	public DisasterArea(ArrayList<DisasterPoint> convexHull,ArrayList<DisasterPoint> allNodes, WeatherTypeEnum disasterType) {
		this.convexHull = convexHull;
		this.allNodes = allNodes;
		this.disasterType = disasterType;
	}
	/**
	 * Getter for the WeatherTypeEnum of the DisasterArea
	 * @return the WeatherTypeEnum of the DisasterPoints contained
	 * in the DisasterArea
	 */
	public WeatherTypeEnum getType() {
		return disasterType;
	}
	/**
	 * Gets the convex hull of the DisasterPoints contained in the
	 * DisasterArea
	 * @return ArrayList of DisasterPoints representing the Convex Hull
	 */
	public ArrayList<DisasterPoint> getHull(){
		return convexHull;
	}
	/**
	 * Gets all the nodes contained in the DisasterArea
	 * @return the ArrayList of DisasterPoints in the DisasterArea
	 */
	public ArrayList<DisasterPoint> getAllNodes(){
		return allNodes;
	}
}
