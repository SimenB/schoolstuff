package no.nith.pg3200.a02.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import no.nith.pg3200.a02.Main;
import no.nith.pg3200.a02.adapters.AllForecastsAdapter;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.utils.Utils;

/**
 * @author Simen Bekkhus
 */
public class ForecastsFragment extends ListFragment {

    private AllForecastsAdapter allForecastsAdapter;

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
        final int weatherDataHash = Utils.getWeatherDataArray().get(position).hashCode();

        ((Main) getActivity()).showWeatherData(weatherDataHash);
    }

    //http://developer.android.com/guide/components/fragments.html#EventCallbacks
    //http://developer.android.com/reference/android/app/ListFragment.html
    //http://www.sitepoint.com/10-reasons-use-angularjs/
}
