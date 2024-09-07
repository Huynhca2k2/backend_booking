package com.devteria.identityservice.service;


import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.mapper.DropoffLocationMapper;
import com.devteria.identityservice.mapper.PickupLocationMapper;
import com.devteria.identityservice.repository.DropoffLocationRepository;
import com.devteria.identityservice.repository.PickupLocationRepository;
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
public class DropoffLocationService {
    DropoffLocationRepository dropoffLocationRepository;
    DropoffLocationMapper dropoffLocationMapper;

    public DropoffLocation createDropoffLocation(DropoffLocation request){
        DropoffLocation dropoffLocation = dropoffLocationMapper.toDropoffLocation(request);
        return dropoffLocationRepository.save(dropoffLocation);
    }
    public List<DropoffLocation> getAllDropoffLocation(){
        return dropoffLocationRepository.findAll();
    }


}
