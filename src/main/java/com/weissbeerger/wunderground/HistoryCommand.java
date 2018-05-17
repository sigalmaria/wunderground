package com.weissbeerger.wunderground;


public class HistoryCommand extends WunderGroundWeatherCommand {


    public HistoryCommand(String state, String city, Integer days) {
        setState(state);
        setCity(city);
        setDays(days);

    }


}
