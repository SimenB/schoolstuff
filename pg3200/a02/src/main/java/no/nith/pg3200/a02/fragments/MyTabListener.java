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
    private final ActionBar actionBar;

    public MyTabListener(final MyMapFragment mapFragment, final ActionBar actionBar) {
        this.fragment = mapFragment;
        this.actionBar = actionBar;
    }

    public MyTabListener(final ForecastsFragment forecastsFragment, final ActionBar actionBar) {
        this.fragment = forecastsFragment;
        this.actionBar = actionBar;
    }

    @Override
    public void onTabSelected(final Tab tab, final FragmentTransaction ft) {
        ft.replace(R.id.fragment_wrapper, fragment);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onTabUnselected(final Tab tab, final FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(final Tab tab, final FragmentTransaction ft) {
    }
}
