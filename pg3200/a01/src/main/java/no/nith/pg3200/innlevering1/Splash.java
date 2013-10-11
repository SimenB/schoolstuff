package no.nith.pg3200.innlevering1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import no.nith.pg3200.innlevering1.menus.MainMenu;
import no.nith.tic_tac_toe.LeaderboardHandler;

/**
 * The splash-screen of the application
 */
public class Splash extends Activity {
    private Context context;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        new LoadLeaderboardTask().execute();
    }

    private class LoadLeaderboardTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            setContentView(R.layout.splash);
        }

        @Override
        protected Void doInBackground(Void... params) {
            final String leaderboardFileName = getString(R.string.leaderboard_file_name);

            try {
                final FileInputStream fileInputStream = openFileInput(leaderboardFileName);
                Leaderboard.loadLeaderboardFromFile(fileInputStream);
                fileInputStream.close();
            } catch (FileNotFoundException e) {
                try {
                    final FileOutputStream fileOutputStream = openFileOutput(leaderboardFileName, MODE_APPEND);
                    fileOutputStream.write(LeaderboardHandler.getInstance().serialize().getBytes());
                    fileOutputStream.close();
                } catch (Exception ignored) {
                }
            } catch (Exception ignored) {
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(context, MainMenu.class));
            finish();
        }
    }
}
