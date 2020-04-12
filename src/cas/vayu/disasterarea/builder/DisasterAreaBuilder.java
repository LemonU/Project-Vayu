package cas.vayu.disasterarea.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import cas.vayu.fileio.DisasterPoint;
import cas.vayu.fileio.WeatherTypeEnum;
import cas.vayu.graph.CCFinder;
import cas.vayu.graph.Graph;

/**
 * Builds the disaster areas using passed point list and map
 * and provides constant time disaster area search.
 * @author OussamaSaoudi
 *
 */
public class DisasterAreaBuilder {
	private Hashtable<WeatherTypeEnum, KdTree> kdtList;
	private ArrayList<DisasterPoint> pointList;
	private HashMap<WeatherTypeEnum,ArrayList<DisasterArea>> areaList;
	
	/**
	 * Builds all the disaster areas on the map. The radius rad is for search of nearest nodes
	 * when connecting close DisasterPoints. The minimum size of a disaster area can be specified
	 * using the method parameter thresh
	 * @param map Hashtable of the mapping of id to array position in the point List
	 * @param pointList Point list containing all the DisasterPoints
	 * @param rad radius of search for connecting close points
	 * @param thresh Minimum number of nodes for a disaster area to be added to the list of saved 
	 * DisasterAreas
	 */
	public DisasterAreaBuilder(Hashtable<Integer,Integer> map, ArrayList<DisasterPoint> pointList,double rad, int thresh) {
		this.pointList = pointList;
		kdtList = new Hashtable<>();
		for(DisasterPoint p : pointList) {
			if(p.getWeatherType() != null && !kdtList.containsKey(p.getWeatherType())) {
				kdtList.put(p.getWeatherType(),new KdTree());
			}
			if(p.getWeatherType() != null) {
				kdtList.get(p.getWeatherType()).insert(p);
			}
		}
		System.out.println("Kd-Trees initialized");
		Graph G = new Graph(pointList.size());
		for(DisasterPoint p: pointList) {
			if (p.getWeatherType() == null) continue;
			for(DisasterPoint e : kdtList.get(p.getWeatherType()).closePionts(p,rad)) {
				G.addEdge(map.get(e.getId()) , map.get(p.getId()));
			}
		}
		System.out.println("Graph initialized");

		CCFinder componentFinder = new CCFinder(G);
		System.out.println("Connected Components initialized");

		areaList = new HashMap<WeatherTypeEnum, ArrayList<DisasterArea>>();
		for(int i = 0; i < componentFinder.componentCount(); i++) {
			if(componentFinder.getComponentById(i).size() < 50) continue;
			ArrayList<DisasterPoint> p = convertToPoint(componentFinder.getComponentById(i));
			WeatherTypeEnum type = p.get(0).getWeatherType();
			ArrayList<DisasterPoint> convexHull = ConvexHullBuilder.convexHull(p);
			DisasterArea a;
			if (convexHull != null) {
				a = new DisasterArea(convexHull,p,type);
			} else {
				continue;
			}
			if(!areaList.containsKey(type)) {
				areaList.put(type, new ArrayList<DisasterArea>());
			}
			areaList.get(type).add(a);
		}
		System.out.println("Disaster Areas Initialized");
	}
	/**
	 * Converts an iterable of integers into a list of DisasterPoints,
	 * using the integers as indices in the arrays and returns the list.
	 * @param iterable The iterable to be converted to a DisasterPoint list
	 * @return List of DIsasterPoints created.
	 */
	private ArrayList<DisasterPoint> convertToPoint(Iterable<Integer> iterable){
		ArrayList<DisasterPoint> a = new ArrayList<>();
		for(Integer e : iterable) {
			a.add(this.pointList.get(e));
		}
		return a;
	}
	/**
	 * Returns list of all the DisasterAreas which have a the passed weather
	 * type type.
	 * @param type The weather type of all the DisasterAreas to be returned
	 * @return List of DisasterAreas that are have the weather type type
	 */
	public ArrayList<DisasterArea> getAreas(WeatherTypeEnum type){
		if(!areaList.containsKey(type)) {
			return null;
		}
		return areaList.get(type);
	}
}
