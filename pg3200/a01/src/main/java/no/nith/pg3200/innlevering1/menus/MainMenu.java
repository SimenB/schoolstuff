package no.nith.pg3200.innlevering1.menus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import no.nith.pg3200.innlevering1.InGame;
import no.nith.pg3200.innlevering1.Leaderboard;
import no.nith.pg3200.innlevering1.R;

public class MainMenu extends Activity implements View.OnClickListener {
    SharedPreferences preferences;
    String savedGame;
    boolean savedGameExists;
    private Context context;
    private Button btnConGame;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        initMembersAndListeners();
    }

    private void initMembersAndListeners() {
        context = this;

        final Button bntNewGame = (Button) findViewById(R.id.btnNewGame);
        btnConGame = (Button) findViewById(R.id.btnConGame);
        final Button btnLeader = (Button) findViewById(R.id.btnLeader);
        final Button btnSettings = (Button) findViewById(R.id.btnSettings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        savedGame = preferences.getString(getString(R.string.saved_game_indic), null);
        savedGameExists = savedGame != null && savedGame.length() > 0;

        btnConGame.setEnabled(savedGameExists);

        bntNewGame.setOnClickListener(this);
        btnConGame.setOnClickListener(this);
        btnLeader.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
        case R.id.btnNewGame:
            if (this.savedGameExists) {
                askUserToLoadGame();
            } else startActivity(new Intent(context, InGame.class));
            break;
        case R.id.btnConGame:
            final Intent intent = new Intent(context, InGame.class);

            intent.putExtra(getString(R.string.saved_game_indic), savedGame);

            startActivity(intent);
            break;
        case R.id.btnLeader:
            startActivity(new Intent(context, Leaderboard.class));
            break;
        case R.id.btnSettings:
            startActivity(new Intent(context, Settings.class));
            break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        savedGame = preferences.getString(getString(R.string.saved_game_indic), null);
        savedGameExists = savedGame != null && savedGame.length() > 0;

        btnConGame.setEnabled(savedGameExists);
    }

    private void askUserToLoadGame() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.load_game))
                .setMessage(getString(R.string.saved_game_tip))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(getString(R.string.saved_game_indic)).apply();

                        startActivity(new Intent(context, InGame.class));
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                }).show();
    }
}
