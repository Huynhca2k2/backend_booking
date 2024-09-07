package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.SeatCreationMoreRequest;
import com.devteria.identityservice.dto.request.SeatCreationRequest;
import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.response.SeatResponse;
import com.devteria.identityservice.entity.Seat;
import com.devteria.identityservice.entity.Ticket;
import com.devteria.identityservice.entity.Seat;
import com.devteria.identityservice.service.SeatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SeatController {
    SeatService seatService;

    @PostMapping()
    Seat createSeat(@RequestBody SeatCreationRequest request){
        return seatService.createSeat(request);
    }

    @PostMapping("/batch")
    public List<Seat> createSeats(@RequestBody SeatCreationMoreRequest request) {
        return seatService.createSeats(request);
    }

    @GetMapping()
    List<SeatResponse> getSeats(){
        return seatService.getSeats();
    }

    @GetMapping("/{id}")
    Seat getSeatById(@PathVariable Integer id) {
        return seatService.getSeatById(id);
    }
}
