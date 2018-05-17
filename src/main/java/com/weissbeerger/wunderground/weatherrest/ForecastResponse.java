package com.weissbeerger.wunderground.weatherrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weissbeerger.wunderground.weatherrest.dto.Forecast;


public class ForecastResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private Forecast forecast;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }


}
