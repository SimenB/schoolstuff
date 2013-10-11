package no.nith.tic_tac_toe.exceptions;

public class GameNotFinishedException extends Exception {

    public GameNotFinishedException() {
        super("The game isn't finished yet.");
    }
}
