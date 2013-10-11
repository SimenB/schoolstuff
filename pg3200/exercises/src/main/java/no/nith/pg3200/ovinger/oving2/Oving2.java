package no.nith.pg3200.ovinger.oving2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import no.nith.pg3200.ovinger.R;
import no.nith.pg3200.ovinger.oving2.task1.Task1;
import no.nith.pg3200.ovinger.oving2.task2.Task2;
import no.nith.pg3200.ovinger.oving2.task3.WriteNumber;
import no.nith.pg3200.ovinger.oving2.task4.Task4;

public class Oving2 extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving2);

        final Button task1 = (Button) findViewById(R.id.oving2Task1);
        final Button task2 = (Button) findViewById(R.id.oving2Task2);
        final Button task3 = (Button) findViewById(R.id.oving2Task3);
        final Button task4 = (Button) findViewById(R.id.oving2Task4);

        task1.setOnClickListener(this);
        task2.setOnClickListener(this);
        task3.setOnClickListener(this);
        task4.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.oving2Task1:
            startActivity(new Intent(this, Task1.class));
            break;
        case R.id.oving2Task2:
            startActivity(new Intent(this, Task2.class));
            break;
        case R.id.oving2Task3:
            startActivity(new Intent(this, WriteNumber.class));
            break;
        case R.id.oving2Task4:
            startActivity(new Intent(this, Task4.class));
            break;
        }
    }
}
