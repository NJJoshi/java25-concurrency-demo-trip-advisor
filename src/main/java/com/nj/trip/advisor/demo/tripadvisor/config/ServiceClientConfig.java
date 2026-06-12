package com.nj.trip.advisor.demo.tripadvisor.config;

import com.nj.trip.advisor.demo.tripadvisor.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

@Configuration
public class ServiceClientConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceClientConfig.class);

    @Value("${spring.thread.virtual.enabled}")
    private boolean isVirtualThreadEnabled;

    @Bean
    public AccommodationServiceClient  accommodationServiceClient(@Value("${accommodation.service.url}") String accommodationServiceUrl){
        LOG.info("Creating AccommodationServiceClient");
        return new AccommodationServiceClient(buildRestClient(accommodationServiceUrl));
    }


    @Bean
    public EventServiceClient eventServiceClient(@Value("${event.service.url}") String baseUrl){
        return new EventServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(@Value("${weather.service.url}") String baseUrl){
        return new WeatherServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public TransportationServiceClient transportationServiceClient(@Value("${transportation.service.url}") String baseUrl){
        return new TransportationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public LocalRecommendationServiceClient recommendationServiceClient(@Value("${local-recommendation.service.url}") String baseUrl){
        return new LocalRecommendationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(@Value("${flight-search.service.url}") String baseUrl){
        return new FlightSearchServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightReservationServiceClient reservationServiceClient(@Value("${flight-reservation.service.url}") String baseUrl){
        return new FlightReservationServiceClient(buildRestClient(baseUrl));
    }

    private RestClient buildRestClient(String baseUrl){
        LOG.info("base url: {}", baseUrl);
        var builder = RestClient.builder().baseUrl(baseUrl);
        if(isVirtualThreadEnabled){
            builder = builder.requestFactory(new JdkClientHttpRequestFactory(
                    HttpClient.newBuilder().executor(Executors.newVirtualThreadPerTaskExecutor()).build()
            ));
        }
        return builder.build();
    }
}
