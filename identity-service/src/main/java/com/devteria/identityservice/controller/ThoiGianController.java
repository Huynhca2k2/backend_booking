package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.dto.response.ThoiGianResponse;
import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ThoiGian;
import com.devteria.identityservice.service.AllCodeService;
import com.devteria.identityservice.service.ThoiGianService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Time")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ThoiGianController {
    ThoiGianService thoiGianService;
    @PostMapping()
    ThoiGian createThoiGian(@RequestBody ThoiGian request){
        return thoiGianService.createThoiGian(request);
    }

    @GetMapping("/findAll")
    List<ThoiGianResponse> getAllTime(){
        return thoiGianService.getThoiGian();
    }
//    @GetMapping("/type")
//    public List<AllCode> getAllcodes(@RequestParam String type) {
//        return allCodeService.getAllcodesByType(type);
//    }

    @PutMapping("/{chuyenXeId}/{thoiGianId}")
    public ResponseEntity<ThoiGian> addTimeToChuyenXe(
            @PathVariable Integer chuyenXeId,
            @PathVariable Integer thoiGianId) {
        ThoiGian thoiGian = thoiGianService.addTimeToChuyenXe(chuyenXeId, thoiGianId);
        return ResponseEntity.ok(thoiGian);
    }


}
