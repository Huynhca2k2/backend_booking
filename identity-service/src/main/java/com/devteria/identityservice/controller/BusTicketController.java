package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.BusTicketCreationRequest;
import com.devteria.identityservice.dto.response.BusTicketResponse;
import com.devteria.identityservice.entity.BusTicket;
import com.devteria.identityservice.service.BusTicketService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/busTicket")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BusTicketController {
    BusTicketService busTicketService;
//    @PostMapping()
//    BusTicket createBusTicket(@RequestBody BusTicketCreationRequest request){
//        return busTicketService.createBusTicket(request);
//    }


    @PostMapping("/create")
    public ResponseEntity<BusTicketResponse> createTicket(@RequestBody BusTicketCreationRequest request) throws Exception {
        BusTicketResponse response = busTicketService.createTicket(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    List<BusTicket> getBusTicket(){
        return busTicketService.getBusTicket();
    }

}
