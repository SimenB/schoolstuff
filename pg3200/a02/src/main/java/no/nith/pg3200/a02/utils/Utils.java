package no.nith.pg3200.a02.utils;

import android.content.Context;
import com.google.android.gms.maps.model.Marker;
import java.util.ArrayList;
import java.util.List;
import no.nith.pg3200.a02.R;
import no.nith.pg3200.a02.db.WeatherDataDao;
import no.nith.pg3200.a02.domain.WeatherData;
import org.jetbrains.annotations.NotNull;

/**
 * @author Simen Bekkhus
 */
public final class Utils {
    private static final List<Integer> mapIcons = new ArrayList<Integer>() {{
        add(R.drawable.img_1);
        add(R.drawable.img_2);
        add(R.drawable.img_3);
        add(R.drawable.img_4);
        add(R.drawable.img_5);
        add(R.drawable.img_6);
        add(R.drawable.img_7);
        add(R.drawable.img_8);
        add(R.drawable.img_9);
        add(R.drawable.img_10);
        add(R.drawable.img_11);
        add(R.drawable.img_12);
        add(R.drawable.img_13);
        add(R.drawable.img_14);
        add(R.drawable.img_15);
        add(R.drawable.img_16);
        add(R.drawable.img_17);
        add(R.drawable.img_18);
        add(R.drawable.img_19);
        add(R.drawable.img_20);
        add(R.drawable.img_21);
        add(R.drawable.img_22);
        add(R.drawable.img_23);
    }};
    private static ArrayList<WeatherData> weatherDataArray;
    @NotNull
    private static WeatherDataDao dao;
    private static ArrayList<Marker> markers;

    private Utils() {
    }

    public static void initUtils(final Context context) {
        dao = new WeatherDataDao(context);
        markers = new ArrayList<Marker>();
    }

    public static ArrayList<WeatherData> getWeatherDataArray() {
        if (weatherDataArray == null) fetchWeatherData();
        return weatherDataArray;
    }

    public static ArrayList<Marker> getMarkers() {
        return markers;
    }

    public static void deleteData() {
        dao.deleteAllData();
        weatherDataArray.clear();

        for (final Marker marker : markers) {
            marker.remove();
        }

        markers.clear();
    }

    public static void fetchWeatherData() {
        weatherDataArray = dao.getAllWeatherData();
    }

    public static int getIcon(final int index) {
        return mapIcons.get(index);
    }

    public static void addWeatherData(final WeatherData weatherData) {
        weatherDataArray.add(weatherData);

        dao.insertWeatherData(weatherData);
    }

    public static void addMarker(final Marker marker) {
        markers.add(marker);
    }
}
