package com.nj.trip.advisor.demo.tripadvisor.client;

import com.nj.trip.advisor.demo.tripadvisor.dto.FlightReservationRequest;
import com.nj.trip.advisor.demo.tripadvisor.dto.FlightReservationResponse;
import org.springframework.web.client.RestClient;

public class FlightReservationServiceClient {
    private final RestClient client;

    public FlightReservationServiceClient(RestClient client) {
        this.client = client;
    }

    public FlightReservationResponse reserve(FlightReservationRequest reservationRequest) {
        return this.client.post()
                            .body(reservationRequest)
                            .retrieve()
                            .body(FlightReservationResponse.class);
    }
}
