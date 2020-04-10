package cas.vayu;

import java.util.Scanner;

public class CommandlineController {

    private static final String SORT_COMMAND = "sort";
    private static final String AREAS_COMMAND = "areas";
    private static final String HELP_COMMAND = "help";
    private static final String EXIT_COMMAND = "exit";

    private static Scanner scanner = null;

    private CommandlineController() {}

    public static void start() {
        scanner = new Scanner(System.in);
        System.out.println("Project Vayu command-line user interface: V 0.0.1");
        System.out.println("Type \"help\" to get the list of commands...\n");
        while(true) {
            System.out.print("> ");
            performNextCommand();
        }
    }

    private static void performNextCommand() {
        String args[] = scanner.nextLine().toLowerCase().split(" ");
        String cmd = args[0];

        if (cmd.equals(EXIT_COMMAND))
            exit(0);
        else if (cmd.equals(HELP_COMMAND))
            printHelp(args);
        else if (cmd.equals(SORT_COMMAND))
            sort(args);
        else if (cmd.equals(AREAS_COMMAND))
            areas(args);
        else
            printNoMatch();
    }

    private static void printHelp(String[] args) {
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
                System.out.println("Synopsis: sort <comparator> <outfile>");
                System.out.println("Avaiable comparators:");
                System.out.println("    ");
            }
            else if (args[1].equals(AREAS_COMMAND)) {

            }
            else {
                printNoMatch();
                return;
            }
        }
    }

    private static void printNoMatch() {
        System.out.println("\nUnkown command! Enter \"help\" for list of commands.\n");
    }

    private static void sort(String[] args) {
        String comparator;
        String outFile;
    }

    private static void areas(String[] args) {
        String disaster;
        String outFile;
    }

    private static void exit(int exitCode) {
        close();
        System.exit(exitCode);
    }

    private static void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        CommandlineController.start();
    }

}