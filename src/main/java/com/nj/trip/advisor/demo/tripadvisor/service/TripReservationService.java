package com.nj.trip.advisor.demo.tripadvisor.service;

import com.nj.trip.advisor.demo.tripadvisor.client.FlightReservationServiceClient;
import com.nj.trip.advisor.demo.tripadvisor.client.FlightSearchServiceClient;
import com.nj.trip.advisor.demo.tripadvisor.dto.Flight;
import com.nj.trip.advisor.demo.tripadvisor.dto.FlightReservationRequest;
import com.nj.trip.advisor.demo.tripadvisor.dto.FlightReservationResponse;
import com.nj.trip.advisor.demo.tripadvisor.dto.TripReservationRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class TripReservationService {
    private final FlightSearchServiceClient  flightSearchServiceClient;
    private final FlightReservationServiceClient  flightReservationServiceClient;

    public TripReservationService(FlightSearchServiceClient flightSearchServiceClient,
                                  FlightReservationServiceClient flightReservationServiceClient) {
        this.flightSearchServiceClient = flightSearchServiceClient;
        this.flightReservationServiceClient = flightReservationServiceClient;
    }

    public FlightReservationResponse reserve(TripReservationRequest request) {
        var flights = this.flightSearchServiceClient.getFlights(request.departure(), request.arrival());
        var bestDeal = flights.stream().min(Comparator.comparing(Flight::price));
        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("No Flight Found"));
        var reservationRequest = new FlightReservationRequest(request.departure(), request.arrival(), flight.flightNumber(), request.date());
        return this.flightReservationServiceClient.reserve(reservationRequest);
    }
}
