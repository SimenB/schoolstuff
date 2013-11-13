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
import static no.nith.pg3200.a02.fragments.MyMapFragment.OnForecastAddedListener;
import static no.nith.pg3200.a02.fragments.MyMapFragment.OnForecastClickedListener;

/**
 * @author Simen Bekkhus
 */
public class Main extends Activity implements OnForecastClickedListener, OnForecastAddedListener {
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
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.my_options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.activate_location:
            mapFragment.activateLocation();
            return true;
        case R.id.clear_all_data:
            askUserClearData();
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

    private void askUserClearData() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.delete_data_title))
                .setMessage(getString(R.string.delete_data_text))
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        Utils.deleteData(true, null);
                    }
                })
                .setNegativeButton(getString(R.string.no), null)
                .show();
    }

    @Override
    public void onBackPressed() {
        // TODO: Should something be done?
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

        mapTab.setTabListener(new MyTabListener(mapFragment, actionBar));
        forecastTab.setTabListener(new MyTabListener(forecastsFragment, actionBar));

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

                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);

                singleForecastFragment.openForecast(weatherData, this);

                Toast.makeText(this, getString(R.string.navigate_to_forecasts), Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    @Override
    public void newDataAdded() {
        forecastsFragment.notifyAdapter();
        singleForecastFragment.notifyAdapter();
    }
}
