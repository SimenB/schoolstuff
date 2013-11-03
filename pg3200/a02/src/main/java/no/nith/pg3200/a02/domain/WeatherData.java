package no.nith.pg3200.a02.domain;

import com.google.android.gms.maps.model.LatLng;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class WeatherData implements Comparable<WeatherData> {
    private DateTime created;
    private LatLng position;
    private List<Forecast> forecasts;

    public WeatherData(final String created, final LatLng position, final List<Forecast> forecasts) {
        this.created = new DateTime(created);
        this.position = position;
        this.forecasts = forecasts;

        Collections.sort(this.forecasts);
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
        if (!(o instanceof WeatherData)) return false;

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

    @Override
    public int compareTo(@NotNull final WeatherData another) {
        return this.getCreated().compareTo(another.getCreated());
    }
}
