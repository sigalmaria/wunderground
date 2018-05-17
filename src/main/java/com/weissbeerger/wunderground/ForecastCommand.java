package com.weissbeerger.wunderground;


public class ForecastCommand extends WunderGroundWeatherCommand {


    public ForecastCommand(String state, String city, Integer days) {
        setState(state);
        setCity(city);
        setDays(days);

    }
}
