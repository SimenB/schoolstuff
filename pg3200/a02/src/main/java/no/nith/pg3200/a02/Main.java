package no.nith.pg3200.a02;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import no.nith.pg3200.a02.domain.WeatherData;
import no.nith.pg3200.a02.fragments.ForecastsFragment;
import no.nith.pg3200.a02.fragments.MyMapFragment;
import no.nith.pg3200.a02.fragments.MyTabListener;
import no.nith.pg3200.a02.fragments.SingleForecastFragment;
import no.nith.pg3200.a02.utils.Utils;

import static android.app.ActionBar.NAVIGATION_MODE_TABS;
import static android.app.ActionBar.Tab;
import static no.nith.pg3200.a02.fragments.MyMapFragment.OnForecastClickedListener;

public class Main extends Activity implements OnForecastClickedListener {

    private MyMapFragment mapFragment;
    private ForecastsFragment forecastsFragment;
    private SingleForecastFragment singleForecastFragment;
    private ActionBar actionBar;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        initTabs();

        Utils.initUtils(this);

        //dao.deleteAllData();

        Utils.fetchWeatherData();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.my_options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.clear_data:
            askUserClearData(false);
            return true;
        case R.id.clear_all_data:
            askUserClearData(true);
            return true;
        case android.R.id.home:
            final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_wrapper, forecastsFragment);
            fragmentTransaction.commit();
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void askUserClearData(final boolean allData) {
        new AlertDialog.Builder(this)
                .setTitle("Deleting data")
                .setMessage("Are you sure you want to delete all items?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        Utils.deleteData(true, null);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // http://youtu.be/d6uNjVEu7_Q
    private void initTabs() {
        actionBar = getActionBar();
        actionBar.setNavigationMode(NAVIGATION_MODE_TABS);
        actionBar.setTitle(getString(R.string.app_name));

        final Tab mapTab = actionBar.newTab().setText(getString(R.string.map));
        final Tab forecastTab = actionBar.newTab().setText(getString(R.string.forecasts));

        mapFragment = new MyMapFragment();
        forecastsFragment = new ForecastsFragment();
        singleForecastFragment = new SingleForecastFragment();

        mapTab.setTabListener(new MyTabListener(mapFragment));
        forecastTab.setTabListener(new MyTabListener(forecastsFragment));

        actionBar.addTab(mapTab);
        actionBar.addTab(forecastTab);
    }

    @Override
    public void showWeatherData(final int hashId) {
        actionBar.setSelectedNavigationItem(1);

        for (final WeatherData weatherData : Utils.getWeatherDataArray()) {
            if (weatherData.hashCode() == hashId) {
                final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_wrapper, singleForecastFragment);
                fragmentTransaction.commit();

                singleForecastFragment.openForecast(weatherData, this);

                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);

                Toast.makeText(this, "Press the Home-key on the actionbar to view all forecast", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
