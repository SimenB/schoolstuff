package no.nith.pg3200.exercises.exercise2.task3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import no.nith.pg3200.exercises.R;

/**
 * Created by Simen on 06.09.13.
 */
public class ShowNumber extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2show_number);

        final TextView textField = (TextView) findViewById(R.id.txtShowNumber);

        final String number = getIntent().getExtras().getString("number");

        textField.setText(number);
    }
}
