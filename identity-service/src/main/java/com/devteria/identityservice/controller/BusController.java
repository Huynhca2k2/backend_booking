package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.response.BusResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.service.BusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BusController {
    BusService busService;
    @PostMapping()
    Bus createBus(@RequestBody BusCreationRequest request){
        return busService.createBus(request);
    }

    @GetMapping("/getBus")
    List<Bus> getBus(){
        return busService.getBus();
    }

    @GetMapping("/{id}")
    Bus getBusById(@PathVariable Integer id) {
       return busService.getBusById(id);
    }

}
