package no.nith.pg3200.a02.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import no.nith.pg3200.a02.adapters.AllForecastsAdapter;
import no.nith.pg3200.a02.adapters.SingleForecastAdapter;
import no.nith.pg3200.a02.domain.Forecast;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.Utils;

/**
 * @author Simen Bekkhus
 */
public class ForecastFragment extends ListFragment {

    private AllForecastsAdapter allForecastsAdapter;
    private SingleForecastAdapter forecastAdapter;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArrayList<WeatherData> weatherDataArray = Utils.getWeatherDataArray();
        allForecastsAdapter = new AllForecastsAdapter(weatherDataArray, getActivity());

        setListAdapter(allForecastsAdapter);

        if (weatherDataArray.isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "There are no weather-data saved to the system", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        final WeatherData weatherData = Utils.getWeatherDataArray().get(position);

        openSingleForecast(weatherData);
    }

    private void openSingleForecast(final WeatherData weatherData) {
        forecastAdapter = new SingleForecastAdapter((ArrayList<Forecast>) weatherData.getForecasts(), getActivity());

        setListAdapter(forecastAdapter);
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
            this.openSingleForecast(current);
        }
    }
}
