package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.response.BusResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.repository.BusRepository;
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
public class BusService {

    BusRepository busRepository;
    BusMapper busMapper;

    public Bus createBus(BusCreationRequest request) {
        Bus bus = busMapper.toBus(request);
        return busRepository.save(bus);
    }

    public List<BusResponse> getBuses() {

        return busRepository.findAll().stream()
                .map(busMapper::toBusResponse)
                .toList();
    }

    public BusResponse getBusById(Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        return busMapper.toBusResponse(bus);
    }

    public BusResponse updateBus(Integer busId, BusUpdateRequest request) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        busMapper.updateBus(bus, request);

        return busMapper.toBusResponse(busRepository.save(bus));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBus(Integer busId) {
        busRepository.deleteById(busId);
    }
}
