import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String citiesFilename = "";
        String routesFilename = "";
        String outputFilename = "";
        String origin = "";
        String destination = "";
        String preference = "";
        boolean biPreference = false;

        Scanner scanner = new Scanner(System.in);

        if (args.length > 0) {
            citiesFilename = args[0];
        } else {
            System.out.print("Enter filename containing cities: ");
            citiesFilename = scanner.nextLine();
        }

        if (args.length > 1) {
            routesFilename = args[1];
        } else {
            System.out.print("Enter filename containing routes: ");
            routesFilename = scanner.nextLine();
        }

        if (args.length > 2) {
            outputFilename = args[2];
        } else {
            System.out.print("Enter filename for output (.html): ");
            outputFilename = scanner.nextLine();
        }

        if (args.length > 3) {
            origin = args[3];
        } else {
            System.out.print("Origin: ");
            origin = scanner.nextLine();
        }

        if (args.length > 4) {
            destination = args[4];
        } else {
            System.out.print("Destination: ");
            destination = scanner.nextLine();
        }

        if (args.length > 5) {
            preference = args[5];
        } else {
            System.out.print("Enter a preference (fastest/cheapest): ");
            preference = scanner.nextLine();
        }

        scanner.close();

        if (preference.equalsIgnoreCase("cheapest")) {
            biPreference = true;
        } else if (preference.equalsIgnoreCase("fastest")) {
            biPreference = false;
        } else {
            System.out.println("Invalid entry ");
            return;
        }

        Graph graph = new Graph(citiesFilename, routesFilename);

        Location originCity = graph.getCity(origin);
        Location destinationCity = graph.getCity(destination);
                // System.out.print(originCity);
                // System.out.println("hi"+ destinationCity);

        if (originCity == null || destinationCity == null) {
            System.out.println("Invalid entry ");
            return;
        }

        graph.Dijkstras(origin, biPreference);

        Stack<Location> cityStack = graph.cityStacker(destination);
        Stack<Route> routeStack = graph.routeStacker(destination, biPreference);

    //     FileOperations.outputGenerator(outputFilename, cityStack, routeStack, biPreference);
    try {
    FileOperations.outputGenerator(outputFilename, cityStack, routeStack, biPreference);
} catch (IOException e) {
    e.printStackTrace(); // Handle the exception appropriately
}

    }
}
