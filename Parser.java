import java.io.*;
import java.util.*;

public class Parser {

    public static List<Location> locationParser(String filename, List<Route> routes) {
        List<Location> cities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String country = parts[0].trim();
                    String city = parts[1].trim();
                    double latitude = Double.parseDouble(parts[2].trim());
                    double longitude = Double.parseDouble(parts[3].trim());

                    Location node = new Location(country, city, latitude, longitude);

                    for (Route route : routes) {
                        if (route.getOriginS().equals(node.getCapital())) {
                            route.setOrigin(node);
                            node.getRoutes().add(route);
                        } else if (route.getDestinationS().equals(node.getCapital())) {
                            route.setDestination(node);
                        }
                    }

                    cities.add(node);
                }
            }
            System.out.println("Cities Parsed from: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public static List<Route> routeParser(String filename) {
        List<Route> allRoutes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String originS = parts[0].trim();
                    String destinationS = parts[1].trim();
                    String type = parts[2].trim();
                    double time = Double.parseDouble(parts[3].trim());
                    double cost = Double.parseDouble(parts[4].trim());
                    String note = parts[5].trim();

                    Location origin = new Location();
                    Location destination = new Location();

                    Route edge = new Route(origin, destination, type, time, cost, note);
                    edge.setDestinationS(destinationS);
                    edge.setOriginS(originS);

                    allRoutes.add(edge);
                }
            }
            System.out.println("Routes Parsed from: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRoutes;
    }
}
