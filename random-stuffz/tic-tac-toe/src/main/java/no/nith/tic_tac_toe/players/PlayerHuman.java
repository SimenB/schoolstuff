package no.nith.tic_tac_toe.players;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import no.nith.tic_tac_toe.FinishedTicTacToeGame;

public class PlayerHuman extends AbstractPlayer {
    public PlayerHuman(final String name) {
        this.name = name;
        this.results = new ArrayList<FinishedTicTacToeGame>();

        this.numberOfVictories = 0;
    }

    public static PlayerHuman deserialize(final String data) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractPlayer.class, new AbstractElementAdapter());

        return gsonBuilder.create().fromJson(data, PlayerHuman.class);
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    public String serialize() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractPlayer.class, new AbstractElementAdapter());

        return gsonBuilder.create().toJson(this);
    }
}
