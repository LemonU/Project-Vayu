package cas.vayu;

import java.util.Hashtable;
/**
 * 
 * @author Christina Korsman
 *
 */
public class DisasterPoint {

    private double latitude;
    private double  longitude;
    private Weather_Type_Enum disaster_type;
    private int year;
    private int casulaties;
    private int property_damage;
    private int ID;
        
    /**
     * 
     * @param id the id to identify the node
     */
    public DisasterPoint(int id){
    	
    	this.ID = id;
    	
    	this.year =0;
        this.latitude = 0;
        this.longitude = 0;
        this.disaster_type = null;
        
        this.casulaties = 0;
        this.property_damage =0;
    }
    
    /**
     * 
     * @param setter value to set latitude
     */
    public void  setLat(double setter) {
    	this.latitude = setter;
    }
    
    /**
     * 
     * @param setter value to set longitude
     */
    public void  setLon(double setter) {
    	this.longitude = setter;
    }
    
    /**
     * 
     * @param setter value to set disaster_type
     */
    public void  settype(Weather_Type_Enum setter) {
    	this.disaster_type = setter;
    }
    
    /**
     * 
     * @param setter value to set casualties
     */
    public void  setCas(int setter) {
    	this.casulaties = setter;
    }
    
    /**
     * 
     * @param setter value to set year
     */
    public void setYear(int setter) {
    	this.year = setter;
    } 
    
    /**
     * 
     * @param setter value to set property damage
     */
    public void  setProDam(int setter) {
    	this.property_damage = setter;
    }
    
    /**
     * 
     * @return the node id
     */
    public int getID() {
    	return this.ID;
    }
    
    /**
     * 
     * @return the node latitude
     */
    public double getLat(){
        return latitude;
    }

    /**
     * 
     * @return the node longitude
     */
    public double getLon(){
        return longitude;
    }

    /**
     * 
     * @return the node weather type
     */
    public Weather_Type_Enum getweatherType(){
        return disaster_type;
    }
    
    /**
     * 
     * @return the node casualties
     */
    public int getCasulaties(){
        return casulaties;
    }

    /**
     * 
     * @return the node property damages
     */
    public int getproperty_damage(){
        return property_damage;
    }
    
    public int getYear() {
    	return year;
    }
    
    /**
     * 
     * @return the node hashcode id
     */
    public int hashCode() {
    	return ID;
    }
    
    /**
     * 
     * @return if a node is equal to each other
     */
    public boolean equals(DisasterPoint other) {
    	return this.ID == other.ID ;	
    	
    }
    
    /**
     * The node to string 
     */
    public String toString () {
    	return ("Id " + ID + " Lon : " +  longitude + "Lat : " + latitude );
    }
}

