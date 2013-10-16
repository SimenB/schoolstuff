package no.nith.pg3200.exercises.oving3.task1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import no.nith.pg3200.ovinger.R;

/**
 * Created by Simen on 05.09.13.
 */
public class Task1 extends Activity {
    private TextView textView;
    private Button btnGenerate;
    private Random random;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving3task1);

        btnGenerate = (Button) findViewById(R.id.exercise3task1btnGenerate);
        textView = (TextView) findViewById(R.id.exercise3task1textView);

        random = new Random();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String[] words = getResources().getStringArray(R.array.list_of_words);

                textView.setText(words[random.nextInt(words.length)]);
            }
        });
    }
}
