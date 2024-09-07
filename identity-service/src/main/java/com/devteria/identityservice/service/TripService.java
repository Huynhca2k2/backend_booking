package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.request.TripCreationRequest;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.entity.*;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.TicketMapper;
import com.devteria.identityservice.mapper.TripMapper;
import com.devteria.identityservice.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PickupLocationRepository pickupLocationRepository;

    @Autowired
    private DropoffLocationRepository dropoffLocationRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TripMapper tripMapper;

    public Trip createTrip(TripCreationRequest request) {

        List<PickupLocation> pickupLocations = pickupLocationRepository.findAllById(request.getPickupLocationIds());
        List<DropoffLocation> dropoffLocations = dropoffLocationRepository.findAllById(request.getDropoffLocationIds());
        List<Bus> buses = busRepository.findAllById(request.getBusIds());

        if (pickupLocations.size() != request.getPickupLocationIds().size()) {
            throw new AppException(ErrorCode.valueOf("pickup_location_not_found"));
        }
        if (dropoffLocations.size() != request.getDropoffLocationIds().size()) {
            throw new AppException(ErrorCode.valueOf("dropoff_location_not_found"));
        }
        if (buses.size() != request.getBusIds().size()) {
            throw new AppException(ErrorCode.valueOf("bus_not_found"));
        }

        Trip trip = Trip.builder()
                .departureLocation(request.getDepartureLocation())
                .arrivalLocation(request.getArrivalLocation())
                .distance(request.getDistance())
                .travelTime(request.getTravelTime())
                .creationDate(request.getCreationDate())
                .departureTime(request.getDepartureTime())
                .pickupLocations(pickupLocations)
                .dropoffLocations(dropoffLocations)
                .buses(buses)
                .build();

        return tripRepository.save(trip);
    }

    public List<TripResponse> getTrips() {
        log.info("In method get trip");
        return tripRepository.findAll().stream().map(tripMapper::toTripResponse).toList();
    }

    public TripResponse getTripById(Integer tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip with id not found "));

        trip.getBuses().forEach(bus -> {
            List<Seat> filteredSeats = seatRepository.findByTripIdAndBusId(tripId, bus.getId());
            bus.setSeats(filteredSeats);
        });

        return  tripMapper.toTripResponse(trip);
    }

    public TripResponse addBusesToTrip(Integer tripId, List<Integer> busIds) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip with id not found"));

        List<Bus> busesToAdd = busRepository.findAllById(busIds);

        if (busesToAdd.size() != busIds.size()) {
            throw new AppException(ErrorCode.valueOf("bus_not_found"));
        }

        trip.getBuses().addAll(busesToAdd);

        tripRepository.save(trip);

        return tripMapper.toTripResponse(trip);
    }

}
