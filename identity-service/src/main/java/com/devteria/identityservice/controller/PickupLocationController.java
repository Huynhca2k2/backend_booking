package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.service.PickupLocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pickup-locations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PickupLocationController {
    PickupLocationService pickupLocationService;

    @PostMapping()
    PickupLocation createPickupLocation(@RequestBody PickupLocation request){
        return pickupLocationService.createPlace(request);
    }

    @GetMapping("/getAll")
    List<PickupLocationResponse> getAllPickupLocation(){
        return pickupLocationService.getAllPickupLocation();
    }

    @GetMapping("/{id}")
    PickupLocation getPickupLocationById(@PathVariable Integer id) {
        return pickupLocationService.getPickupLocationById(id);
    }



}
