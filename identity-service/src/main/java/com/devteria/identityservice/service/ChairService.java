package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Chair;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.ChairRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class ChairService {
    BusRepository busRepository;
    BusMapper busMapper;

    ChairRepository chairRepository;

    public Bus createBus(BusCreationRequest request){
        Bus bus = busMapper.toBus((request));
        return busRepository.save(bus);
    }


//    public void reserveChairs(List<Integer> chairIds) {
//        List<Chair> chairs = chairRepository.findAllById(chairIds);
//
//        for (Chair chair : chairs) {
//            if ("available".equals(chair.getStatus())) {
//                chair.setStatus("disable");
//            } else {
//                throw new RuntimeException("Chair " + chair.getTenGhe() + " is not available");
//            }
//        }
//        chairRepository.saveAll(chairs);
//    }


    public List<String> reserveChairs(List<Integer> chairIds) {
        List<Chair> chairs = chairRepository.findAllById(chairIds);
        List<String> reservedChairNames = new ArrayList<>();

        for (Chair chair : chairs) {
            if ("available".equals(chair.getStatus())) {
                chair.setStatus("disable");
                reservedChairNames.add(chair.getTenGhe());  // Thêm tên ghế vào danh sách
            } else {
                throw new RuntimeException("Chair " + chair.getTenGhe() + " is not available");
            }
        }
        chairRepository.saveAll(chairs);
        return reservedChairNames;
    }
    public void createChairs(int numberOfChairs, Integer busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        for (int i = 1; i <= numberOfChairs; i++) {
            Chair chair = new Chair();
            chair.setTenGhe("A" + i);
            chair.setStatus("available");
            chair.setBus(bus);  // Gán bus_id cho ghế
            chairRepository.save(chair);
        }
    }
}
