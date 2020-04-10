package disasterarea.builder;

import java.util.ArrayList;
import java.util.HashMap;

import cas.vayu.Weather_Type_Enum;
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
	private ArrayList<ArrayList<DisasterArea>> areaList;
	
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
				G.addEdge(map.get(e.getId()) , map.get(p.getId()));
			}
		}
		CCFinder componentFinder = new CCFinder(G);
		for(int i = 0; i < componentFinder.componentCount(); i++) {
			for(int e : componentFinder.getComponentById(i)) {
				ArrayList<DisasterPoint> p = convertToPoint(e);
				DisasterArea a = new DisasterArea(convexHull(p),p,p.get(0).getType());
				
			}
		}
	}
	private ArrayList<DisasterPoint> convertToPoint(ArrayList<Integer> list){
		ArrayList<DisasterPoint> a = new ArrayList<>();
		for(Integer e : list) {
			a.add(this.pointList.get(e));
		}
		return a;
	}
	
	public ArrayList<DisasterArea> getAreas(Weather_Type_Enum type){
				
	}
}
