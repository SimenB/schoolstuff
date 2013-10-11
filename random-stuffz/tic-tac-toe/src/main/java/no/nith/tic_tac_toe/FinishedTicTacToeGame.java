package no.nith.tic_tac_toe;

import com.google.gson.Gson;
import java.util.Date;

public class FinishedTicTacToeGame implements Comparable<FinishedTicTacToeGame> {
    private int[] gameboard;
    private String player1Name, player2Name, winner, loser;
    private Date gameStartedDate, gameOverDate;

    public FinishedTicTacToeGame(final int[] gameboard, final int playerWon, final String name1, final String name2, final Date gameStartedDate, final Date gameOverDate) {
        if (playerWon == 1) {
            this.winner = name1;
            this.loser = name2;
        } else if (playerWon == 2) {
            this.winner = name2;
            this.loser = name1;
        } else {
            this.winner = "Draw";
            this.loser = "Draw";
        }

        this.gameboard = gameboard;
        this.player1Name = name1;
        this.player2Name = name2;
        this.gameStartedDate = gameStartedDate;
        this.gameOverDate = gameOverDate;
    }

    private FinishedTicTacToeGame() {
    }

    public static FinishedTicTacToeGame deserialize(final String json) {
        return new Gson().fromJson(json, FinishedTicTacToeGame.class);
    }

    public String getNameOfWinner() {
        return this.winner;
    }

    public String getNameOfLoser() {
        return this.loser;
    }

    public Date getGameOverDate() {
        return this.gameOverDate;
    }

    public long getLengthOfGameInMillis() {
        return this.gameOverDate.getTime() - this.gameStartedDate.getTime();
    }

    public String getPlayer1Name() {
        return this.player1Name;
    }

    public String getPlayer2Name() {
        return this.player2Name;
    }

    public int[] getGameboard() {
        return this.gameboard.clone();
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    @Override
    public int compareTo(FinishedTicTacToeGame another) {
        final int BEFORE = -1, EQUAL = 0, AFTER = 1;

        if (this == another) return EQUAL;

        // We want the newest at the top of the list
        return another.getGameOverDate().compareTo(this.getGameOverDate());
    }
}
