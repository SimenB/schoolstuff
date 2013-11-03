package no.nith.pg3200.a02;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import no.nith.pg3200.a02.db.WeatherDataDao;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.CallbackListener;
import no.nith.pg3200.a02.utils.LoadJson;

public class Main extends Activity implements CallbackListener {
    private static final SparseIntArray map = new SparseIntArray() {{
        put(1, R.drawable.img_1);
        put(2, R.drawable.img_2);
        put(3, R.drawable.img_3);
        put(4, R.drawable.img_4);
        put(5, R.drawable.img_5);
        put(6, R.drawable.img_6);
        put(7, R.drawable.img_7);
        put(8, R.drawable.img_8);
        put(9, R.drawable.img_9);
        put(10, R.drawable.img_10);
        put(11, R.drawable.img_11);
        put(12, R.drawable.img_12);
        put(13, R.drawable.img_13);
        put(14, R.drawable.img_14);
        put(15, R.drawable.img_15);
        put(16, R.drawable.img_16);
        put(17, R.drawable.img_17);
        put(18, R.drawable.img_18);
        put(19, R.drawable.img_19);
        put(20, R.drawable.img_20);
        put(21, R.drawable.img_21);
        put(22, R.drawable.img_22);
        put(23, R.drawable.img_23);
    }};
    private GoogleMap googleMap;
    private Context context;
    private ArrayList<WeatherData> weatherDataArray;
    private WeatherDataDao dao;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        context = this;

        dao = new WeatherDataDao(context);

        //dao.deleteAllData();

        weatherDataArray = dao.getAllWeatherData();

        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        googleMap.setMyLocationEnabled(true);

        if (!weatherDataArray.isEmpty()) {
            for (WeatherData weatherData : weatherDataArray) {
                this.addMarkerToMap(weatherData);
            }
        }

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

    private void fetchWeatherData(final LatLng latLng) {
        Log.i("WeatherMap", "Fetching weather from " + latLng.toString());

        new LoadJson(latLng, context, this).execute();
    }

    @Override
    public void addWeatherDataToList(final WeatherData weatherData) {
        weatherDataArray.add(weatherData);

        dao.insertWeatherData(weatherData);

        addMarkerToMap(weatherData);
    }

    private void addMarkerToMap(final WeatherData weatherData) {
        final int symbol = weatherData.getForecasts().get(0).getSymbol();

        googleMap.addMarker(new MarkerOptions()
                .position(weatherData.getPosition())
                .icon(BitmapDescriptorFactory.fromResource(map.get(symbol)))
                .title("" + weatherData.hashCode())
        );
    }
}
