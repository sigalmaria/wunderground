package com.weissbeerger.wunderground;


import com.weissbeerger.wunderground.weatherrest.WeatherClient;
import com.weissbeerger.wunderground.weatherrest.WeatherResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;


/**
 * Worker to execute all weather requests
 */
public class WeatherExecutor extends Thread {
    private ArrayBlockingQueue<WunderGroundWeatherCommand> requests;
    private WeatherClient client;
    private ResponseBodyEmitter emitter;

    public WeatherExecutor(WeatherClient client,
                           ArrayBlockingQueue<WunderGroundWeatherCommand> requests,
                           ResponseBodyEmitter emitter) {
        this.requests = requests;
        this.client = client;
        this.emitter = emitter;
    }

    public void run() {
        System.out.println("Started executing requests");
        try {
            while (!requests.isEmpty()) {
                WunderGroundWeatherCommand request = requests.poll();
                List<WeatherResponse> responses;

                if (request instanceof ForecastCommand) {
                    responses = client.getForecast((ForecastCommand) request);
                } else {
                    responses = client.getHistory((HistoryCommand) request);
                }
                for (WeatherResponse response : responses) {
                    emitter.send(response.toString() + "\n");
                }
            }
        } catch (Exception e) {
            try {
                emitter.send(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        emitter.complete();
        System.out.println("All requests processed");
    }


}
