package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.*;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Ticket;
import com.devteria.identityservice.entity.Trip;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.TripRepository;
import com.devteria.identityservice.service.BusService;
import com.devteria.identityservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping()
    Trip createTrip(@RequestBody TripCreationRequest request){
        return tripService.createTrip(request);
    }

    @GetMapping()
    List<TripResponse> getAllTrip(){
        return tripService.getTrips();
    }

    @GetMapping("/{id}")
    TripResponse getTripById(@PathVariable Integer id) {
        return tripService.getTripById(id);
    }

    @PutMapping("/{tripId}/add-buses")
    public TripResponse addBusesToTrip(@PathVariable Integer tripId, @RequestBody TripAddBusRequest request) {
        return tripService.addBusesToTrip(tripId, request.getBusIds());
    }

    @PostMapping("/filter")
    public List<TripResponse> filterTrips(@RequestBody TripFilterRequest filterRequest) {
        return tripService.filterTrips(filterRequest);
    }

    @DeleteMapping("/{tripId}")
    public ApiResponse<String> deleteTrip(@PathVariable Integer tripId) {
        tripService.deleteTrip(tripId);
        return ApiResponse.<String>builder().result("Trip has been deleted").build();
    }

    @PutMapping("/{tripId}")
    public ApiResponse<TripResponse> updateTrip(@PathVariable Integer tripId, @RequestBody TripUpdateRequest request) {
        return ApiResponse.<TripResponse>builder()
                .result(tripService.updateTrip(tripId, request))
                .build();
    }

}



