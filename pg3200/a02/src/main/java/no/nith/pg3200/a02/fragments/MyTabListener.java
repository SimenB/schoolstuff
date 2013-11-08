package no.nith.pg3200.a02.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import no.nith.pg3200.a02.R;

import static android.app.ActionBar.Tab;

/**
 * @author Simen Bekkhus
 */

public class MyTabListener implements ActionBar.TabListener {
    private final Fragment fragment;

    public MyTabListener(final MyMapFragment mapFragment) {
        this.fragment = mapFragment;
    }

    public MyTabListener(final ForecastFragment forecastFragment) {
        this.fragment = forecastFragment;
    }

    @Override
    public void onTabSelected(final Tab tab, final FragmentTransaction ft) {
        ft.replace(R.id.fragment_wrapper, fragment);
    }

    @Override
    public void onTabUnselected(final Tab tab, final FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(final Tab tab, final FragmentTransaction ft) {
    }
}
