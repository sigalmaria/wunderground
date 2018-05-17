package com.weissbeerger.wunderground;





public  class WunderGroundWeatherCommand {

private String city;
private String state;
private Integer days;




    public String getCity() {
        return city;
}

    public void setCity(String city) {
        this.city = city;
}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }


}
