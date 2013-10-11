package no.nith.tic_tac_toe;

import com.google.gson.GsonBuilder;
import java.util.Calendar;
import java.util.Date;
import no.nith.tic_tac_toe.exceptions.GameNotFinishedException;
import no.nith.tic_tac_toe.players.AbstractElementAdapter;
import no.nith.tic_tac_toe.players.AbstractPlayer;
import no.nith.tic_tac_toe.players.PlayerHuman;

public class TicTacToeGame {
    public static final int BLANK_SPOT = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int UNFINISHED = 0;
    public static final int DRAW = 3;
    private static final String notImplementedMessage = "AI not implemented";
    private AbstractPlayer player1, player2;
    private int[] currentBoard;
    private int turnsTaken, winner;
    private boolean turn = true;
    private Date gameStarted, gameFinished;

    /**
     * Instantiate a new game
     *
     * @param numberOfHumanPlayers
     *         The number of human players
     * @param name1
     *         The name of player 1
     * @param name2
     *         The name of player 2
     */
    public TicTacToeGame(final int numberOfHumanPlayers, final String name1, final String name2) {
        // There is no CPU implemented, but what the hell
        switch (numberOfHumanPlayers) {
        case 0:
            this.noHumans();

            break;
        case 1:
            this.oneHuman(name1);

            break;
        case 2:
            this.twoHumans(name1, name2);

            break;
        default:
            if (numberOfHumanPlayers < 0) noHumans();
            else throw new UnsupportedOperationException("There is a maximum number of players at 2");

            break;
        }

        currentBoard = Gameboard.getCleanGameBoard();
        this.gameStarted = Calendar.getInstance().getTime();
    }

    private TicTacToeGame() {
    }

    public static TicTacToeGame deserialize(final String data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractPlayer.class, new AbstractElementAdapter());
        return gsonBuilder.create().fromJson(data, TicTacToeGame.class);
    }

    public AbstractPlayer getPlayer1() {
        return this.player1;
    }

    public AbstractPlayer getPlayer2() {
        return this.player2;
    }

    public int[] getCurrentBoard() {
        return this.currentBoard.clone();
    }

    public boolean isPlayer1Turn() {
        return this.turn;
    }

    public int getTurnsTaken() {
        return this.turnsTaken;
    }

    /**
     * Place a char on the playing-board.
     *
     * @param position
     *         The position on the board the player is trying to place it's char
     * @return Whether or not the operation was successful.
     */
    public boolean placeChar(final int position) {
        if (this.currentBoard[position] != BLANK_SPOT) return false;

        this.currentBoard[position] = this.turn ? PLAYER_1 : PLAYER_2;

        this.turnsTaken++;
        this.turn = !this.turn;

        return true;
    }

    /**
     * Check if a player has won the game.
     *
     * @return 0 if there is no winner, 1 if player 1 has won, 2 if player 2 has won and 3 if the game ended in a draw.
     */
    public int checkForVictoryOrDraw() {
        final int victor = this.checkVictory();

        if (victor != UNFINISHED) {
            this.gameFinished = Calendar.getInstance().getTime();
            this.winner = victor;
            return victor;
        }

        if (turnsTaken == 9) {
            this.gameFinished = Calendar.getInstance().getTime();
            this.winner = DRAW;
            return DRAW;
        }

        return UNFINISHED;
    }

    /**
     * Internal check if a player has won the game.
     *
     * @return 0 if there is no winner, 1 if player 1 has won and 2 if player 2 has won.
     */
    private int checkVictory() {
        if (this.currentBoard[0] != 0) {
            if (this.currentBoard[0] == this.currentBoard[1] && this.currentBoard[1] == this.currentBoard[2])
                return this.currentBoard[0];
            if (this.currentBoard[0] == this.currentBoard[4] && this.currentBoard[4] == this.currentBoard[8])
                return this.currentBoard[0];
            if (this.currentBoard[0] == this.currentBoard[3] && this.currentBoard[3] == this.currentBoard[6])
                return this.currentBoard[0];
        }

        if (this.currentBoard[1] == this.currentBoard[4] && this.currentBoard[4] == this.currentBoard[7])
            return this.currentBoard[1];

        if (this.currentBoard[2] != 0) {
            if (this.currentBoard[2] == this.currentBoard[5] && this.currentBoard[5] == this.currentBoard[8])
                return this.currentBoard[2];
            if (this.currentBoard[2] == this.currentBoard[4] && this.currentBoard[4] == this.currentBoard[6])
                return this.currentBoard[2];
        }

        if (this.currentBoard[3] == this.currentBoard[4] && this.currentBoard[4] == this.currentBoard[5])
            return this.currentBoard[3];

        if (this.currentBoard[6] == this.currentBoard[7] && this.currentBoard[7] == this.currentBoard[8])
            return this.currentBoard[6];

        return 0;
    }

    private void noHumans() {
        throw new UnsupportedOperationException(notImplementedMessage);
    }

    private void oneHuman(final String nameofHuman) {
        throw new UnsupportedOperationException(notImplementedMessage);
    }

    private void twoHumans(final String name1, final String name2) {
        this.player1 = new PlayerHuman(name1);
        this.player2 = new PlayerHuman(name2);
    }

    /**
     * Serialize the current game for saving.
     *
     * @return A JSON-representation of the current game.
     */
    public String serialize() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(AbstractPlayer.class, new AbstractElementAdapter());
        return gsonBuilder.create().toJson(this);
    }

    /**
     * @return A small representation of the finished game, useful for storing history.
     * @throws GameNotFinishedException
     *         If the game is not finished yet.
     */
    public FinishedTicTacToeGame getFinishedGame() throws GameNotFinishedException {
        if (this.gameFinished == null) {
            throw new GameNotFinishedException();
        }

        return new FinishedTicTacToeGame(this.currentBoard, this.winner, player1.getName(), player2.getName(), this.gameStarted, this.gameFinished);
    }
}
