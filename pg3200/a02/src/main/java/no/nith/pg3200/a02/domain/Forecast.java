package no.nith.pg3200.a02.domain;

import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class Forecast {
    private DateTime time;
    private int symbol;
    private double temperature;

    public DateTime getTime() {
        return time;
    }

    public int getSymbol() {
        return symbol;
    }

    public double getTemperature() {
        return temperature;
    }
}
