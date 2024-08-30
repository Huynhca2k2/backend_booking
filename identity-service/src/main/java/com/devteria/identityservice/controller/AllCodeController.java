package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.service.AllCodeService;
import com.devteria.identityservice.service.BusService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/allCodes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AllCodeController {
    AllCodeService allCodeService;
    @PostMapping()
    AllCode createAllCode(@RequestBody AllCode request){
        return allCodeService.createAllCode(request);
    }

    @GetMapping("/findAll")
    List<AllCode> getAllCode(){
        return allCodeService.getAllCode();
    }
    @GetMapping("/type")
    public List<AllCode> getAllcodes(@RequestParam String type) {
        return allCodeService.getAllcodesByType(type);
    }


}
