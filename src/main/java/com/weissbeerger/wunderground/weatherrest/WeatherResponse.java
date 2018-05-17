package com.weissbeerger.wunderground.weatherrest;


public class WeatherResponse {
    private String date;
    private String weatherDescription;
    private String maxTemprature;

    public WeatherResponse() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getMaxTemprature() {
        return maxTemprature;
    }

    public void setMaxTemprature(String maxTemprature) {
        this.maxTemprature = maxTemprature;
    }

    public String toString() {
        return "date: " + getDate() + ", max temp: " + getMaxTemprature() + " description: " + getWeatherDescription();
    }


}
