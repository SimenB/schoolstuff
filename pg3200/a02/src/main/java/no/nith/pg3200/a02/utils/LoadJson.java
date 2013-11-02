package no.nith.pg3200.a02.utils;

import android.os.AsyncTask;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimeZone;
import no.nith.pg3200.a02.domain.WeatherData;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Simen Bekkhus
 */
public class LoadJson extends AsyncTask<Void, Void, String> {
    final String path;

    public LoadJson(final double lat, final double lon) {
        path = String.format("http://weathermap-nith.appspot.com/locationforecast?lat=%f&lon=%f", lat, lon);
    }

    @Override
    protected String doInBackground(Void... params) {
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
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String json = "";

        try {
            json = convertJson(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            //final WeatherData weatherData = new Gson().fromJson(json, WeatherData.class);
            final WeatherData weatherData = new GsonBuilder()
                    .registerTypeAdapter(DateTime.class, new DateTimeTypeConverter())
                    .create()
                    .fromJson(json, WeatherData.class);
            String hei = "";
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives JSON from the API, and converts it to the local domain
     *
     * @param json
     *         JSON from the Yr-API
     * @return JSON representing the local domain-object
     * @throws org.json.JSONException
     */
    private String convertJson(String json) throws JSONException {
        final JSONObject weatherdata = new JSONObject(json).getJSONObject("weatherdata");
        JSONArray k = weatherdata.getJSONObject("product").getJSONArray("time");
        weatherdata.put("forecasts", k);
        k = weatherdata.getJSONArray("forecasts");

        weatherdata.remove("product");
        weatherdata.remove("xmlns:xsi");
        weatherdata.remove("meta");
        weatherdata.remove("xsi:noNamespaceSchemaLocation");

        final JSONObject location1 = ((JSONObject) k.get(0)).getJSONObject("location");
        weatherdata.put("longitude", location1.getDouble("longitude"));
        weatherdata.put("latitude", location1.getDouble("latitude"));

        for (int i = 0; i < k.length(); i++) {
            final JSONObject current = (JSONObject) k.get(i);
            final String time = current.getString("to");
            final JSONObject location = current.getJSONObject("location");
            final double temperature = location.getJSONObject("temperature").getDouble("value");

            current.remove("to");
            current.remove("datatype");
            current.remove("from");
            current.remove("location");

            current.put("time", time);
            current.put("temperature", temperature);
        }

        return weatherdata.toString();
    }

    // Taken from https://sites.google.com/site/gson/gson-type-adapters-for-common-classes
    // I changed the timezone when creating a new instance, otherwise pure copy.
    private class DateTimeTypeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {
        // No need for an InstanceCreator since DateTime provides a no-args constructor
        @Override
        public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }

        @Override
        public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
                throws JsonParseException {

            return new DateTime(json.getAsString(), DateTimeZone.forTimeZone(TimeZone.getTimeZone("Europe/Oslo")));
            //return new DateTime(json.getAsString(), DateTimeZone.UTC);
            //return new DateTime(json.getAsString(), DateTimeZone.forOffsetHours(1));
        }
    }
}
