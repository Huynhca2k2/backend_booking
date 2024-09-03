package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.service.AllCodeService;
import com.devteria.identityservice.service.ChairService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chairs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChairController {
    ChairService chairService;

    @PostMapping("/create")
    public ResponseEntity<String> createChairs(@RequestParam int numberOfChairs, @RequestParam Integer busId) {
        chairService.createChairs(numberOfChairs, busId);
        return ResponseEntity.ok(numberOfChairs + " chairs created successfully and associated with Bus ID " + busId);
    }

}
