import java.util.*;

public class Location {
    private String country;
    private String capital;
    private float lat;
    private float lon;
    private boolean exists;
    private Location previous;
    private float lengthFromStart;
    private List<Route> routes;

    // Constructors
    public Location() {
        this.country = "";
        this.capital = "";
        this.lat = 0;
        this.lon = 0;
        this.lengthFromStart = Float.POSITIVE_INFINITY; // Highest possible value for comparison
        this.exists = true;
        this.previous = null;
        this.routes = new ArrayList<>();
    }

    public Location(String country, String capital) {
        this.country = country;
        this.capital = capital;
        this.lat = 0;
        this.lon = 0;
        this.lengthFromStart = Float.POSITIVE_INFINITY;
        this.exists = true;
        this.previous = null;
        this.routes = new ArrayList<>();
    }

    public Location(String country, String capital, double lat, double lon) {
        this.country = country;
        this.capital = capital;
        this.lat = (float)lat;
        this.lon = (float)lon;
        this.lengthFromStart = Float.POSITIVE_INFINITY;
        this.exists = true;
        this.previous = null;
        this.routes = new ArrayList<>();
    }

    // Getters and Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Location getPrevious() {
        return previous;
    }

    public void setPrevious(Location previous) {
        this.previous = previous;
    }

    public float getLengthFromStart() {
        return lengthFromStart;
    }

    public void setLengthFromStart(float lengthFromStart) {
        this.lengthFromStart = lengthFromStart;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    // Methods
    public float getWeight(Location start, Location end) {
        // Implement your logic for calculating weight between two locations
        return 0;
    }

    // Override methods for comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(capital, location.capital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capital);
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", capital='" + capital + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
  
   
}
