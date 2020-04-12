package cas.vayu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import cas.vayu.disasterarea.builder.DisasterArea;
import cas.vayu.disasterarea.builder.DisasterAreaBuilder;
import cas.vayu.fileio.DisasterPoint;
import cas.vayu.fileio.FileOutput;
import cas.vayu.fileio.Parser;
import cas.vayu.fileio.WeatherTypeEnum;
import cas.vayu.sorting.ByCasualties;
import cas.vayu.sorting.ByDamage;
import cas.vayu.sorting.ByProximity;
import cas.vayu.sorting.Quicksort;

public class CommandlineController {

    private static final String SORT_COMMAND = "sort";
    private static final String AREAS_COMMAND = "areas";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    private static final String BYCASUALTY_COMMAND = "bycasualty";
    private static final String BYPROXIMITY_COMMAND = "byproximity";
    private static final String BYDAMAGE_COMMAND = "bydamage";

    private static final double RAD = 0.1;

    private Parser parser;
    private DisasterAreaBuilder DABuilder;
    private Scanner inputScanner;
    private FileOutput outputter;

    public CommandlineController() {
        inputScanner = new Scanner(System.in);
        outputter = new FileOutput();

        parser = new Parser();
        DABuilder = new DisasterAreaBuilder(parser.getTable(), parser.getData(), RAD,50);
    }

    public void start() {
        System.out.println("Project Vayu command-line user interface: V0.0.1");
        System.out.println("Type \"help\" to get the list of commands...\n");

        while(true) {
            System.out.print("> ");
            performNextCommand();
        }
    }

    private void performNextCommand() {
        String args[] = inputScanner.nextLine().toLowerCase().trim().split(" ");
        String cmd = args[0];

        if (cmd.equals(EXIT_COMMAND))
            exit(0);
        else if (cmd.equals(HELP_COMMAND))
            printHelp(args);
        else if (cmd.equals(SORT_COMMAND))
            sort(args);
        else if (cmd.equals(AREAS_COMMAND))
            areas(args);
        else if (args.length == 1 && cmd.equals(""))
            return;
        else
            printNoMatch();
    }

    private void printHelp(String[] args) {
        if (args.length == 1) {
            System.out.println("Available commands:");
            System.out.println("--------------------------------");
            System.out.println("sort  <comparator> <outfile>");
            System.out.println("areas <disaster>   <outfile>");
            System.out.println("help  [sort | areas]");
            System.out.println("exit");
        }
        else {
            if (args[1].equals(SORT_COMMAND)) {
                printHelpSort();
            } else if (args[1].equals(AREAS_COMMAND)) {
                printHelpAreas();
            } else {
                printNoMatch();
                return;
            }
        }
    }

    private void printHelpSort() {
        System.out.println("\nOutputs a sorted list of disaster locations to the desired file");
                System.out.println("Synopsis: sort <comparator> <outfile>");
                System.out.println("Available comparators:");
                System.out.println("  - byCasualty");
                System.out.println("  - byProximity");
                System.out.println("  - byDamage");
    }

    private void printHelpAreas() {
        System.out.println("\nOutputs a list of disaster locations of a specific disaster type"
                            + "that are in close proximity with each other to the desired file");
        System.out.println("Synopsis: areas <disaster> <outfile>");
        System.out.println("Available disaster types:");
        System.out.println("  - Astronomical_Low_Tide");
        System.out.println("  - Avalanche");
        System.out.println("  - Blizzard");
        System.out.println("  - Coastal_Flood");
        System.out.println("  - Cold_Wind_Chill");
        System.out.println("  - ...");
        System.out.println("To see more, enter \"help areas --more\"");
    }

    private void printHelpAreasExt() {
        
    }

    private void printNoMatch() {
        System.out.println("\nUnkown command! Enter \"help\" for list of commands.\n");
    }

    private void sort(String[] args) {
        if (args.length != 3) {
            printHelpSort();
            return;
        }

        Comparator<DisasterPoint> comparator = null;
        if (args[1].equalsIgnoreCase(BYDAMAGE_COMMAND))
            comparator = new ByDamage();
        else if (args[1].equalsIgnoreCase(BYCASUALTY_COMMAND))
            comparator = new ByCasualties();
        else if (args[1].equalsIgnoreCase(BYPROXIMITY_COMMAND))
            comparator = new ByProximity();
        else {
            printHelpSort();
            return;
        }

        ArrayList<DisasterPoint> result = Quicksort.sort(parser.getData(), comparator);
        System.out.println("Writing to file...");
        outputter.writeData(args[2], result);
    }

    private void areas(String[] args) {
        if (args.length != 3) {
            printHelpAreas();
            return;
        }

        WeatherTypeEnum disasterType = enumOfString(args[1]);
        if (disasterType == null) {
            printHelpAreas();
            return;
        }

        ArrayList<DisasterArea> result = DABuilder.getAreas(disasterType);

        System.out.println("Writing to file...");
        outputter.writeArea(args[2], result);
    }

    private WeatherTypeEnum enumOfString(String rpr) {
        WeatherTypeEnum matchedType = null;

        if (rpr.equalsIgnoreCase("Astronomical_Low_Tide")) {
            matchedType = WeatherTypeEnum.AstronomicalLowTide;
        }
        else if (rpr.equalsIgnoreCase("Avalanche")) {
            matchedType = WeatherTypeEnum.Avalanche;
        }
        else if (rpr.equalsIgnoreCase("Blizzard")) {
            matchedType = WeatherTypeEnum.Blizzard;
        }
        else if (rpr.equalsIgnoreCase("Coastal_Flood")) {
            matchedType = WeatherTypeEnum.Coastal_Flood;
        }
        else if (rpr.equalsIgnoreCase("Cold_Wind_Chill")) {
            matchedType = WeatherTypeEnum.Cold_Wind_Chill;
        }
        else if (rpr.equalsIgnoreCase("Debris_Flow")) {
            matchedType = WeatherTypeEnum.Debris_Flow;
        }
        else if (rpr.equalsIgnoreCase("Dense_Fog")) {
            matchedType = WeatherTypeEnum.Dense_Fog;
        }
        else if (rpr.equalsIgnoreCase("Dense_Smoke")) {
            matchedType = WeatherTypeEnum.Dense_Smoke;
        }
        else if (rpr.equalsIgnoreCase("Drought")) {
            matchedType = WeatherTypeEnum.Drought;
        }
        else if (rpr.equalsIgnoreCase("Dust_Devil")) {
            matchedType = WeatherTypeEnum.Dust_Devil;
        }
        else if (rpr.equalsIgnoreCase("Dust_Storm")) {
            matchedType = WeatherTypeEnum.Dust_Storm;
        }
        else if (rpr.equalsIgnoreCase("Excessive_Heat")) {
            matchedType = WeatherTypeEnum.Excessive_Heat;
        }
        else if (rpr.equalsIgnoreCase("Extreme_Cold_Wind_Chill")) {
            matchedType = WeatherTypeEnum.Extreme_Cold_Wind_Chill;
        }
        else if (rpr.equalsIgnoreCase("Flash_Flood")) {
            matchedType = WeatherTypeEnum.Flash_Flood;
        }
        else if (rpr.equalsIgnoreCase("Flood")) {
            matchedType = WeatherTypeEnum.Flood;
        }
        else if (rpr.equalsIgnoreCase("Frost_Freeze")) {
            matchedType = WeatherTypeEnum.Frost_Freeze;
        }
        else if (rpr.equalsIgnoreCase("Funnel_Cloud")) {
            matchedType = WeatherTypeEnum.Funnel_Cloud;
        }
        else if (rpr.equalsIgnoreCase("Freezing_Fog")) {
            matchedType = WeatherTypeEnum.Freezing_Fog;
        }
        else if (rpr.equalsIgnoreCase("Hail")) {
            matchedType = WeatherTypeEnum.Hail;
        }
        else if (rpr.equalsIgnoreCase("Heat")) {
            matchedType = WeatherTypeEnum.Heat;
        }
        else if (rpr.equalsIgnoreCase("Heavy_Rain")) {
            matchedType = WeatherTypeEnum.Heavy_Rain;
        }
        else if (rpr.equalsIgnoreCase("Heavy_Snow")) {
            matchedType = WeatherTypeEnum.Heavy_Snow;
        }
        else if (rpr.equalsIgnoreCase("High_Surf")) {
            matchedType = WeatherTypeEnum.High_Surf;
        }
        else if (rpr.equalsIgnoreCase("High_Wind")) {
            matchedType = WeatherTypeEnum.High_Wind;
        }
        else if (rpr.equalsIgnoreCase("Hurricane")) {
            matchedType = WeatherTypeEnum.Hurricane;
        }
        else if (rpr.equalsIgnoreCase("Hurricane_Typhoon")) {
            matchedType = WeatherTypeEnum.Hurricane_Typhoon;
        }
        else if (rpr.equalsIgnoreCase("Ice_Storm")) {
            matchedType = WeatherTypeEnum.Ice_Storm;
        }
        else if (rpr.equalsIgnoreCase("Lake_Effect_Snow")) {
            matchedType = WeatherTypeEnum.Lake_Effect_Snow;
        }
        else if (rpr.equalsIgnoreCase("Lake_shore_Flood")) {
            matchedType = WeatherTypeEnum.Lake_shore_Flood;
        }
        else if (rpr.equalsIgnoreCase("Lightning")) {
            matchedType = WeatherTypeEnum.Lightning;
        }
        else if (rpr.equalsIgnoreCase("Marine_Hail")) {
            matchedType = WeatherTypeEnum.Marine_Hail;
        }
        else if (rpr.equalsIgnoreCase("Marine_High_Wind")) {
            matchedType = WeatherTypeEnum.Marine_High_Wind;
        }
        else if (rpr.equalsIgnoreCase("Marine_Strong_Wind")) {
            matchedType = WeatherTypeEnum.Marine_Strong_Wind;
        }
        else if (rpr.equalsIgnoreCase("Marine_Thunder_Wind")) {
            matchedType = WeatherTypeEnum.Marine_Thunder_Wind;
        }
        else if (rpr.equalsIgnoreCase("Marine_Tropical_Storm")) {
            matchedType = WeatherTypeEnum.Marine_Tropical_Storm;
        }
        else if (rpr.equalsIgnoreCase("Marine_Tropical_Depression")) {
            matchedType = WeatherTypeEnum.Marine_Tropical_Depression;
        }
        else if (rpr.equalsIgnoreCase("Rip_Current")) {
            matchedType = WeatherTypeEnum.Rip_Current;
        }
        else if (rpr.equalsIgnoreCase("Seiche")) {
            matchedType = WeatherTypeEnum.Seiche;
        }
        else if (rpr.equalsIgnoreCase("Sleet")) {
            matchedType = WeatherTypeEnum.Sleet;
        }
        else if (rpr.equalsIgnoreCase("Storm_Surge_Tide")) {
            matchedType = WeatherTypeEnum.Storm_Surge_Tide;
        }
        else if (rpr.equalsIgnoreCase("Strong_Wind")) {
            matchedType = WeatherTypeEnum.Strong_Wind;
        }
        else if (rpr.equalsIgnoreCase("Thunderstorm_Wind")) {
            matchedType = WeatherTypeEnum.Thunderstorm_Wind;
        }
        else if (rpr.equalsIgnoreCase("Tornado")) {
            matchedType = WeatherTypeEnum.Tornado;
        }
        else if (rpr.equalsIgnoreCase("Tropical_Depression")) {
            matchedType = WeatherTypeEnum.Tropical_Depression;
        }
        else if (rpr.equalsIgnoreCase("Tropical_Storm")) {
            matchedType = WeatherTypeEnum.Tropical_Storm;
        }
        else if (rpr.equalsIgnoreCase("Tsunami")) {
            matchedType = WeatherTypeEnum.Tsunami;
        }
        else if (rpr.equalsIgnoreCase("Volcanic_Ash")) {
            matchedType = WeatherTypeEnum.Volcanic_Ash;
        }
        else if (rpr.equalsIgnoreCase("Waterspout")) {
            matchedType = WeatherTypeEnum.Waterspout;
        }
        else if (rpr.equalsIgnoreCase("Wildfire")) {
            matchedType = WeatherTypeEnum.Wildfire;
        }
        else if (rpr.equalsIgnoreCase("Winter_Storm")) {
            matchedType = WeatherTypeEnum.Winter_Storm;
        }
        else if (rpr.equalsIgnoreCase("Winter_Weather")) {
            matchedType = WeatherTypeEnum.Winter_Weather;
        }
        else {}

        return matchedType;
    }

    private void exit(int exitCode) {
        close();
        System.exit(exitCode);
    }

    private void close() {
        inputScanner.close();
    }

}