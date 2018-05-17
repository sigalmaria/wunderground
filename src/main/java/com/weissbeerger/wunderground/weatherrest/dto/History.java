package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class History {
    private ArrayList<HistoryDailySummary> dailysummary;

    public ArrayList<HistoryDailySummary> getDailysummary() {
        return dailysummary;
    }

    public void setDailysummary(ArrayList<HistoryDailySummary> dailysummary) {
        this.dailysummary = dailysummary;
    }


}
