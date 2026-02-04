package CarBooking.Final.Project.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import CarBooking.Final.Project.dto.google.GoogleDirectionsResponse;
import CarBooking.Final.Project.service.GoogleMapsService;

@Service
public class GoogleMapsServiceImpl implements GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public double getDistanceInKm(double pickupLat,double pickupLng, double dropLat, double dropLng) {

        try {
            String url =
                "https://maps.googleapis.com/maps/api/directions/json"
                + "?origin=" + pickupLat + "," + pickupLng
                + "&destination=" + dropLat + "," + dropLng
                + "&key=" + apiKey;

            GoogleDirectionsResponse response =
                    restTemplate.getForObject(url, GoogleDirectionsResponse.class);

            if (response != null
                    && response.routes != null
                    && !response.routes.isEmpty()
                    && response.routes.get(0).legs != null
                    && !response.routes.get(0).legs.isEmpty()) {

                double meters =
                        response.routes.get(0).legs.get(0).distance.value;

                return meters / 1000.0; // meters → KM
            }

        } catch (Exception e) {}
        return fallbackDistance(pickupLat, pickupLng, dropLat, dropLng);
    }

    private double fallbackDistance(double lat1, double lng1,double lat2, double lng2) {

        double earthRadius = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }
}
