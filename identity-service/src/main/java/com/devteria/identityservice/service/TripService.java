package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.TripCreationRequest;
import com.devteria.identityservice.dto.request.TripFilterRequest;
import com.devteria.identityservice.dto.request.TripUpdateRequest;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.Seat;
import com.devteria.identityservice.entity.Trip;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.TripMapper;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.PickupLocationRepository;
import com.devteria.identityservice.repository.DropoffLocationRepository;
import com.devteria.identityservice.repository.SeatRepository;
import com.devteria.identityservice.repository.TripRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class TripService {

    TripRepository tripRepository;
    PickupLocationRepository pickupLocationRepository;
    DropoffLocationRepository dropoffLocationRepository;
    BusRepository busRepository;
    SeatRepository seatRepository;
    TripMapper tripMapper;

    public Trip createTrip(TripCreationRequest request) {
        List<PickupLocation> pickupLocations = pickupLocationRepository.findAllById(request.getPickupLocationIds());
        List<DropoffLocation> dropoffLocations = dropoffLocationRepository.findAllById(request.getDropoffLocationIds());
        List<Bus> buses = busRepository.findAllById(request.getBusIds());

        if (pickupLocations.size() != request.getPickupLocationIds().size()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        if (dropoffLocations.size() != request.getDropoffLocationIds().size()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        if (buses.size() != request.getBusIds().size()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
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
        log.info("In method get trips");
        return tripRepository.findAll().stream()
                .map(tripMapper::toTripResponse)
                .collect(Collectors.toList());
    }

    public TripResponse getTripById(Integer tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        trip.getBuses().forEach(bus -> {
            List<Seat> filteredSeats = seatRepository.findByTripIdAndBusId(tripId, bus.getId());
            bus.setSeats(filteredSeats);
        });

        return tripMapper.toTripResponse(trip);
    }

    public TripResponse addBusesToTrip(Integer tripId, List<Integer> busIds) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        List<Bus> busesToAdd = busRepository.findAllById(busIds);

        if (busesToAdd.size() != busIds.size()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        trip.getBuses().addAll(busesToAdd);
        tripRepository.save(trip);

        return tripMapper.toTripResponse(trip);
    }

    public List<TripResponse> filterTrips(TripFilterRequest filterRequest) {
        List<Trip> allTrips = tripRepository.findAll();

        List<Trip> filteredTrips = allTrips.stream()
                .filter(trip -> trip.getDepartureLocation().equalsIgnoreCase(filterRequest.getDepartureLocation()))
                .filter(trip -> trip.getArrivalLocation().equalsIgnoreCase(filterRequest.getArrivalLocation()))
                .filter(trip -> trip.getCreationDate().isEqual(filterRequest.getCreationDate()))
                .collect(Collectors.toList());

        filteredTrips.forEach(trip -> {
            trip.getBuses().forEach(bus -> {
                List<Seat> filteredSeats = seatRepository.findByTripIdAndBusId(trip.getId(), bus.getId());
                bus.setSeats(filteredSeats);
            });
        });

        return filteredTrips.stream()
                .map(tripMapper::toTripResponse)
                .collect(Collectors.toList());
    }

    public TripResponse updateTrip(Integer tripId, TripUpdateRequest request) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        tripMapper.updateTrip(trip, request);
        return tripMapper.toTripResponse(tripRepository.save(trip));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTrip(Integer tripId) {
        tripRepository.deleteById(tripId);
    }
}
