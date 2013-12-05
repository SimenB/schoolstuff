package no.nith.beans;

/**
 * @author Simen Bekkhus
 */
public interface Counter {
    long getStartTime();

    void start();

    void reset();

    long getElapsedTime();
}
