package no.nith.pg3200.exercises.oving2.task4;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import no.nith.pg3200.ovinger.R;

/**
 * Created by Simen on 06.09.13.
 */
public class Task4 extends Activity implements TabListener {
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving2task4);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setText(R.string.section1).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.section2).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.section3).setTabListener(this));
    }

    @Override
    public void onTabSelected(final Tab tab, final FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(final Tab tab, final FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(final Tab tab, final FragmentTransaction ft) {

    }
}
