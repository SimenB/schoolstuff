package no.nith.pg3200.exercises.oving2.task3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import no.nith.pg3200.ovinger.R;

/**
 * Created by Simen on 06.09.13.
 */
public class WriteNumber extends Activity {
    private EditText input;
    private Button btnCall, btnShow;
    private Context context;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oving2write_number);

        context = this;

        input = (EditText) findViewById(R.id.nrInput);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent i = new Intent(context, ShowNumber.class);
                i.putExtra("number", input.getText().toString());
                startActivity(i);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:" + input.getText().toString()));
                startActivity(i);
            }
        });
    }
}
