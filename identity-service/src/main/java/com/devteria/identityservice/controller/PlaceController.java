package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Place;
import com.devteria.identityservice.service.AllCodeService;
import com.devteria.identityservice.service.PlaceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PlaceController {
    PlaceService placeService;
    @PostMapping()
    Place createPlace(@RequestBody Place request){
        return placeService.createPlace(request);
    }

    @GetMapping("/findAll")
    List<Place> getAllPlace(){
        return placeService.getPlace();
    }
//    @GetMapping("/{id}")
//    public Place getPlaceByID(@RequestParam Integer id) {
//        return placeService.getPlaceByID(id);
//    }


}
