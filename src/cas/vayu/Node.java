package cas.vayu;

import java.util.Hashtable;

public class Node {
	
	
	
    private double latitude;
    private double  longitude;
    private WeatherTypeEnum disaster_type;
    private int year;
    private int casulaties;
    private int property_damage;
    private int ID;
    
    //when DisasterPoint is intailized , it intailly "null", will be identified by id
    public Node(int id){
    	this.ID = id;
    	
    	this.year =0;
        this.latitude = 0;
        this.longitude = 0;
        this.disaster_type = null;
        
        this.casulaties = 0;
        this.property_damage =0;
    }

    
    public void  setLat(double setter) {
    	this.latitude = setter;
    }
    
    public void  setLon(double setter) {
    	this.longitude = setter;
    }
    
    public void  settype(WeatherTypeEnum setter) {
    	this.disaster_type = setter;
    }
    
    public void  setCas(int setter) {
    	this.casulaties = setter;
    }
    public void setYear(int setter) {
    	this.year = setter;
    }
    
    
    
    public void  setProDam(int setter) {
    	this.property_damage = setter;
    }
    
    public int getID() {
    	return this.ID;
    }
    
    public double getLat(){
        return latitude;
    }

    public double getLon(){
        return longitude;
    }

    public WeatherTypeEnum getweatherType(){
        return disaster_type;
    }

    public int getCasulaties(){
        return casulaties;
    }

    public int getproperty_damage(){
        return property_damage;
    }
    
    public int getYear() {
    	return year;
    }
   
    public int hashCode() {
    	return ID;
    }
    public boolean equals(DisasterPoint other) {
    	return this.ID == other.ID ;	
    	
    }
    
    public String toString () {
    	return ("Id " + ID + " Lon : " +  longitude + "Lat : " + latitude );
    }
}

