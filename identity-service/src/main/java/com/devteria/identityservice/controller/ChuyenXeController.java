package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.ApiResponse;
import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.ChuyenXeCreationRequest;
import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.ChuyenXeRepository;
import com.devteria.identityservice.service.BusService;
import com.devteria.identityservice.service.ChuyenXeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chuyenxe")
public class ChuyenXeController {
    @Autowired
    private ChuyenXeService chuyenXeService;

    private BusRepository busRepository;
    private ChuyenXeRepository chuyenXeRepository;
    BusService busService;

    @PostMapping
    public  ChuyenXe createChuyenXe(@RequestBody ChuyenXeCreationRequest chuyenXe) {
        return chuyenXeService.createChuyenXe(chuyenXe);
    }

    @GetMapping("/getBus")
    List<ChuyenXe> getChuyenXe(){
        return chuyenXeService.getChuyenXe();
    }

    @GetMapping("getId/{id}")
    ChuyenXe getChuyenXeById(@PathVariable Integer id) {
        return chuyenXeService.getChuyenXeById(id);
    }


    @PostMapping("post/{chuyenXeId}/{busId}")
    public ChuyenXe addBusToChuyenXe(
            @PathVariable Integer chuyenXeId, @PathVariable Integer busId) {

        ChuyenXe chuyenXe = chuyenXeService.getChuyenXeById(chuyenXeId);

        Bus bus = busService.getBusById(busId);

        bus.setChuyenXe(chuyenXe);
        busRepository.save(bus);

        return chuyenXe;
    }




















    @GetMapping("/getChuyenXe")
    ApiResponse<List<ChuyenXeResponse>> getUsers() {
        return ApiResponse.<List<ChuyenXeResponse>>builder()
                .result(chuyenXeService.getChuyenXes())
                .build();
    }





    @PostMapping("/{chuyenXeId}/bus")
    public ResponseEntity<ChuyenXe> addBusToChuyenXe(
            @PathVariable Integer chuyenXeId, @RequestBody List<Bus> buses) {

        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new RuntimeException("ChuyenXe not found"));

        for (Bus bus : buses) {
            bus.setChuyenXe(chuyenXe);
        }

        busRepository.saveAll(buses);

        return ResponseEntity.ok(chuyenXe);
    }

    @GetMapping("/{chuyenXeId}")
    public ChuyenXe getChuyenXe(Integer chuyenXeId) {
        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new RuntimeException("ChuyenXe not found"));
        return chuyenXe;
    }

//    @GetMapping("/getChuyenXe/bus")
//    ApiResponse<List<ChuyenXeResponse>> getUsers() {
//        return ApiResponse.<List<ChuyenXeResponse>>builder()
//                .result(chuyenXeService.createChuyenXeWithBus())
//                .build();
//    }



    @PostMapping("/{chuyenXeId}/addExistingBus/{busId}")
    public ResponseEntity<ChuyenXe> addExistingBusToChuyenXe(
            @PathVariable Integer chuyenXeId, @PathVariable Integer busId) {

        // Tìm ChuyenXe có sẵn
        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new RuntimeException("ChuyenXe not found"));

        // Tìm Bus có sẵn
        Bus bus = busService.getBusById(busId);

        // Gán ChuyenXe cho Bus
        bus.setChuyenXe(chuyenXe);

        // Lưu lại Bus với thông tin ChuyenXe mới
        busRepository.save(bus);

        return ResponseEntity.ok(chuyenXe);
    }


}


