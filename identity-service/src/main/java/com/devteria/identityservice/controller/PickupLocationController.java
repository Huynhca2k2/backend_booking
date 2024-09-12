package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.PickupLocationCreationRequest;
import com.devteria.identityservice.dto.request.PickupLocationUpdateRequest;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
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
    public PickupLocation createPickupLocation(@RequestBody PickupLocationCreationRequest request){
        return pickupLocationService.createPickupLocation(request);
    }

    @GetMapping()
    public List<PickupLocationResponse> getAllPickupLocation(){
        return pickupLocationService.getAllPickupLocations();
    }

    @GetMapping("/{id}")
    public PickupLocationResponse getPickupLocationById(@PathVariable Integer id) {
        return pickupLocationService.getPickupLocationById(id);
    }

    @DeleteMapping("/{pickupLocationId}")
    public ApiResponse<String> deletePickupLocation(@PathVariable Integer pickupLocationId) {
        pickupLocationService.deletePickupLocation(pickupLocationId);
        return ApiResponse.<String>builder().result("PickupLocation has been deleted").build();
    }

    @PutMapping("/{pickupLocationId}")
    public ApiResponse<PickupLocationResponse> updatePickupLocation(@PathVariable Integer pickupLocationId, @RequestBody PickupLocationUpdateRequest request) {
        return ApiResponse.<PickupLocationResponse>builder()
                .result(pickupLocationService.updatePickupLocation(pickupLocationId, request))
                .build();
    }

}
