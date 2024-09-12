package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.response.BusResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.service.BusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BusController {
    BusService busService;

    @PostMapping()
    public Bus createBus(@RequestBody BusCreationRequest request) {
        return busService.createBus(request);
    }

    @GetMapping()
    public List<BusResponse> getAllBuses() {
        return busService.getBuses();
    }

    @GetMapping("/{busId}")
    public BusResponse getBusById(@PathVariable Integer busId) {
        return busService.getBusById(busId);
    }

    @DeleteMapping("/{busId}")
    public ApiResponse<String> deleteBus(@PathVariable Integer busId) {
        busService.deleteBus(busId);
        return ApiResponse.<String>builder().result("Bus has been deleted").build();
    }

    @PutMapping("/{busId}")
    public ApiResponse<BusResponse> updateBus(@PathVariable Integer busId, @RequestBody BusUpdateRequest request) {
        return ApiResponse.<BusResponse>builder()
                .result(busService.updateBus(busId, request))
                .build();
    }
}
