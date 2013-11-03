package no.nith.pg3200.a02.domain;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class WeatherData {
    private DateTime created;
    private LatLng position;
    private List<Forecast> forecasts;

    public DateTime getCreated() {
        return created;
    }

    public LatLng getPosition() {
        return position;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
