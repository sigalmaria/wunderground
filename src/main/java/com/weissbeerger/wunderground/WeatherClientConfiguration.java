package com.weissbeerger.wunderground;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherClientConfiguration {
    @Value("${wunderground.forecast.10days}")
    private String forecast10days;

    @Value("${wunderground.forecast.url}")
    private String baseURL;
    @Value("${wunderground.forecast.key}")
    private String key;
    @Value("${wunderground.forecast.history}")
    private String forecastHistory;

    public String getBaseURL() {
        return baseURL;
    }

    public String getKey() {
        return key;
    }

    public String getForecast10days() {
        return forecast10days;
    }

    public String getForecastHistory() {
        return forecastHistory;
    }
}
