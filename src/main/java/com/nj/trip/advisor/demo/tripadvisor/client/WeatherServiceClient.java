package com.nj.trip.advisor.demo.tripadvisor.client;

import com.nj.trip.advisor.demo.tripadvisor.dto.Weather;
import org.springframework.web.client.RestClient;

public class WeatherServiceClient {
    private final RestClient client;

    public WeatherServiceClient(RestClient client) {
        this.client = client;
    }

    public Weather getWeather(String airportCode) {
        return this.client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(Weather.class);
    }
}
