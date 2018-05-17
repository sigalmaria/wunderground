package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weissbeerger.wunderground.weatherrest.dto.ForecastDate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryDailySummary {


    private ForecastDate date;
    private String maxtempm;

    public ForecastDate getDate() {
        return date;
    }

    public void setDate(ForecastDate date) {
        this.date = date;
    }


    public String getMaxtempm() {
        return maxtempm;
    }

    public void setMaxtempm(String maxtempm) {
        this.maxtempm = maxtempm;
    }

}
