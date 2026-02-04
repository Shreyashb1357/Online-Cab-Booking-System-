package CarBooking.Final.Project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FareConfig {

    @Value("${cab.fare.base}")
    private double baseFare;

    @Value("${cab.fare.per.km}")
    private double perKmRate;

    @Value("${cab.fare.per.minute}")
    private double perMinuteRate;

    public double getBaseFare() { return baseFare; }
    public double getPerKmRate() { return perKmRate; }
    public double getPerMinuteRate() { return perMinuteRate; }
}
