package no.nith.pg3200.exercises.exercise2.task1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import no.nith.pg3200.exercises.R;

/**
 * Created by Simen on 05.09.13.
 */
public class Task1 extends Activity {
    private TextView textView;
    private EditText editText;
    private Button btnOk;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2task1);

        textView = (TextView) findViewById(R.id.task1textView);
        editText = (EditText) findViewById(R.id.task1editText);
        btnOk = (Button) findViewById(R.id.task1btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                textView.setText(editText.getText());
                editText.setText("");
            }
        });
    }
}
