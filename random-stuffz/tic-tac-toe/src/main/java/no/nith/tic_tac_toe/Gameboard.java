package no.nith.tic_tac_toe;

public final class Gameboard {
    private static final int[][] WINNING_COMBINATIONS = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    private static final int[] CLEAN_GAMEBOARD = new int[]{
            0, 0, 0, 0, 0, 0, 0, 0, 0
    };
    private static int turns;
    private static boolean playerOnesTurn = true;

    private Gameboard() {
    }

    public static int[][] getWinningCombinations() {
        return WINNING_COMBINATIONS.clone();
    }

    public static int[] getCleanGameBoard() {
        return CLEAN_GAMEBOARD.clone();
    }

    public static int[] checkForWinner(final int[] currentBoard) {
        return null;
    }

    public static int getTurns() {
        return turns;
    }

    public static void incrementTurns() {
        turns++;
    }

    public static void switchTurn() {
        playerOnesTurn = !playerOnesTurn;
    }

    public static void resetGame() {
        turns = 0;
        playerOnesTurn = true;
    }
}
