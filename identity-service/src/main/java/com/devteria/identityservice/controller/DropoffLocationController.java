package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.service.DropoffLocationService;
import com.devteria.identityservice.service.DropoffLocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dropoff-locations")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class DropoffLocationController {
    DropoffLocationService dropoffLocationService;

    @PostMapping()
    DropoffLocation createDropoffLocation(@RequestBody DropoffLocation request){
        return dropoffLocationService.createDropoffLocation(request);
    }

    @GetMapping("/getAll")
    List<DropoffLocation> getAllDropoffLocation(){
        return dropoffLocationService.getAllDropoffLocation();
    }


}
