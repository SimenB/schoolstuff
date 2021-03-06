package no.nith.pg3200.a02.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.CallbackListener;
import no.nith.pg3200.a02.utils.LoadJson;
import no.nith.pg3200.a02.utils.Utils;

/**
 * @author Simen Bekkhus
 */
public class MyMapFragment extends MapFragment implements CallbackListener {
    private OnForecastClickedListener forecastClickedListener;
    private OnForecastAddedListener forecastAddedListener;
    private GoogleMap googleMap;
    private ArrayList<WeatherData> weatherDataArray;
    private boolean myLocationEnabled = false;

    // http://developer.android.com/guide/components/fragments.html#EventCallbacks
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        try {
            forecastClickedListener = (OnForecastClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnForecastClickedListener");
        }
        try {
            forecastAddedListener = (OnForecastAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnForecastAddedListener");
        }
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        googleMap = getMap();

        weatherDataArray = Utils.getWeatherDataArray();

        drawAllIcons();

        // Avoid reloading every single time
        setRetainInstance(true);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                openForecast(marker);
                return true;
            }
        });

        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                fetchWeatherData(latLng);
            }
        });
    }

    private void openForecast(final Marker marker) {
        final int id = Integer.parseInt(marker.getTitle());

        Log.i("Icon pressed with ID: ", "" + id);

        forecastClickedListener.showWeatherData(id);
    }

    private void drawAllIcons() {
        if (!weatherDataArray.isEmpty()) {
            for (final WeatherData weatherData : weatherDataArray) {
                this.addMarkerToMap(weatherData);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.drawAllIcons();
    }

    private void fetchWeatherData(final LatLng latLng) {
        Log.i("WeatherMap", "Fetching weather from " + latLng.toString());

        new LoadJson(latLng, getActivity(), this).execute();
    }

    @Override
    public void addWeatherDataToList(final WeatherData weatherData) {
        Utils.addWeatherData(weatherData);

        forecastAddedListener.newDataAdded();

        addMarkerToMap(weatherData);
    }

    private void addMarkerToMap(final WeatherData weatherData) {
        final int symbol = weatherData.getForecasts().get(0).getSymbol();

        final Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(weatherData.getPosition())
                .icon(BitmapDescriptorFactory.fromResource(Utils.getIcon(symbol)))
                .title("" + weatherData.hashCode())
        );

        Utils.addMarker(marker);
    }

    /**
     * Toggle the My Location indicator
     */
    public void activateLocation() {
        myLocationEnabled = !myLocationEnabled;
        googleMap.setMyLocationEnabled(myLocationEnabled);
    }

    public interface OnForecastClickedListener {
        public void showWeatherData(final int hashId);
    }

    public interface OnForecastAddedListener {
        public void newDataAdded();
    }
}
