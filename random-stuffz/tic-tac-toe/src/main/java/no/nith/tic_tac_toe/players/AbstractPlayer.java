package no.nith.tic_tac_toe.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.nith.tic_tac_toe.FinishedTicTacToeGame;

public abstract class AbstractPlayer implements Player, Comparable<AbstractPlayer> {
    protected String name;
    protected int numberOfVictories;
    protected List<FinishedTicTacToeGame> results;

    @Override
    public ArrayList<FinishedTicTacToeGame> getListOfGames() {
        return new ArrayList<FinishedTicTacToeGame>(this.results);
    }

    @Override
    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    @Override
    public int getNumberOfGames() {
        return this.results.size();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPercentOfVictories() {
        return (int) Math.round(getNumberOfVictories() * 100.0 / getNumberOfGames());
    }

    @Override
    public int compareTo(final AbstractPlayer another) {
        final int BEFORE = -1, EQUAL = 0, AFTER = 1;

        if (this == another) return EQUAL;

        if (this.getNumberOfVictories() > another.getNumberOfVictories()) return BEFORE;
        if (this.getNumberOfVictories() < another.getNumberOfVictories()) return AFTER;

        if (this.getNumberOfGames() > another.getNumberOfGames()) return BEFORE;
        if (this.getNumberOfGames() < another.getNumberOfGames()) return AFTER;

        if (this.isHuman() && !another.isHuman()) return BEFORE;
        if (!this.isHuman() && another.isHuman()) return AFTER;

        // Hopefully this won't return 0. If so, something went wrong (No support for the same names)
        return this.getName().compareTo(another.getName());
    }

    @Override
    public void addToResults(final FinishedTicTacToeGame game) {
        this.results.add(game);

        Collections.sort(this.results);

        if (this.name.equals(game.getNameOfWinner())) this.numberOfVictories++;
    }
}
