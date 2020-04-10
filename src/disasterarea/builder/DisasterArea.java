package disasterarea.builder;

import cas.vayu.Weather_Type_Enum;

public class DisasterArea {
	private ArrayList<DisasterPoint> convexHull;
	private ArrayList<DisasterPoint> allNodes;
	private Weather_Type_Enum disasterType;
	private double severity;
	
	public DisasterArea(ArrayList<DisasterPoint> convexHull,ArrayList<DisasterPoint> allNodes, Weather_Type_Enum disasterType) {
		this.convexHull = convexHull;
		this.allNodes = allNode;
		this.disasterType = disasterType;
	}
	
	public Weather_Type_Enum getType() {
		
	}
	public double getSeverity() {
		
	}
	public ArrayList<DisasterPoint> getHull(){
		
	}
	public ArrayList<DisasterPoint> getAllNodes(){
		
	}
}
