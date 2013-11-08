package no.nith.pg3200.a02.fragments;

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
    private GoogleMap googleMap;
    private ArrayList<WeatherData> weatherDataArray;

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        googleMap = getMap();

        googleMap.setMyLocationEnabled(true);

        Utils.fetchWeatherData();

        weatherDataArray = Utils.getWeatherDataArray();

        drawAllIcons();

//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        // Creating a criteria object to retrieve provider
//        Criteria criteria = new Criteria();
//
//        // Getting the name of the best provider
//        String provider = locationManager.getBestProvider(criteria, true);
//
//        // Getting Current Location
//        Location location = locationManager.getLastKnownLocation(provider);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                String name = marker.getTitle();
                Log.i("whoop", name);
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
        weatherDataArray.add(weatherData);

        Utils.getDao().insertWeatherData(weatherData);

        addMarkerToMap(weatherData);
    }

    private void addMarkerToMap(final WeatherData weatherData) {
        final int symbol = weatherData.getForecasts().get(0).getSymbol();

        googleMap.addMarker(new MarkerOptions()
                .position(weatherData.getPosition())
                .icon(BitmapDescriptorFactory.fromResource(Utils.getIcon(symbol)))
                .title("" + weatherData.hashCode())
        );
    }
}
