package com.devteria.identityservice.service;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Place;
import com.devteria.identityservice.mapper.AllCodeMapper;
import com.devteria.identityservice.mapper.PlaceMapper;
import com.devteria.identityservice.repository.AllCodeRepository;
import com.devteria.identityservice.repository.PlaceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class PlaceService {
    PlaceRepository placeRepository;
    PlaceMapper placeMapper;

    public Place createPlace(Place request){
        Place place =placeMapper.toPlace((request));
        return placeRepository.save(place);
    }
    public List<Place> getPlace(){
        return placeRepository.findAll();
    }
//    public Place getPlaceByID(Integer id) {
//        return placeRepository.findByID(id);
//    }

}
