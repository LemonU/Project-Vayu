package cas.vayu.disasterarea.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import cas.vayu.fileio.DisasterPoint;
import cas.vayu.fileio.WeatherTypeEnum;
import cas.vayu.graph.CCFinder;
import cas.vayu.graph.Graph;

/**
 * Builds the disaster areas based on 
 * @author OussamaSaoudi
 *
 */
public class DisasterAreaBuilder {
	private Hashtable<WeatherTypeEnum, KdTree> kdtList;
	private ArrayList<DisasterPoint> pointList;
	private HashMap<WeatherTypeEnum,ArrayList<DisasterArea>> areaList;
	
	public DisasterAreaBuilder(Hashtable<Integer,Integer> map, ArrayList<DisasterPoint> pointList,double rad, int thresh) {
		this.pointList = pointList;
		kdtList = new Hashtable<>();
		for(DisasterPoint p : pointList) {
			if(p.getweatherType() != null && !kdtList.containsKey(p.getweatherType())) {
				kdtList.put(p.getweatherType(),new KdTree());
			}
			if(p.getweatherType() != null) {
				kdtList.get(p.getweatherType()).insert(p);
			}
		}
		System.out.println("Kd-Trees initialized");
		Graph G = new Graph(pointList.size());
		for(DisasterPoint p: pointList) {
			if (p.getweatherType() == null) continue;
			for(DisasterPoint e : kdtList.get(p.getweatherType()).closePionts(p,rad)) {
				G.addEdge(map.get(e.getID()) , map.get(p.getID()));
			}
		}
		System.out.println("Graph initialized");

		CCFinder componentFinder = new CCFinder(G);
		System.out.println("Connected Components initialized");

		areaList = new HashMap<WeatherTypeEnum, ArrayList<DisasterArea>>();
		for(int i = 0; i < componentFinder.componentCount(); i++) {
			if(componentFinder.getComponentById(i).size() < 50) continue;
			ArrayList<DisasterPoint> p = convertToPoint(componentFinder.getComponentById(i));
			WeatherTypeEnum type = p.get(0).getweatherType();
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
	private ArrayList<DisasterPoint> convertToPoint(Iterable<Integer> iterable){
		ArrayList<DisasterPoint> a = new ArrayList<>();
		for(Integer e : iterable) {
			a.add(this.pointList.get(e));
		}
		return a;
	}
	
	public ArrayList<DisasterArea> getAreas(WeatherTypeEnum type){
		if(!areaList.containsKey(type)) {
			return null;
		}
		return areaList.get(type);
	}
}
