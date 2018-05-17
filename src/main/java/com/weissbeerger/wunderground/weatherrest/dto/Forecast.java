package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Forecast {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private SimpleForecast simpleforecast;

    public SimpleForecast getSimpleforecast() {
        return simpleforecast;
    }

    public void setSimpleforecast(SimpleForecast simpleforecast) {
        this.simpleforecast = simpleforecast;
    }


}
