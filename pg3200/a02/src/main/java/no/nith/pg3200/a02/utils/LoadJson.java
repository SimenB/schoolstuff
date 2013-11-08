package no.nith.pg3200.a02.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import no.nith.pg3200.a02.domain.WeatherData;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class LoadJson extends AsyncTask<Void, Integer, String> {
    private final String path;
    private final Context context;
    private final CallbackListener callbackListener;
    private ProgressDialog dialog;

    public LoadJson(final LatLng latlng, final Context context, final CallbackListener callbackListener) {
        path = String.format("http://weathermap-nith.appspot.com/locationforecast?lat=%f&lon=%f", latlng.latitude, latlng.longitude);
        this.context = context;
        this.callbackListener = callbackListener;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Loading", "Loading weather-data, please wait", true);

        dialog.show();
    }

    @Override
    protected String doInBackground(final Void... params) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        StringWriter stringWriter = null;
        String res = "";

        try {
            URL url = new URL(this.path);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            stringWriter = new StringWriter();

            IOUtils.copy(inputStream, stringWriter, "UTF-8");

            res = stringWriter.toString();
        } catch (IOException ignored) {
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {

                if (inputStream != null) {
                    inputStream.close();
                }

                if (stringWriter != null) {
                    stringWriter.close();
                }
            } catch (IOException ignored) {
            }
        }

        return res;
    }

    @Override
    protected void onPostExecute(final String s) {
        super.onPostExecute(s);

        Log.i("WeatherMap", "JSON returned from service: " + s);

        JsonObject json = convertJson(s);

        WeatherData weatherData = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
                .registerTypeAdapter(LatLng.class, new LatLngTypeConverter())
                .create()
                .fromJson(json, WeatherData.class);

        Log.i("mytag", json.toString());

        Collections.sort(weatherData.getForecasts());

        this.dialog.dismiss();

        this.callbackListener.addWeatherDataToList(weatherData);
    }

    /**
     * Receives JSON from the API, and converts it to the local domain
     *
     * @param json
     *         JSON from the Yr-API
     * @return JSON representing the local domain-object
     */
    private JsonObject convertJson(final String json) {
        final JsonObject origJsonObject = new JsonParser().parse(json).getAsJsonObject().getAsJsonObject("weatherdata"),
                newJsonObject = new JsonObject();

        newJsonObject.add("forecasts", origJsonObject.getAsJsonObject("product").getAsJsonArray("time"));
        newJsonObject.add("created", origJsonObject.getAsJsonPrimitive("created"));

        final JsonArray forecasts = newJsonObject.getAsJsonArray("forecasts");
        final JsonObject location = forecasts.get(0).getAsJsonObject().getAsJsonObject("location");
        final JsonObject position = new JsonObject();

        position.add("longitude", location.getAsJsonPrimitive("longitude"));
        position.add("latitude", location.getAsJsonPrimitive("latitude"));

        newJsonObject.add("position", position);

        for (int i = 0; i < forecasts.size(); i++) {
            final JsonObject current = (JsonObject) forecasts.get(i);
            final String time = current.getAsJsonPrimitive("to").getAsString();
            final double temperature = current.getAsJsonObject("location").getAsJsonObject("temperature").getAsJsonPrimitive("value").getAsDouble();

            current.remove("to");
            current.remove("datatype");
            current.remove("from");
            current.remove("location");

            current.addProperty("time", time);
            current.addProperty("temperature", temperature);
        }

        return newJsonObject;
    }

    // Taken from https://sites.google.com/site/gson/gson-type-adapters-for-common-classes
    private class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        // No need for an InstanceCreator since DateTime provides a no-args constructor
        @Override
        public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {

            return new DateTime(json.getAsString());
        }
    }

    private class LatLngTypeConverter implements JsonSerializer<LatLng>, JsonDeserializer<LatLng> {
        // No need for an InstanceCreator since DateTime provides a no-args constructor
        @Override
        public JsonElement serialize(LatLng src, Type srcType, JsonSerializationContext context) {
            final JsonObject jsonObject = new JsonObject();
            jsonObject.add("latitude", new JsonPrimitive(src.latitude));
            jsonObject.add("longitude", new JsonPrimitive(src.longitude));
            return jsonObject;
        }

        @Override
        public LatLng deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {
            final JsonObject data = json.getAsJsonObject();

            final double lat = data.get("latitude").getAsDouble();
            final double lng = data.get("longitude").getAsDouble();

            return new LatLng(lat, lng);
        }
    }
}
