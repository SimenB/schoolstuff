package no.nith.pg3200.innlevering1.menus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import no.nith.pg3200.innlevering1.R;

public class Settings extends Activity implements View.OnClickListener {
    private Context context;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        context = this;

        Button btnChangeName = (Button) findViewById(R.id.btnChangeNames);
        Button btnPurgeLeaderboard = (Button) findViewById(R.id.btnPurgeLeader);
        Button btnReset = (Button) findViewById(R.id.btnReset);

        btnChangeName.setOnClickListener(this);
        btnPurgeLeaderboard.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.btnChangeNames:
            this.changeNames();
            break;
        case R.id.btnPurgeLeader:
            this.resetLeaderboard();
            break;
        case R.id.btnReset:
            this.resetAll();
            break;
        }
    }

    private void changeNames() {
        final View view = getLayoutInflater().inflate(R.layout.change_name_dialog, null);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText editPlayer1 = (EditText) view.findViewById(R.id.editFieldPlayer1);
        final EditText editPlayer2 = (EditText) view.findViewById(R.id.editFieldPlayer2);

        editPlayer1.setText(preferences.getString(getString(R.string.pref_key_name1), getString(R.string.default_name1)));
        editPlayer2.setText(preferences.getString(getString(R.string.pref_key_name2), getString(R.string.default_name2)));

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.name_setter_title))
                .setMessage(getString(R.string.name_setter_message))
                .setView(view)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value1 = editPlayer1.getText().toString(),
                                value2 = editPlayer2.getText().toString();

                        final SharedPreferences.Editor editor = preferences.edit();

                        // TODO: If the names are the same, shit's gonna hit the fan later on
                        editor.putString(getString(R.string.pref_key_name1), value1);
                        editor.putString(getString(R.string.pref_key_name2), value2);

                        editor.apply();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }

    private void resetLeaderboard() {
    }

    private void resetAll() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.name_setter_title))
                .setMessage(getString(R.string.name_setter_message))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();

                        editor.putString(getString(R.string.pref_key_name1), getString(R.string.default_name1));
                        editor.putString(getString(R.string.pref_key_name2), getString(R.string.default_name2));

                        editor.apply();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }
}
