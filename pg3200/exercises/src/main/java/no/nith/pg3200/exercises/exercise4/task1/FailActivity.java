package no.nith.pg3200.exercises.exercise4.task1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class FailActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String NPE = null;

        try {
            NPE.toString();
        } catch (Exception e) {
            Log.i("MINE!!!", "Oh noes, a NPE");
            Toast.makeText(this, "It crashed D:", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
