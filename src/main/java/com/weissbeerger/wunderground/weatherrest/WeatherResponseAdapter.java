package com.weissbeerger.wunderground.weatherrest;


import com.weissbeerger.wunderground.ForecastCommand;
import com.weissbeerger.wunderground.HistoryCommand;
import com.weissbeerger.wunderground.weatherrest.dto.ForecastDay;
import com.weissbeerger.wunderground.weatherrest.dto.History;
import com.weissbeerger.wunderground.weatherrest.dto.HistoryDailySummary;
import com.weissbeerger.wunderground.weatherrest.dto.SimpleForecast;


import java.util.ArrayList;
import java.util.List;

/**
 * responses that are returned from Wunderground API should be adapted to our API
 */
public class WeatherResponseAdapter {

    public static List<WeatherResponse> convert(ForecastCommand request, ForecastResponse response) {
        ArrayList<WeatherResponse> responses = new ArrayList<>();
        SimpleForecast simpleForecast = response.getForecast().getSimpleforecast();
        if (simpleForecast != null) {
            List<ForecastDay> forecastDays = simpleForecast.getForecastday();
            for (int i = 0; i < request.getDays(); i++) {
                ForecastDay forecastDay = forecastDays.get(i);
                WeatherResponse runWeatherResponse = new WeatherResponse();
                runWeatherResponse.setMaxTemprature(forecastDay.getHigh().getCelsius());
                runWeatherResponse.setDate(forecastDay.getDate().getISODate());
                runWeatherResponse.setWeatherDescription(forecastDay.getConditions());
                responses.add(runWeatherResponse);

            }
        }

        return responses;
    }

    public static List<WeatherResponse> convert(HistoryCommand request, HistoryResponse response) {
        ArrayList<WeatherResponse> responses = new ArrayList<>();
        History history = response.getHistory();
        if (history != null) {
            for (HistoryDailySummary historyDailySummary : history.getDailysummary()) {
                WeatherResponse runWeatherResponse = new WeatherResponse();
                runWeatherResponse.setMaxTemprature(historyDailySummary.getMaxtempm());
                runWeatherResponse.setDate(historyDailySummary.getDate().getISODate());
                // there is no condition field for history
                responses.add(runWeatherResponse);
            }
        }

        return responses;
    }


}
