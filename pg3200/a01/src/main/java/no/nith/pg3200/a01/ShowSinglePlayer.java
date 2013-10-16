package no.nith.pg3200.a01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import no.nith.pg3200.innlevering1.R;
import no.nith.pg3200.a01.adapters.SinglePlayerAdapter;
import no.nith.tic_tac_toe.players.AbstractPlayer;
import no.nith.tic_tac_toe.players.PlayerHuman;

public class ShowSinglePlayer extends Activity {
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.single_player);

        final String playerString = getIntent().getStringExtra("player");
        final boolean isHuman = getIntent().getBooleanExtra("isHuman", true);

        final TextView textView = (TextView) findViewById(R.id.singlePlayerListName);

        final AbstractPlayer player = isHuman ? PlayerHuman.deserialize(playerString) : null; // TODO: CPU
        final String name = player.getName();

        textView.setText(String.format(getString(R.string.result_history, name)));
        SinglePlayerAdapter adapter = new SinglePlayerAdapter(name, player.getListOfGames(), this);

        final ListView listView = (ListView) findViewById(R.id.singlePlayerListView);

        context = this;

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*final Intent intent = new Intent(context, .class);

                intent.putExtra("isHuman", absPlayer.isHuman());

                startActivity(intent);*/
            }
        });
    }
}
