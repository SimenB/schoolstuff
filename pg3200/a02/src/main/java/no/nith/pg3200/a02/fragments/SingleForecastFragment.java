package no.nith.pg3200.a02.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import no.nith.pg3200.a02.R;
import no.nith.pg3200.a02.adapters.SingleForecastAdapter;
import no.nith.pg3200.a02.domain.WeatherData;

/**
 * @author Simen Bekkhus
 */
public class SingleForecastFragment extends ListFragment {
    private SingleForecastAdapter forecastAdapter;
    private Activity activity;
    private WeatherData weatherData;

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        final TextView textView = new TextView(activity);
        final LatLng position = weatherData.getPosition();

        textView.setText(String.format(getString(R.string.lat_lng), position.latitude, position.longitude));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        textView.setGravity(Gravity.CENTER);
        textView.setHeight(2 * textView.getLineHeight());

        setListAdapter(null);

        getListView().addHeaderView(textView);

        forecastAdapter = new SingleForecastAdapter(weatherData.getForecasts(), activity);

        setListAdapter(forecastAdapter);
    }

    public void openForecast(final WeatherData weatherData, final Activity activity) {
        this.activity = activity;
        this.weatherData = weatherData;
    }

    public void notifyAdapter() {
        if (forecastAdapter != null) {
            forecastAdapter.notifyDataSetChanged();
        }
    }
}