package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.PickupLocationCreationRequest;
import com.devteria.identityservice.dto.request.PickupLocationUpdateRequest;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.PickupLocationMapper;
import com.devteria.identityservice.repository.PickupLocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class PickupLocationService {
    PickupLocationRepository pickupLocationRepository;
    PickupLocationMapper pickupLocationMapper;

    public PickupLocation createPickupLocation(PickupLocationCreationRequest request) {
        PickupLocation pickupLocation = pickupLocationMapper.toPickupLocation(request);
        return pickupLocationRepository.save(pickupLocation);
    }

    public List<PickupLocationResponse> getAllPickupLocations() {
        log.info("In method get all pickup locations");
        return pickupLocationRepository.findAll()
                .stream()
                .map(pickupLocationMapper::toPickupLocationResponse)
                .toList();
    }

    public PickupLocationResponse getPickupLocationById(Integer pickupLocationId) {
        PickupLocation pickupLocation = pickupLocationRepository.findById(pickupLocationId)
                .orElseThrow(() -> new RuntimeException("PickupLocation with ID " + pickupLocationId + " not found"));

        return pickupLocationMapper.toPickupLocationResponse(pickupLocation);
    }

    public PickupLocationResponse updatePickupLocation(Integer pickupLocationId, PickupLocationUpdateRequest request) {
        PickupLocation pickupLocation = pickupLocationRepository.findById(pickupLocationId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        pickupLocationMapper.updatePickupLocation(pickupLocation, request);

        PickupLocation updatedPickupLocation = pickupLocationRepository.save(pickupLocation);

        return pickupLocationMapper.toPickupLocationResponse(updatedPickupLocation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletePickupLocation(Integer pickupLocationId) {
        pickupLocationRepository.deleteById(pickupLocationId);
    }
}
