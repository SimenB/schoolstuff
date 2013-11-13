package no.nith.pg3200.a02.fragments;

import android.app.Activity;
import android.app.ListFragment;
import no.nith.pg3200.a02.adapters.SingleForecastAdapter;
import no.nith.pg3200.a02.domain.WeatherData;

/**
 * @author Simen Bekkhus
 */
public class SingleForecastFragment extends ListFragment {
    private SingleForecastAdapter forecastAdapter;

    public void openForecast(final WeatherData current, final Activity activity) {
        forecastAdapter = new SingleForecastAdapter(current.getForecasts(), activity);

        setListAdapter(forecastAdapter);
    }
}