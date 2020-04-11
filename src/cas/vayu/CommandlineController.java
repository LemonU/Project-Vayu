package cas.vayu;

import java.util.ArrayList;
import java.util.Scanner;

import cas.vayu.disasterarea.builder.DisasterArea;
import cas.vayu.disasterarea.builder.DisasterAreaBuilder;

public class CommandlineController {

    private static final String SORT_COMMAND = "sort";
    private static final String AREAS_COMMAND = "areas";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    private static final String BYCASUALTY_COMMAND = "bycasualty";
    private static final String BYPROXIMITY_COMMAND = "byproximity";
    private static final String BYDAMAGE_COMMAND = "bydamage";

    private static final double RAD = 50;

    private Parser parser;
    private DisasterAreaBuilder DABuilder;
    private Scanner inputScanner;
    private FileOutput outputter;

    public CommandlineController() {
        inputScanner = new Scanner(System.in);
        parser = new Parser();
        DABuilder = new DisasterAreaBuilder(Parser.lookup, parser.getData(), RAD);
        outputter = new FileOutput();
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
        String args[] = inputScanner.nextLine().toLowerCase().split(" ");
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
            }
            else if (args[1].equals(AREAS_COMMAND)) {
                printHelpAreas();
            }
            else {
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
    }

    private void printHelpAreasExt() {
        System.out.println("\nOutputs a list of disaster locations of a specific disaster type"
                            + "that are in close proximity with each other to the desired file");
        System.out.println("Synopsis: areas <disaster> <outfile>");
        System.out.println("Available disaster types:");
        System.out.println("  - Astronomical_Low_Tide");
        System.out.println("  - Avalanche");
        System.out.println("  - Blizzard");
        System.out.println("  - Coastal_Flood");
        System.out.println("  - Cold_Wind_Chill");
        System.out.println("  - Debris_Flow");
        System.out.println("  - Dense_Fog");
        System.out.println("  - Dense_Smoke");
        System.out.println("  - Drought");
        System.out.println("  - Dust_Devil");
        System.out.println("  - Dust_Storm");
        System.out.println("  - Excessive_Heat");
        System.out.println("  - Extreme_Cold_Wind_Chill");
        System.out.println("  - Flash_Flood");
        System.out.println("  - Frost_Freeze");
        System.out.println("  - Funnel_Cloud");
        System.out.println("  - Freezing_Fog");
        System.out.println("  - Heat");
        System.out.println("  - Heavy_Rain");
        System.out.println("  - Heavy_Snow");
        System.out.println("  - High_Surf");
        System.out.println("  - High_Wind");
        System.out.println("  - Hurricane_Typhoon");
        System.out.println("  - Ice_Storm");
        System.out.println("  - Lake_Effect_Snow");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
        System.out.println("  - ");
    }

    private void printNoMatch() {
        System.out.println("\nUnkown command! Enter \"help\" for list of commands.\n");
    }

    private void sort(String[] args) {
        if (args.length != 3) {
            printHelpSort();
            return;
        }

        String comparator = args[1];
        String outFile = args[2];
        ArrayList<DisasterPoint> result;

        if (comparator.equals(BYCASUALTY_COMMAND)) {
            result = null;
            outputter.writeData(outFile, result);
        } else if (comparator.equals(BYPROXIMITY_COMMAND)) {

        } else if (comparator.equals(BYDAMAGE_COMMAND)) {

        } else {
            
        }
    }

    private void areas(String[] args) {
        if (args.length != 3) {
            printHelpAreas();
            return;
        }

        String outFile = args[2];

        WeatherTypeEnum disasterType = enumOfString(args[1]);
        if (disasterType == null)
            return;

        ArrayList<DisasterArea> result = DABuilder.getAreas(disasterType);
    }

    private WeatherTypeEnum enumOfString(String rpr) {
        return null;
    }

    private void exit(int exitCode) {
        close();
        System.exit(exitCode);
    }

    private void close() {
        inputScanner.close();
    }

}