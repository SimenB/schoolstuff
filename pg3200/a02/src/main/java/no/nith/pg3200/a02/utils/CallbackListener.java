package no.nith.pg3200.a02.utils;

import no.nith.pg3200.a02.domain.WeatherData;

/**
 * @author Simen Bekkhus
 */
public interface CallbackListener {
    public void addWeatherDataToList(final WeatherData weatherData);
}
