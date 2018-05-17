package com.weissbeerger.wunderground.weatherrest;


import com.weissbeerger.wunderground.ForecastCommand;
import com.weissbeerger.wunderground.HistoryCommand;
import com.weissbeerger.wunderground.WeatherClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeatherClient {
    /**
     * Client to execute the requests to wundergournd
     */
    private final RestTemplate restTemplate;
    private final WeatherClientConfiguration configuration;

    @Autowired
    public WeatherClient(RestTemplateBuilder builder,
                         WeatherClientConfiguration configuration) {
        this.restTemplate = builder.build();
        this.configuration = configuration;
    }

    /**
     * request forecast for specific days
     *
     * @param forecastRequest
     * @return
     */
    public List<WeatherResponse> getForecast(ForecastCommand forecastRequest) {
        List<WeatherResponse> responses = new ArrayList<>();
        String url = configuration.getBaseURL() + "/" + configuration.getKey() + "/" + configuration.getForecast10days() +
                "/q/" + forecastRequest.getState() + "/" + forecastRequest.getCity() + ".json";
        ForecastResponse response = restTemplate.getForObject(url, ForecastResponse.class);
        // the wundeground api returns forecast for 10 days, we need to process results and filter them
        responses = WeatherResponseAdapter.convert(forecastRequest, response);
        return responses;
    }

    public List<WeatherResponse> getHistory(HistoryCommand historyRequest) {
        List<WeatherResponse> responses;
        String url = configuration.getBaseURL() + "/" + configuration.getKey() + "/" + configuration.getForecastHistory();
        String date = getPastDay(historyRequest.getDays());
        url += "_" + date;
        url += "/q/" + historyRequest.getState() + "/" + historyRequest.getCity() + ".json";
        HistoryResponse response = restTemplate.getForObject(url, HistoryResponse.class);
        //we need to process results to comply our api
        responses = WeatherResponseAdapter.convert(historyRequest, response);
        return responses;
    }

    private String getPastDay(final int days) {

        Instant now = Instant.now(); //current date
        Instant before = now.minus(Duration.ofDays(days * -1));
        Date dateBefore = Date.from(before);
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        final String dateFromated = format.format(dateBefore);
        return dateFromated;
    }
}
