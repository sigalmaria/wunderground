package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleForecast {
    private ArrayList<ForecastDay> forecastday;

    public ArrayList<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }


}
