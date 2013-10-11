package no.nith.pg3200.ovinger.oving3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import no.nith.pg3200.ovinger.R;
import no.nith.pg3200.ovinger.oving3.task1.Task1;
import no.nith.pg3200.ovinger.oving3.task2.WordList;

public class Oving3 extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving3);

        final Button task1 = (Button) findViewById(R.id.oving3Task1);
        final Button task2 = (Button) findViewById(R.id.oving3Task2);

        task1.setOnClickListener(this);
        task2.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.oving3Task1:
            startActivity(new Intent(this, Task1.class));
            break;
        case R.id.oving3Task2:
            startActivity(new Intent(this, WordList.class));
            break;
        }
    }
}
