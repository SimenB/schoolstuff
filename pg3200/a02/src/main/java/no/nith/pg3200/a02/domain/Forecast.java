package no.nith.pg3200.a02.domain;

import org.joda.time.DateTime;

/**
 * @author Simen Bekkhus
 */
public class Forecast {
    private DateTime time;
    private int symbol;
    private double temperature;

    public Forecast(final String time, final int symbol, final double temperature) {
        this.time = new DateTime(time);
        this.symbol = symbol;
        this.temperature = temperature;
    }

    public Forecast() {
    }

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
