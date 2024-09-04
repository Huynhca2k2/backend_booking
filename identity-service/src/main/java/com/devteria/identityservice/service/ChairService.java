package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.BusTicket;
import com.devteria.identityservice.entity.Chair;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.BusTicketRepository;
import com.devteria.identityservice.repository.ChairRepository;
import com.devteria.identityservice.repository.ChuyenXeRepository;
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
    ChuyenXeRepository chuyenXeRepository;

    BusTicketRepository busTicketRepository;
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
//        int num = 0;
//        double ticketprice = 0.0;
//        double currentPrice = 0;
        for (Chair chair : chairs) {
            if ("available".equals(chair.getStatus())) {
                chair.setStatus("disable");
//                num++;
//                currentPrice = chair.getBus().getChuyenXe().getPrice();
                reservedChairNames.add(chair.getTenGhe());  // Thêm tên ghế vào danh sách
            } else {
                throw new RuntimeException("Chair " + chair.getTenGhe() + " is not available");
            }
        }
//        ticketprice = num * currentPrice;
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


    public List<BusTicket> reserveChairsAndCreateTickets(List<Integer> chairIds, int chuyenXeId) {
        List<Chair> chairs = chairRepository.findAllById(chairIds);




        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new RuntimeException("ChuyenXe not found"));





        List<Bus> bus = chuyenXe.getBus();


        double ticketPrice = chuyenXe.getPrice() * chairs.size(); // Tính tổng giá trị vé dựa trên số ghế và giá của chuyến xe.

        List<BusTicket> busTickets = new ArrayList<>();

        for (Chair chair : chairs) {
            if ("available".equals(chair.getStatus())) {
                chair.setStatus("disable");

                // Tạo BusTicket cho mỗi ghế được đặt
                BusTicket busTicket = new BusTicket();
                busTicket.setTicketPrice(ticketPrice);
                busTicket.setChuyenXe(chuyenXe);

                busTickets.add(busTicket);
            } else {
                throw new RuntimeException("Chair " + chair.getTenGhe() + " is not available");
            }
        }

        // Lưu trạng thái ghế và BusTicket
        chairRepository.saveAll(chairs);
        busTicketRepository.saveAll(busTickets);

        return busTickets;
    }

}
