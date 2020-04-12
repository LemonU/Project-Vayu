package cas.vayu.fileio;
/**
 * DisasterPoint representing a disaster that occurs at a specific year,
 * and position (in latitude and longitude). Contains information on property damage,
 * casualties, and its unique id
 * @author Christina Korsman
 *
 */
public class DisasterPoint {

    private double latitude;
    private double  longitude;
    private WeatherTypeEnum disasterType;
    private int year;
    private int casulaties;
    private int propertyDamage;
    private int id;
    
    
    /**
     * Constructs DisasterPoint with year, latitutde, longitude, casualties, and property damage
     * initialized to 0. Sets the id to the input id
     * @param id the value to identify the DisasterPoint by 
     */
    public DisasterPoint(int id){
    	
    	this.id = id;
    	
    	this.year =0;
        this.latitude = 0;
        this.longitude = 0;
        
        this.casulaties = 0;
        this.propertyDamage =0;
    }
    /**
     * Constructs DisasterPoint with given id, latitude lat and longitude lon.
     * Sets the year, casualties, and property damage to be 0
     * @param id the value to identify the DisasterPoint by 
     * @param lat Latitude value of the DisasterPoint's position
     * @param lon Longitude value of the DisasterPoint's position
     */
    public DisasterPoint(int id, double lat, double lon){
    	
    	this.id = id;
    	
    	this.year =0;
        this.latitude = lat;
        this.longitude = lon;
        
        this.casulaties = 0;
        this.propertyDamage =0;
    }

    /**
     * Sets the latitude to the passed lat value
     * @param lat The value for latitude to be set to 
     */
    public void  setLat(double lat) {
    	this.latitude = lat;
    }
    
    /**
    * Sets the longitude to the passed lon value
    * @param lon The value for longitude to be set to 
    */
    public void  setLon(double lon) {
    	this.longitude = lon;
    }
    
    /**
     * Sets the WeatherTypeEnum weather type of the DisasterPoint to the passed type
     * @param type The value for the DisasterPoint's WeatherTypeEnum type to be set to 
     */
    public void  settype(WeatherTypeEnum type) {
    	this.disasterType = type;
    }
    
    /**
     *  Sets the casualties of the DisasterPoint
     * @param cas The value for casualties to be set to 
     */
    public void  setCas(int cas) {
    	this.casulaties = cas;
    }
    
    /**
     * Sets the year the DisasterPoint occurs in
     * @param year The value for year to be set to 
     */
    public void setYear(int year) {
    	this.year = year;
    }
    
    
    /**
     * Sets the property damage of the DisasterPoint
     * @param pd The value for property damage to be set to 
     */
    public void  setPropertyDam(int pd) {
    	this.propertyDamage = pd;
    }
    
    /**
     * Getter for the unique id of the DisasterPoint to identify it by
     * @return The ID of this DisasterPoint
     */
    public int getId() {
    	return this.id;
    }
    
    /**
     * Getter for the latitude of the DisasterPoint's position
     * @return The latitude of this DisasterPoint
     */
    public double getLat(){
        return latitude;
    }
    
    /**
     * Getter for the longitude of the DisasterPoint's position
     * @return The longitude of this node
     */
    public double getLon(){
        return longitude;
    }
    
    /**
     * Getter for the WeaterTypeEnum of the DisasterPoint
     * @return The Weather type of this DisasterPoint
     */
    public WeatherTypeEnum getWeatherType(){
        return disasterType;
    }

    /**
     * Getter for the casualties of the DisasterPoint
     * @return The casualties of this DisasterPoint
     */
    public int getCasulaties(){
        return casulaties;
    }
    
    /**
     * Getter for the property damage of the DisasterPoint
     * @return The property damage of this DisasterPoint
     */
    public int getPropertyDamage(){
        return propertyDamage;
    }
    
    /**
     * Getter for the year the DisasterPoint occurs in
     * @return The year of this DisasterPoint
     */
    public int getYear() {
    	return year;
    }
    
    /**
     * Returns the hash code of the DisasterPoints
     * @return The hash code of this DisasterPoint
     */
    public int hashCode() {
    	return id;
    }
    
    /**
     * Returns true if the two DisasterPoints this and other have
     * the same id
     * @param other The other node to be compared
     * @return Boolean representing if this DisasterPoint and the other
     * DisasterPoint are equal
     */
    public boolean equals(DisasterPoint other) {
    	return this.id == other.id ;	
    	
    }
    
    /**
     * Returns string representation of the DisasterPoint in the
     * form "Id id Lon: longitude Lat: latitude"
     * @return String representing the DisasterPOint
     */
    public String toString () {
    	return ("Id " + id + " Lon : " +  longitude + "Lat : " + latitude );
    }
    
}

