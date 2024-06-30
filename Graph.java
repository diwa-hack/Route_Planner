import java.util.*;
import java.io.*;

public class Graph {
    private List<Location> cities;
    private List<Route> routes;
    private int numExists;

    public Graph(String nodesFile, String edgesFile) {
        try {
            routes = FileOperations.routeParser(edgesFile);
        } catch (IOException e) {
            // Handle the exception, e.g., print an error message or log it
            e.printStackTrace();
        }
        
        try {
            cities = FileOperations.locationParser(nodesFile, routes);
        } catch (IOException e) {
            // Handle the exception, e.g., print an error message or log it
            e.printStackTrace();
        }
        numExists = cities.size();
    }

    public int getCityIndex(String key) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getCapital().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public Location getCity(String key) {
        int index = getCityIndex(key);
        if (index != -1) {
            return cities.get(index);
        } else {
            return null;
        }
    }

    public float getWeight(String startS, String endS, boolean costOrTime) {
        Location start = getCity(startS);
        Location end = getCity(endS);
        if (start != null && end != null) {
            for (Route route : routes) {
                if (route.doesConnect(start, end)) {
                    return costOrTime ? route.getCost() : route.getTime();
                }
            }
        }
        return -1;
    }

    public float getWeight(Location start, Location end, boolean costOrTime) {
        if (start != null && end != null) {
            for (Route route : routes) {
                if (route.doesConnect(start, end)) {
                    return costOrTime ? route.getCost() : route.getTime();
                }
            }
        }
        return -1;
    }

    public void Dijkstras(String startS, boolean costOrTime) {
        Location start = getCity(startS);
        if (start == null) return;

        start.setLengthFromStart(0);
        PriorityQueue<Location> minHeap = new PriorityQueue<>((l1, l2) -> Float.compare(l1.getLengthFromStart(), l2.getLengthFromStart()));
        while (!minHeap.isEmpty()) {
            Location smallest = minHeap.poll();
            smallest.setExists(false);

            List<Location> adjacentCities = adjacentLocations(smallest);

            for (Location adjacent : adjacentCities) {
                float distance = getWeight(smallest, adjacent, costOrTime) + smallest.getLengthFromStart();

                if (distance < adjacent.getLengthFromStart()) {
                    adjacent.setLengthFromStart(distance);
                    adjacent.setPrevious(smallest);
                }

                minHeap.remove(adjacent);
                minHeap.add(adjacent);
            }
        }
    }

    public List<Location> adjacentLocations(Location city) {
        List<Location> output = new ArrayList<>();
        for (Route route : city.getRoutes()) {
            if (route.getDestination().isExists()) {
                output.add(route.getDestination());
            }
        }
        return output;
    }

    public List<Route> adjacentRoutes(Location city) {
        List<Route> output = new ArrayList<>();
        for (Route route : routes) {
            if (route.getOrigin().getCapital().equals(city.getCapital())) {
                output.add(route);
            }
        }
        return output;
    }

    public Stack<Location> cityStacker(String destinationS) {
        Location destination = getCity(destinationS);
        Stack<Location> output = new Stack<>();

        while (destination != null) {
            output.push(destination);
            destination = destination.getPrevious();
        }

        return output;
    }

    public Stack<Route> routeStacker(String destinationS, boolean costOrTime) {
        Stack<Route> output = new Stack<>();
        Location destination = getCity(destinationS);

        float totalDistance = destination.getLengthFromStart();

        while (destination.getPrevious() != null) {
            output.push(getRoute(destination.getPrevious(), costOrTime, totalDistance));
            destination = destination.getPrevious();
            totalDistance = destination.getLengthFromStart();
        }

        return output;
    }

    public Route getRoute(Location start, boolean costOrTime, float totalDistance) {
        List<Route> adjacentRoutes = adjacentRoutes(start);
        final double epsilon = 1e-5;

        for (Route route : adjacentRoutes) {
            if (costOrTime) {
                if (Math.abs((totalDistance - route.getCost()) - route.getOrigin().getLengthFromStart()) > epsilon) {
                    return route;
                }
            } else {
                if (Math.abs((totalDistance - route.getTime()) - route.getOrigin().getLengthFromStart()) > epsilon) {
                    return route;
                }
            }
        }
        return null;
    }
    // public static void main(String[] args) {
    //     Graph x= new Graph("cities.csv", "routes.csv");
    //  System.out.println(x.getCityIndex("New Delhi"));
    
    // }
}
