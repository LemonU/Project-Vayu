package cas.vayu.fileio;
/**
 * Enum representing a weather disaster type, with associated string representations
 * @author Christina Korsman
 *
 */
//listing of all given disaster types
public enum WeatherTypeEnum {
	AstronomicalLowTide ("Astronomical Low Tide"),
	Avalanche ("Avalanche"), 
	Blizzard("Blizzard"), 
	Coastal_Flood("Coastal Flood"), 
	Cold_Wind_Chill("Cold/Wind Chill"), 
	Debris_Flow("Debris Flow"), 
	Dense_Fog("Dense Fog"), 
    Dense_Smoke("Dense Smoke"), 
    Drought("Drought"), 
    Dust_Devil("Dust Devil"), 
    Dust_Storm("Dust Storm"), 
    Excessive_Heat("Excessive Heat"), 
    Extreme_Cold_Wind_Chill("Extreme Cold/Wind Chill"), 
    Flash_Flood("Flash Flood"), 
    Flood("Flood"),
    Frost_Freeze("Frost/Freeze"),
    Funnel_Cloud("Funnel Cloud"), 
    Freezing_Fog("Freezing Fog"), 
    Hail("Hail"), 
    Heat("Heat"), 
    Heavy_Rain("Heavy Rain"), 
    Heavy_Snow("Heavy Snow"), 
    High_Surf("High Surf"), 
    High_Wind("High Wind"), 
    Hurricane("Hurricane"),
    Hurricane_Typhoon("Marine Hurricane/Typhoon"),
    Ice_Storm("Ice Storm"), 
    Lake_Effect_Snow("Lake-Effect Snow"), 
    Lake_shore_Flood("Lakeshore Flood"),
    Lightning("Lightning"), 
    Marine_Hail("Marine Hail"), 
    Marine_High_Wind("Marine High Wind"), 
    Marine_Strong_Wind("Marine Strong Wind"), 
    Marine_Thunder_Wind("Marine Thunderstorm Wind"),
    Marine_Tropical_Storm("Marine Tropical Storm"),
    Marine_Tropical_Depression("Marine Tropical Depression"), 
    Rip_Current("Rip Current"), 
    Seiche("Seiche"), 
    Sleet("Sleet"), 
    Storm_Surge_Tide("Storm Surge/Tide"), 
    Strong_Wind("Strong Wind"), 
    Thunderstorm_Wind("Thunderstorm Wind"), 
    Tornado("Tornado"), 
    Tropical_Depression("Tropical Depression"), 
    Tropical_Storm("Tropical Storm"),
    Tsunami("Tsunami"), 
    Volcanic_Ash("Volcanic Ash"), 
    Waterspout("Waterspout"), 
    Wildfire("Wildfire"), 
    Winter_Storm("Winter Storm"), 
    Winter_Weather ("Winter Weather");
    
    
    private String text;

    /**
     * Constructs WeatherTypeEnum from associated text.
     * @param text name of the created WeatherTypeEnum
     */
	private WeatherTypeEnum(String text) {
        this.text = text;
    }
    
    /**
     * Returns the associated name of the WeatherTypeEnum
     * @return name of the WeatherTypeEnum
     */
    public String getText() {
        return this.text;
    }
    
    /**
     * Returns the WeatherTypeEnum associated with the given text if it exists,
     * else return null.
     * @param text Name of the WeatherTypeEnum
     * @return WeatherTypeEnum if there is one with associated text, and null
     * otherwise
     */
    public static WeatherTypeEnum fromString(String text) {
        for (WeatherTypeEnum type : WeatherTypeEnum.values()) {
            if (type.text.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
