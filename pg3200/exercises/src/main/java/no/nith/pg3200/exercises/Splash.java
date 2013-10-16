package no.nith.pg3200.exercises;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import no.nith.pg3200.exercises.exercise2.Exercise2;
import no.nith.pg3200.exercises.exercise3.Exercise3;
import no.nith.pg3200.exercises.exercise4.Exercise4;

public class Splash extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final Context context = this;

        final ListView listView = (ListView) findViewById(R.id.splashListView);

        final String[] listOfExercises = new String[]{
                "Exercise2", "Exercise3", "Exercise4"
        };

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfExercises);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (((TextView) view).getText().toString()) {
                case "Exercise2":
                    startActivity(new Intent(context, Exercise2.class));
                    break;
                case "Exercise3":
                    startActivity(new Intent(context, Exercise3.class));
                    break;
                case "Exercise4":
                    startActivity(new Intent(context, Exercise4.class));
                    break;
                }
            }
        });
    }
}
