package no.nith.pg3200.ovinger.oving3.task2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import no.nith.pg3200.ovinger.R;

/**
 * Created by Simen on 12.09.13.
 */
public class ViewWord extends Activity {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving3view);

        final String word = getIntent().getExtras().getString("word");

        final TextView textField = (TextView) findViewById(R.id.exercise3task2text);

        textField.setText(word);
    }
}
