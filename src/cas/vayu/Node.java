package cas.vayu;


public class Node {
    private double latitude;
    private double  longitude;
    private Weather_Type_Enum disaster_type;
    private int casulaties;
    private int property_damage;
    
    public Node(double lat, double lon, Weather_Type_Enum type, int casulaties, int property_damage){
        this.latitude = lat;
        this.longitude = lon;
        this.disaster_type = type;
        this.casulaties = property_damage;
    }


    public double getLat(){
        return latitude;
    }

    public double getLon(){
        return longitude;
    }

    public Weather_Type_Enum getweatherType(){
        return disaster_type;
    }

    public int getCasulaties(){
        return casulaties;
    }

    public int getproperty_damage(){
        return property_damage;
    }
}

