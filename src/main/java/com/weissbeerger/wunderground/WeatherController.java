package com.weissbeerger.wunderground;



import com.weissbeerger.wunderground.weatherrest.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;


@Controller
public class WeatherController {

    // a synchronized queue to store the requests
    private ArrayBlockingQueue<WunderGroundWeatherCommand> requests = new ArrayBlockingQueue<>(250);
    // the client to consume rest request from Wunderground API
    private WeatherClient client;

    @Autowired
    public WeatherController(WeatherClient client) {
        this.client = client;
    }

    @GetMapping("/forecast")
    @ResponseBody
    public ResponseBodyEmitter processRequest(@RequestParam String action,
                                              @RequestParam(required = false) String city,
                                              @RequestParam(required = false) Integer days,
                                              @RequestParam(required = false) String state) {
        //create an emitter object to stream the results;
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(100000L);
        try {
            if (action.equals("getForecast")) {
                WunderGroundWeatherCommand request = null;
                if (city == null || state == null || days == null || city.equals("") ||
                        days.equals("") ||
                        state.equals("")) {
                    emitter.send("please provide state, days, city ");
                    emitter.complete();
                } else {

                    if (days < 0) { // if days are negative when we need to invoke history request
                        request = new HistoryCommand(state, city, days);
                    } else {//otherwise invoke forecast command
                        request = new ForecastCommand(state, city, days);
                    }
                    //push to the queue
                    requests.add(request);
                    emitter.send("Pushed to the queue");
                    emitter.complete();
                }
            } else if (action.equals("run")) {
                // if the action is run then execute all requests from the queue in
                //another thread
                Thread worker = new WeatherExecutor(client, requests, emitter);
                worker.start();
            } else {
                emitter.send("undefined action");
                emitter.complete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                emitter.send("Error Occured " + e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return emitter;

    }


}
