package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.DropoffLocationCreationRequest;
import com.devteria.identityservice.dto.request.DropoffLocationUpdateRequest;
import com.devteria.identityservice.dto.response.DropoffLocationResponse;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.DropoffLocationMapper;
import com.devteria.identityservice.repository.DropoffLocationRepository;
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
public class DropoffLocationService {
    DropoffLocationRepository dropoffLocationRepository;
    DropoffLocationMapper dropoffLocationMapper;

    public DropoffLocation createDropoffLocation(DropoffLocationCreationRequest request) {
        DropoffLocation dropoffLocation = dropoffLocationMapper.toDropoffLocation(request);
        return dropoffLocationRepository.save(dropoffLocation);
    }

    public List<DropoffLocation> getAllDropoffLocation() {
        return dropoffLocationRepository.findAll();
    }

    public DropoffLocationResponse updateDropoffLocation(Integer dropoffLocationId, DropoffLocationUpdateRequest request) {
        DropoffLocation dropoffLocation = dropoffLocationRepository.findById(dropoffLocationId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        dropoffLocationMapper.updateDropoffLocation(dropoffLocation, request);

        return dropoffLocationMapper.toDropoffLocationResponse(dropoffLocationRepository.save(dropoffLocation));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDropoffLocation(Integer dropoffLocationId) {
        dropoffLocationRepository.deleteById(dropoffLocationId);
    }
}
