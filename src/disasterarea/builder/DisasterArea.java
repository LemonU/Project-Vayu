package disasterarea.builder;

import java.util.ArrayList;

import cas.vayu.DisasterPoint;
import cas.vayu.WeatherTypeEnum;

public class DisasterArea {
	private ArrayList<DisasterPoint> convexHull;
	private ArrayList<DisasterPoint> allNodes;
	private WeatherTypeEnum disasterType;
	private double severity;
	
	public DisasterArea(ArrayList<DisasterPoint> convexHull,ArrayList<DisasterPoint> allNodes, WeatherTypeEnum disasterType) {
		this.convexHull = convexHull;
		this.allNodes = allNodes;
		this.disasterType = disasterType;
	}
	
	public WeatherTypeEnum getType() {
		return disasterType;
	}
	public double getSeverity() {
		return severity;
	}
	public ArrayList<DisasterPoint> getHull(){
		return convexHull;
	}
	public ArrayList<DisasterPoint> getAllNodes(){
		return allNodes;
	}
}
