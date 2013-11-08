package no.nith.pg3200.a02.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import no.nith.pg3200.a02.adapters.SingleForecastAdapter;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.Utils;
import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class ForecastFragment extends ListFragment {

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void showWeatherData(final int hashId) {
        WeatherData current = null;

        for (final WeatherData weatherData : Utils.getWeatherDataArray()) {
            if (weatherData.hashCode() == hashId) {
                current = weatherData;
                break;
            }
        }

        if (current != null) {
            final DateTime created = current.getCreated();
        }
    }
}
