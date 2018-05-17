package com.weissbeerger.wunderground.weatherrest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDate {
    private String epoch;
    @JsonAlias({"tz_long", "tzname"})
    private String timeZone;
    @JsonAlias({"day", "mday"})
    private String day;
    @JsonAlias({"month", "mon"})
    private String month;
    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getISODate() {
        LocalDate localDate = null;
        if (getEpoch() != null) {

            localDate = Instant.ofEpochMilli(Long.parseLong(getEpoch()) * 1000).atZone(ZoneId.of(getTimeZone())).toLocalDate();


        } else {
            localDate = LocalDate.of(Integer.valueOf(year),
                    Integer.valueOf(month), Integer.valueOf(day));
        }


        String formatted = localDate.format(DateTimeFormatter.ISO_DATE.ISO_DATE);
        return formatted;
    }
}
