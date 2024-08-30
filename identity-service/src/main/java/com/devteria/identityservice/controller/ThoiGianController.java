package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ThoiGian;
import com.devteria.identityservice.service.AllCodeService;
import com.devteria.identityservice.service.ThoiGianService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
    List<ThoiGian> getAllTime(){
        return thoiGianService.getThoiGian();
    }
//    @GetMapping("/type")
//    public List<AllCode> getAllcodes(@RequestParam String type) {
//        return allCodeService.getAllcodesByType(type);
//    }


}
