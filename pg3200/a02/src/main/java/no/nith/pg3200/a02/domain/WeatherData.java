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

    public WeatherData(final String created, final LatLng position, final List<Forecast> forecasts) {
        this.created = new DateTime(created);
        this.position = position;
        this.forecasts = forecasts;
    }

    public WeatherData() {
    }

    public DateTime getCreated() {
        return created;
    }

    public LatLng getPosition() {
        return position;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final WeatherData that = (WeatherData) o;

        if (!created.equals(that.created)) return false;
        if (!position.equals(that.position)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = created.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}
