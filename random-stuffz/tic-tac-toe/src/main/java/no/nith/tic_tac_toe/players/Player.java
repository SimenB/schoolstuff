package no.nith.tic_tac_toe.players;

import java.util.List;
import no.nith.tic_tac_toe.FinishedTicTacToeGame;

public interface Player {
    String getName();

    boolean isHuman();

    int getNumberOfVictories();

    int getNumberOfGames();

    int getPercentOfVictories();

    List<FinishedTicTacToeGame> getListOfGames();

    void addToResults(final FinishedTicTacToeGame game);
}
