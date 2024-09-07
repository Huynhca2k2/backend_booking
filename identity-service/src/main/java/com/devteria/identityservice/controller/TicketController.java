package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Ticket;
import com.devteria.identityservice.service.TicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TicketController {
    TicketService ticketService;

    @PostMapping()
    Ticket createTicket(@RequestBody TicketCreationRequest request){
        return ticketService.createTicket(request);
    }

    @GetMapping()
    List<TicketResponse> getAllTicket(){
        return ticketService.getAllTicket();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable int id) {
        TicketResponse ticketResponse = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketResponse);
    }

}
