import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FileOperations {

    public static List<Location> locationParser(String filename, List<Route> routes) throws IOException {
        BufferedReader locations = new BufferedReader(new FileReader(filename));

        String line;
        List<Location> cities = new ArrayList<>();
        
        while ((line = locations.readLine()) != null) {
            String[] parts = line.split(",");
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

        locations.close();
        System.out.println("Cities Parsed from: " + filename);
        return cities;
    }

    public static List<Route> routeParser(String filename) throws IOException {
        BufferedReader routes = new BufferedReader(new FileReader(filename));

        String line;
        List<Route> allRoutes = new ArrayList<>();

        while ((line = routes.readLine()) != null) {
            String[] parts = line.split(",");
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

        routes.close();
        System.out.println("Routes Parsed from: " + filename);
        return allRoutes;
    }

    public static void outputGenerator(String filename, Stack<Location> cities, Stack<Route> routes, boolean costOrTime) throws IOException {
        FileWriter output = new FileWriter(filename);

        output.write("<HTML><HEAD><TITLE>Shortest path from Italy to Kazakhstan</TITLE></HEAD><script type='text/javascript' src='http://maps.google.com/maps/api/js?sensor=false'></script><script>function initialize() { var myOptions = { zoom: 3, center: new google.maps.LatLng(0, 0), mapTypeId: google.maps.MapTypeId.ROADMAP};var map=new google.maps.Map(document.getElementById('map'), myOptions);\n");

        int markerCount = 0;
        int contentStringCount = 0;
        Location origin;
        Location destination;
        Route route;

        while (!cities.empty() && !routes.empty()) {
            origin = cities.pop();
            destination = cities.pop();

            output.write("var marker" + markerCount + " = new google.maps.Marker({ position: new google.maps.LatLng(" + origin.getLat() + ", " + origin.getLon() + "), map: map, title: \"" + origin.getCapital() + ", " + origin.getCountry() + "\"});\n");

            markerCount++;

            output.write("var marker" + markerCount + " = new google.maps.Marker({ position: new google.maps.LatLng(" + destination.getLat() + ", " + destination.getLon() + "), map: map, title: \"" + destination.getCapital() + ", " + destination.getCountry() + "\"});\n");

            markerCount++;

            route = routes.pop();

            double cost = route.getCost();
            if (route.getTransport().equals("plane")) {
                cost = cost / Route.MULTI; // Assuming MULTI is defined elsewhere
            }

            output.write("var contentString" + contentStringCount + " = \"" + origin.getCapital() + ", " + origin.getCountry() + " --> " + destination.getCapital() + ", " + destination.getCountry() + "(" + route.getTransport() + " - " + route.getTime() + " hours - $" + cost + ")\"; var path" + contentStringCount + " = new google.maps.Polyline({ path: [new google.maps.LatLng(" + origin.getLat() + ", " + origin.getLon() + "), new google.maps.LatLng(" + destination.getLat() + ", " + destination.getLon() + ")], strokeColor: '#0000FF', strokeOpacity: 1.0, strokeWeight: 2}); path" + contentStringCount + ".setMap(map); google.maps.event.addListener(path" + contentStringCount + ", 'click', function(event) { alert(contentString" + contentStringCount + ");});\n");

            contentStringCount++;
        }

        output.write("} google.maps.event.addDomListener(window, 'load', initialize); </script></HEAD><BODY><div id='map' style='width:100%;height:100%;'></div></BODY></HTML>");
        output.close();

        System.out.println("Output File Generated: " + filename);
    }
}
