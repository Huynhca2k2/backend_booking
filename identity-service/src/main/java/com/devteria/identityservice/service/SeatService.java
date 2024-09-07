package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.SeatCreationMoreRequest;
import com.devteria.identityservice.dto.request.SeatCreationRequest;
import com.devteria.identityservice.dto.response.SeatResponse;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Seat;
import com.devteria.identityservice.entity.SeatStatus;
import com.devteria.identityservice.entity.Trip;
import com.devteria.identityservice.mapper.SeatMapper;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.SeatRepository;
import com.devteria.identityservice.repository.TripRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class SeatService {
    SeatRepository seatRepository;
    BusRepository busRepository;
    TripRepository tripRepository;
    SeatMapper seatMapper;

    public List<Seat> createSeats(SeatCreationMoreRequest request) {
        List<Seat> seats = new ArrayList<>();
        Optional<Bus> busOpt = busRepository.findById(request.getBusId());
        Optional<Trip> tripOpt = tripRepository.findById(request.getTripId());

        if (busOpt.isEmpty()) {
            throw new RuntimeException("id Bus not found");
        }
        if (tripOpt.isEmpty()) {
            throw new RuntimeException("id Trip not found");
        }

        Bus bus = busOpt.get();
        Trip trip = tripOpt.get();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < request.getSeatCapacity(); i++) {
            Seat seat = new Seat();
            seat.setSeatCode("A" + i);
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setCreationDate(currentDate);
            seat.setBus(bus);
            seat.setTrip(trip);
            seats.add(seat);
        }

        return seatRepository.saveAll(seats);
    }

    public Seat createSeat(SeatCreationRequest request){
        Seat seat = seatMapper.toSeat((request));
        return seatRepository.save(seat);
    }

    public List<SeatResponse> getSeats() {
        log.info("In method get seat");
        return seatRepository.findAll().stream().map(seatMapper::toSeatResponse).toList();
    }

    public Seat getSeatById(Integer seatId){
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("seat with ID not found"));
    }


}
