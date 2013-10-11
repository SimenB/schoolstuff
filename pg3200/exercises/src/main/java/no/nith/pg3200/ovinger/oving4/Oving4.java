package no.nith.pg3200.ovinger.oving4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import no.nith.pg3200.ovinger.R;
import no.nith.pg3200.ovinger.oving2.task3.WriteNumber;
import no.nith.pg3200.ovinger.oving4.task1.FailActivity;
import no.nith.pg3200.ovinger.oving4.task2.ContactViewer;

public class Oving4 extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving4);

        final Button task1 = (Button) findViewById(R.id.oving4Task1);
        final Button task2 = (Button) findViewById(R.id.oving4Task2);
        final Button task3 = (Button) findViewById(R.id.oving4Task3);

        task1.setOnClickListener(this);
        task2.setOnClickListener(this);
        task3.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.oving4Task1:
            startActivity(new Intent(this, FailActivity.class));
            break;
        case R.id.oving4Task2:
            startActivity(new Intent(this, ContactViewer.class));
            break;
        case R.id.oving4Task3:
            startActivity(new Intent(this, WriteNumber.class));
            break;
        }
    }
}
