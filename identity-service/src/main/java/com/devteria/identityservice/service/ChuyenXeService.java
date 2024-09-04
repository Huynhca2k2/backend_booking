package com.devteria.identityservice.service;


import com.devteria.identityservice.constant.PredefinedRole;
import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.ChuyenXeCreationRequest;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.*;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.mapper.ChuyenXeMapper;
import com.devteria.identityservice.repository.AllCodeRepository;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.ChuyenXeRepository;
import com.devteria.identityservice.repository.ThoiGianRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class ChuyenXeService {
    ChuyenXeRepository chuyenXeRepository;
    ChuyenXeMapper chuyenXeMapper;
    BusRepository busRepository;
    ThoiGianRepository thoiGianRepository;

    AllCodeRepository allCodeRepository;

    public ChuyenXe createChuyenXe(ChuyenXeCreationRequest request){
        ChuyenXe chuyenXe = chuyenXeMapper.toChuyenXe(request);
        return chuyenXeRepository.save(chuyenXe);
    }

    public List<ChuyenXe> getChuyenXe(){
        return chuyenXeRepository.findAll();
    }


    public List<ChuyenXeResponse> getChuyenXes() {
        log.info("In method get ChuyenXe");
        return chuyenXeRepository.findAll().stream().map(chuyenXeMapper::toChuyenXeResponse).toList();
    }


    public ChuyenXe getChuyenXeById(Integer chuyenXeId){
        return chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new RuntimeException("ChuyenXe with ID not found"));
    }

    public ChuyenXeResponse createChuyenXeWithBus(ChuyenXeCreationRequest request,BusCreationRequest busId) {
        if (chuyenXeRepository.existsById(request.getId())) throw new AppException(ErrorCode.USER_EXISTED);

        ChuyenXe chuyenXe = chuyenXeMapper.toChuyenXe(request);

        HashSet<Bus> buses = new HashSet<>();
        busRepository.findById(busId.getId()).ifPresent(buses::add);

        chuyenXe.setBus((List<Bus>) buses);

        return chuyenXeMapper.toChuyenXeResponse(chuyenXeRepository.save(chuyenXe));
    }
//    @Transactional
//    public ChuyenXe addAllCodesToChuyenXe(Integer chuyenXeId, Set<Long> allCodeIds) {
//        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId).orElseThrow();
//        // Sử dụng vòng lặp để thêm AllCode vào Set
//        // Sử dụng vòng lặp for để thêm AllCode vào Set
//        Set<AllCode> allCodes = new HashSet<>();
//
//        for (AllCode allCode : iterable) {
//            allCodes.add(allCode);
//        }
//
//        chuyenXe.setAllCodes(allCodes);
//
//        // Gán giá trị từ AllCode vào thuộc tính departure và destination
//        for (AllCode allCode : allCodes) {
//            if ("departure".equals(allCode.getKeyMap())) {
//                chuyenXe.setDeparture(allCode.getValue());
//            } else if ("destination".equals(allCode.getKeyMap())) {
//                chuyenXe.setDestination(allCode.getValue());
//            }
//        }
//
//        return chuyenXeRepository.save(chuyenXe);
//    }




    @Transactional
    public ChuyenXeResponse addBusToChuyenXe(Integer chuyenXeId, Integer busId) {
        // Tìm kiếm ChuyenXe theo ID
        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        // Tìm kiếm Bus theo ID
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        if (bus.getChuyenXe() != null) {
            throw new AppException(ErrorCode.valueOf("Bus da dc gan"));
        }
        // Thêm Bus vào danh sách Bus của ChuyenXe
//        chuyenXe.setBus((List<Bus>) bus);
        chuyenXe.addBus(bus);
        //Gán chuyenXe cho bus
//        bus.setChuyenXe(chuyenXe);

        // Lưu lại ChuyenXe sau khi đã thêm Bus
        ChuyenXe updatedChuyenXe = chuyenXeRepository.save(chuyenXe);

        // Trả về phản hồi sau khi thêm thành công
        return chuyenXeMapper.toChuyenXeResponse(updatedChuyenXe);
    }

//    @Transactional
//    public ChuyenXeResponse addTimeToChuyenXe(Integer chuyenXeId, Integer timeId) {
//        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
//                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
//        ThoiGian thoiGian = thoiGianRepository.findById(timeId)
//                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
//        if (thoiGian.getChuyenXe() != null) {
//            throw new AppException(ErrorCode.valueOf("Time da dc gan"));
//        }
//        chuyenXe.addTime(thoiGian);
//        ChuyenXe updatedChuyenXe = chuyenXeRepository.save(chuyenXe);
//        // Trả về phản hồi sau khi thêm thành công
//        return chuyenXeMapper.toChuyenXeResponse(updatedChuyenXe);
//    }
}
