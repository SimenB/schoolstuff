package no.nith.tic_tac_toe;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import no.nith.tic_tac_toe.players.AbstractElementAdapter;
import no.nith.tic_tac_toe.players.AbstractPlayer;
import no.nith.tic_tac_toe.players.PlayerHuman;

public class LeaderboardHandler {
    private static LeaderboardHandler instance = new LeaderboardHandler();
    private final Type type;
    private final GsonBuilder gsonBuilder;
    private HashMap<String, AbstractPlayer> actualLeaderboard;

    private LeaderboardHandler() {
        actualLeaderboard = new HashMap<String, AbstractPlayer>();
        type = new TypeToken<HashMap<String, AbstractPlayer>>() {
        }.getType();
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractPlayer.class, new AbstractElementAdapter());
    }

    public static LeaderboardHandler getInstance() {
        return instance;
    }

    public String serialize() {
        return gsonBuilder.create().toJson(actualLeaderboard, type);
    }

    public void fillLeaderboard(final String data) {
        // If only '{}' arrives, just return
        if (data.length() <= 2) return;

        actualLeaderboard = gsonBuilder.create().fromJson(data, type);
        actualLeaderboard.remove("Draw");
    }

    public ArrayList<AbstractPlayer> getLeaderboard() {
        final ArrayList<AbstractPlayer> arrayListRepresentation = new ArrayList<AbstractPlayer>(actualLeaderboard.values());

        Collections.sort(arrayListRepresentation);

        // Return a copy
        return new ArrayList<AbstractPlayer>(arrayListRepresentation);
    }

    public void addScoreToLeaderboard(final FinishedTicTacToeGame game) {
        final String nameOfPlayer1 = game.getPlayer1Name(),
                nameOfPlayer2 = game.getPlayer2Name();
        final AbstractPlayer absPlayer1 = actualLeaderboard.get(nameOfPlayer1),
                absPlayer2 = actualLeaderboard.get(nameOfPlayer2);
        final PlayerHuman player1, player2;

        // TODO: Some sort of check for CPU-player

        if (absPlayer1 == null) {
            player1 = new PlayerHuman(nameOfPlayer1);
            actualLeaderboard.put(nameOfPlayer1, player1);
        } else player1 = (PlayerHuman) absPlayer1;

        if (absPlayer2 == null) {
            player2 = new PlayerHuman(nameOfPlayer2);
            actualLeaderboard.put(nameOfPlayer2, player2);
        } else player2 = (PlayerHuman) absPlayer2;

        player1.addToResults(game);
        player2.addToResults(game);
    }
}
