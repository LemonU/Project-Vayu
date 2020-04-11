package cas.vayu.fileio;
/**
 * 
 * @author Christina Korsman
 *
 */
public class DisasterPoint {

    private double latitude;
    private double  longitude;
    private WeatherTypeEnum disaster_type;
    private int year;
    private int casulaties;
    private int property_damage;
    private int ID;
    
    
    /**
     * 
     * @param id the value to identity the node by 
     */
    public DisasterPoint(int id){
    	
    	this.ID = id;
    	
    	this.year =0;
        this.latitude = 0;
        this.longitude = 0;
        
        this.casulaties = 0;
        this.property_damage =0;
    }
    public DisasterPoint(int id, double lat, double lon){
    	
    	this.ID = id;
    	
    	this.year =0;
        this.latitude = lat;
        this.longitude = lon;
        
        this.casulaties = 0;
        this.property_damage =0;
    }

    /**
     * 
     * @param setter The value for latitude to be set to 
     */
    public void  setLat(double setter) {
    	this.latitude = setter;
    }
    
    /**
    * 
    * @param setter The value for longitude to be set to 
    */
    public void  setLon(double setter) {
    	this.longitude = setter;
    }
    
    /**
     * 
     * @param setter The value for disaster type to be set to 
     */
    public void  settype(WeatherTypeEnum setter) {
    	this.disaster_type = setter;
    }
    
    /**
     * 
     * @param setter The value for casualties type to be set to 
     */
    public void  setCas(int setter) {
    	this.casulaties = setter;
    }
    
    /**
     * 
     * @param setter The value for year type to be set to 
     */
    public void setYear(int setter) {
    	this.year = setter;
    }
    
    
    /**
     * 
     * @param setter The value for property damage to be set to 
     */
    public void  setProDam(int setter) {
    	this.property_damage = setter;
    }
    
    /**
     * 
     * @return The ID of this node
     */
    public int getID() {
    	return this.ID;
    }
    
    /**
     * 
     * @return The latitude of this node
     */
    public double getLat(){
        return latitude;
    }
    
    /**
     * 
     * @return The longitude of this node
     */
    public double getLon(){
        return longitude;
    }
    
    /**
     * 
     * @return The Weather type of this node
     */
    public WeatherTypeEnum getweatherType(){
        return disaster_type;
    }

    /**
     * 
     * @return The casualties of this node
     */
    public int getCasulaties(){
        return casulaties;
    }
    
    /**
     * 
     * @return The property damage of this node
     */
    public int getproperty_damage(){
        return property_damage;
    }
    
    /**
     * 
     * @return The year of this node
     */
    public int getYear() {
    	return year;
    }
    
    /**
     * @return The Hashcode of this node
     */
    public int hashCode() {
    	return ID;
    }
    
    /**
     * 
     * @param other The other node to be compared
     * @return If this node and the other node are equal
     */
    public boolean equals(DisasterPoint other) {
    	return this.ID == other.ID ;	
    	
    }
    
    /**
     * @return String of all contents of this node
     */
    public String toString () {
    	return ("Id " + ID + " Lon : " +  longitude + "Lat : " + latitude );
    }
}

