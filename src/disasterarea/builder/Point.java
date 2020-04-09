package disasterarea.builder;

/**
 * DisasterPoint data type which contains x and y positions which represent
 * latitude and longitude respectively. Each point corresponds to a 
 * disaster point, which is identified with the id field.
 * @author OussamaSaoudi
 *
 */
public class Point {
	private int id;
	private double lat;
	private double lon;
	
	/**
	 * Constructs DisasterPoint from the id, latitude, and longitude, which 
	 * corresponds to a DisasterPoint object
	 * @param id Id of the corresponding event
	 * @param lat latitude of the event
	 * @param lon longitude of the event
	 */
	public Point(int id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
	}
	
	/**
	 * Returns the x position, ie the latitude of the DisasterPoint
	 * @return Double latitude of the point
	 */
	public double x() {
		return this.lat;
	}
	/**
	 * Returns the y position, ie the longitude of the DisasterPoint
	 * @return Double longitude of the point
	 */
	public double y() {
		return this.lon;
	}
	/**
	 * Returns the id of the DisasterPoint object corresponding to the
	 * DisasterPoint
	 * @return Integer id of the point
	 */
	public int id() {
		return this.id;
	}
}
