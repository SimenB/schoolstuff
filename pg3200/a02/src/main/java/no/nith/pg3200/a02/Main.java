package no.nith.pg3200.a02;

import android.app.Activity;
import android.os.Bundle;
import no.nith.pg3200.a02.utils.LoadJson;

public class Main extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new LoadJson(59.394458, 10.593885).execute();
    }
}
