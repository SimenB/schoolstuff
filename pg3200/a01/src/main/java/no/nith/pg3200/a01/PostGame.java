package no.nith.pg3200.a01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import no.nith.pg3200.a01.R;
import no.nith.pg3200.a01.menus.MainMenu;
import no.nith.tic_tac_toe.FinishedTicTacToeGame;
import no.nith.tic_tac_toe.LeaderboardHandler;

public class PostGame extends Activity implements View.OnClickListener {
    private final String doneSaving = "doneSaving";
    private Context context;
    private FinishedTicTacToeGame prevGame;
    private Button btnRematch;
    private Button btnLeaderboard;
    private boolean dataSaved;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.post_game);

        context = this;
        prevGame = FinishedTicTacToeGame.deserialize(getIntent().getStringExtra("prevGame"));
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(getString(R.string.saved_game_indic)).apply();

        TextView txtWinner = (TextView) findViewById(R.id.txtWinner);
        btnRematch = (Button) findViewById(R.id.btnRematch);
        btnLeaderboard = (Button) findViewById(R.id.btnLeaderboard);

        btnRematch.setOnClickListener(this);
        btnLeaderboard.setOnClickListener(this);

        final String nameOfWinner = prevGame.getNameOfWinner();
        if ("Draw".equals(nameOfWinner)) txtWinner.setText(getString(R.string.draw_text));
        else txtWinner.setText(String.format(getString(R.string.winner_text), nameOfWinner));

        // Don't save the result again
        if (savedInstanceState == null || !savedInstanceState.getBoolean(doneSaving))
            new SaveLeaderboardTask().execute();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(doneSaving, dataSaved);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btnRematch:
            startActivity(new Intent(this, InGame.class));
            finish();

            break;
        case R.id.btnLeaderboard:
            startActivity(new Intent(context, Leaderboard.class));
            finish();

            break;
        }
    }

    // Maybe load up an image if the finished game? (It looks like this happens if the screen is NOT rotated)
    @Override
    public void onBackPressed() {
        startActivity(new Intent(context, MainMenu.class));

        finish();
    }

    private class SaveLeaderboardTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            // TODO: Evaluate if this is needed (it's really quick)
            Toast.makeText(context, getString(R.string.saving_leaderboard), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            LeaderboardHandler leaderboard = LeaderboardHandler.getInstance();

            leaderboard.addScoreToLeaderboard(prevGame);
            try {
                final String leaderboardFileName = getString(R.string.leaderboard_file_name);

                FileOutputStream fos = openFileOutput(leaderboardFileName, Context.MODE_PRIVATE);
                fos.write(leaderboard.serialize().getBytes());
                fos.close();
            } catch (Exception ignored) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            btnRematch.setEnabled(true);
            btnLeaderboard.setEnabled(true);

            dataSaved = true;
        }
    }
}
