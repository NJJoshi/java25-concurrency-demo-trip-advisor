package com.nj.trip.advisor.demo.tripadvisor.controller;

import com.nj.trip.advisor.demo.tripadvisor.dto.FlightReservationResponse;
import com.nj.trip.advisor.demo.tripadvisor.dto.TripPlan;
import com.nj.trip.advisor.demo.tripadvisor.dto.TripReservationRequest;
import com.nj.trip.advisor.demo.tripadvisor.service.TripPlanService;
import com.nj.trip.advisor.demo.tripadvisor.service.TripReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
public class TripController {

    private final TripPlanService planService;
    private final TripReservationService reservationService;

    public TripController(TripPlanService planService, TripReservationService reservationService) {
        this.planService = planService;
        this.reservationService = reservationService;
    }

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode){
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request){
        return this.reservationService.reserve(request);
    }

}
