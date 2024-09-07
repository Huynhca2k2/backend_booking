package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.PickupLocationMapper;
import com.devteria.identityservice.repository.PickupLocationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class PickupLocationService {
    PickupLocationRepository pickupLocationRepository;
    PickupLocationMapper pickupLocationMapper;

    public PickupLocation createPlace(PickupLocation request){
        PickupLocation place = pickupLocationMapper.toPickupLocation((request));
        return pickupLocationRepository.save(place);
    }
    
    public List<PickupLocationResponse> getAllPickupLocation() {
        log.info("In method get pickupLocation");
        return pickupLocationRepository.findAll().stream().map(pickupLocationMapper::toPickupLocationResponse).toList();
    }

    public PickupLocation getPickupLocationById(Integer pickupLocationid){
        return pickupLocationRepository.findById(pickupLocationid)
                .orElseThrow(() -> new RuntimeException("PickupLocation with ID not found"));
    }

}
