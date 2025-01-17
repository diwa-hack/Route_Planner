public class Route {
    private Location origin;
    private Location destination;
    private String originS;
    private String destinationS;
    private String transport;
    private float time;
    private float cost;
    private String note;

public static final float MULTI = 3;

    public Route() {
        this.origin = null;
        this.destination = null;
        this.transport = "";
        this.time = 0;
        this.cost = 0;
        this.note = "";
    }

    public Route(Location org, Location dest) {
        this.origin = org;
        this.destination = dest;
        this.transport = "";
        this.time = 0;
        this.cost = 0;
        this.note = "";
    }

    public Route(Location org, Location dest, String trans, double tim, double cst, String note) {
        this.origin = org;
        this.destination = dest;
        this.transport = trans;
        this.time = (float) tim;
        this.cost = (float)cst;
        this.note = note;

        if (trans.equals("plane")) {
            this.cost = (float)(cst * MULTI);
        }
    }

    public boolean doesConnect(Location start, Location end) {
        return start == origin && end == destination;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public String getOriginS() {
        return originS;
    }

    public void setOriginS(String originS) {
        this.originS = originS;
    }

    public String getDestinationS() {
        return destinationS;
    }

    public void setDestinationS(String destinationS) {
        this.destinationS = destinationS;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
