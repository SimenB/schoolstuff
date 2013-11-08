package no.nith.pg3200.a02;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import no.nith.pg3200.a02.fragments.ForecastFragment;
import no.nith.pg3200.a02.fragments.MyMapFragment;
import no.nith.pg3200.a02.fragments.MyTabListener;
import no.nith.pg3200.a02.utils.Utils;

import static android.app.ActionBar.NAVIGATION_MODE_TABS;
import static android.app.ActionBar.Tab;

public class Main extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        initTabs();

        Utils.initUtils(this);

        //dao.deleteAllData();

        Utils.fetchWeatherData();
    }

    // http://youtu.be/d6uNjVEu7_Q
    private void initTabs() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(NAVIGATION_MODE_TABS);
        actionBar.setTitle(getString(R.string.app_name));

        final Tab mapTab = actionBar.newTab().setText(getString(R.string.map));
        final Tab forecastTab = actionBar.newTab().setText(getString(R.string.forecasts));

        final MyMapFragment mapFragment = new MyMapFragment();
        final ForecastFragment forecastFragment = new ForecastFragment();

        mapTab.setTabListener(new MyTabListener(mapFragment));
        forecastTab.setTabListener(new MyTabListener(forecastFragment));

        actionBar.addTab(mapTab);
        actionBar.addTab(forecastTab);
    }
}
