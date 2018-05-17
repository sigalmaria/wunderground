package com.weissbeerger.wunderground.weatherrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.weissbeerger.wunderground.weatherrest.dto.History;


@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryResponse {


    private History history;

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

}
