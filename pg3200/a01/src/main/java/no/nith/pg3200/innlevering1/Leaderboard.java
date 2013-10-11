package no.nith.pg3200.innlevering1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import no.nith.pg3200.innlevering1.adapters.LeaderboardAdapter;
import no.nith.tic_tac_toe.LeaderboardHandler;
import no.nith.tic_tac_toe.players.AbstractPlayer;
import no.nith.tic_tac_toe.players.PlayerHuman;

public class Leaderboard extends Activity {
    private ListView leaderboardList;
    private LeaderboardAdapter adapter;
    private Context context;

    public static void loadLeaderboardFromFile(final FileInputStream fileInputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                total.append(line);
            }
            bufferedReader.close();

            LeaderboardHandler.getInstance().fillLeaderboard(total.toString());
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.leaderboard);

        context = this;

        leaderboardList = (ListView) findViewById(R.id.leaderboardListView);
        final ArrayList<AbstractPlayer> leaderboard = LeaderboardHandler.getInstance().getLeaderboard();

        adapter = new LeaderboardAdapter(leaderboard, this);

        leaderboardList.setAdapter(adapter);

        leaderboardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(context, ShowSinglePlayer.class);

                AbstractPlayer absPlayer = (AbstractPlayer) leaderboardList.getItemAtPosition(position);

                if (absPlayer.isHuman()) {
                    PlayerHuman player = (PlayerHuman) absPlayer;
                    intent.putExtra("player", player.serialize());
                } else {
                    // TODO: Do the same for CPU
                }

                intent.putExtra("isHuman", absPlayer.isHuman());

                startActivity(intent);
            }
        });
    }
}
