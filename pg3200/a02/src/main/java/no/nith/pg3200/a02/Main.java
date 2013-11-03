package no.nith.pg3200.a02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.CallbackListener;
import no.nith.pg3200.a02.utils.LoadJson;

public class Main extends Activity implements CallbackListener {

    private GoogleMap googleMap;
    private Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        googleMap.setMyLocationEnabled(true);
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
            public boolean onMarkerClick(Marker marker) {
                final LatLng position = marker.getPosition();

                return true;
            }
        });
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                fetchWeatherData(latLng);
            }
        });
    }

    private void fetchWeatherData(LatLng latLng) {
        Log.i("mytag", "Position pressed was" + latLng.toString());

        new LoadJson(latLng, context, this).execute();
    }

    @Override
    public void callback(WeatherData weatherData) {
        weatherData.toString();
    }
}
