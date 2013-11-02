package no.nith.pg3200.a02.domain;

import java.util.List;
import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class WeatherData {
    private DateTime created;
    private double latitude, longitude;
    private List<Forecast> forecasts;

    public DateTime getCreated() {
        return created;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
