package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.DropoffLocationCreationRequest;
import com.devteria.identityservice.dto.request.DropoffLocationUpdateRequest;
import com.devteria.identityservice.dto.response.DropoffLocationResponse;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.service.DropoffLocationService;
import com.devteria.identityservice.service.DropoffLocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dropoff-locations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DropoffLocationController {
    DropoffLocationService dropoffLocationService;

    @PostMapping()
    DropoffLocation createDropoffLocation(@RequestBody DropoffLocationCreationRequest request){
        return dropoffLocationService.createDropoffLocation(request);
    }

    @GetMapping()
    List<DropoffLocation> getAllDropoffLocation(){
        return dropoffLocationService.getAllDropoffLocation();
    }

    @DeleteMapping("/{dropoffLocationId}")
    ApiResponse<String> deleteDropoffLocation(@PathVariable Integer dropoffLocationId) {
        dropoffLocationService.deleteDropoffLocation(dropoffLocationId);
        return ApiResponse.<String>builder().result("DropoffLocation has been deleted").build();
    }

    @PutMapping("/{dropoffLocationId}")
    ApiResponse<DropoffLocationResponse> updateDropoffLocation(@PathVariable Integer dropoffLocationId, @RequestBody DropoffLocationUpdateRequest request) {
        return ApiResponse.<DropoffLocationResponse>builder()
                .result(dropoffLocationService.updateDropoffLocation(dropoffLocationId, request))
                .build();
    }


}
