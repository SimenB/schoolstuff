package no.nith.pg3200.exercises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import no.nith.pg3200.exercises.exercise2.Exercise2;
import no.nith.pg3200.exercises.exercise3.Exercise3;
import no.nith.pg3200.exercises.exercise4.Exercise4;

public class Splash extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final Button task2 = (Button) findViewById(R.id.exercise2);
        final Button task3 = (Button) findViewById(R.id.exercise3);
        final Button task4 = (Button) findViewById(R.id.exercise4);

        task2.setOnClickListener(this);
        task3.setOnClickListener(this);
        task4.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.exercise2:
            startActivity(new Intent(this, Exercise2.class));
            break;
        case R.id.exercise3:
            startActivity(new Intent(this, Exercise3.class));
            break;
        case R.id.exercise4:
            startActivity(new Intent(this, Exercise4.class));
            break;
        }
    }
}
