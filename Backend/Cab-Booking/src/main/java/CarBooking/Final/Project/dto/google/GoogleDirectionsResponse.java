package CarBooking.Final.Project.dto.google;

import java.util.List;

public class GoogleDirectionsResponse {
    public List<Route> routes;

    public static class Route {
        public List<Leg> legs;
    }

    public static class Leg {
        public Distance distance;
    }

    public static class Distance {
        public double value; // meters
    }
}
