package cas.vayu.disasterarea.builder;

import java.util.ArrayList;
import java.util.HashMap;

import cas.vayu.DisasterPoint;
import cas.vayu.WeatherTypeEnum;
import cas.vayu.graph.CCFinder;
import cas.vayu.graph.Graph;

/**
 * Builds the disaster areas based on 
 * @author OussamaSaoudi
 *
 */
public class DisasterAreaBuilder {
	private ArrayList<KdTree> kdtList;
	private HashMap<Integer,Integer> map;
	private ArrayList<DisasterPoint> pointList;
	private HashMap<WeatherTypeEnum,ArrayList<DisasterArea>> areaList;
	
	public DisasterAreaBuilder(HashMap<Integer,Integer> map, ArrayList<DisasterPoint> pointList,double rad) {
		this.pointList = pointList;
		for(DisasterPoint p : pointList) {
			if(kdtList.get(p.getweatherType().ordinal()) == null) {
				kdtList.set(p.getweatherType().ordinal(),new KdTree());
				kdtList.get(p.getweatherType().ordinal()).insert(p);
			} else {
				kdtList.get(p.getweatherType().ordinal()).insert(p);
			}
		}
		Graph G = new Graph(pointList.size());
		for(DisasterPoint p: pointList) {
			for(DisasterPoint e : kdtList.get(p.getweatherType().ordinal()).closePionts(p,rad)) {
				G.addEdge(map.get(e.getID()) , map.get(p.getID()));
			}
		}
		CCFinder componentFinder = new CCFinder(G);
		
		areaList = new HashMap<WeatherTypeEnum, ArrayList<DisasterArea>>();
		for(int i = 0; i < componentFinder.componentCount(); i++) {
			ArrayList<DisasterPoint> p = convertToPoint(componentFinder.getComponentById(i));
			WeatherTypeEnum type = p.get(0).getweatherType();
			DisasterArea a = new DisasterArea(ConvexHullBuilder.convexHull(p),p,type);
			if(!areaList.containsKey(type)) {
				areaList.put(type, new ArrayList<DisasterArea>());
			}
			areaList.get(type).add(a);
		}
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
