package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDay {
    private String conditions;
    private HighTempature high;
    private ForecastDate date;

    public ForecastDate getDate() {
        return date;
    }

    public void setDate(ForecastDate date) {
        this.date = date;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public HighTempature getHigh() {
        return high;
    }

    public void setHigh(HighTempature high) {
        this.high = high;
    }


}
