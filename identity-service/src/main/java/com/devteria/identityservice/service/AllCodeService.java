package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.mapper.AllCodeMapper;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.repository.AllCodeRepository;
import com.devteria.identityservice.repository.BusRepository;
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
public class AllCodeService {
    AllCodeRepository allCodeRepository;
    AllCodeMapper allCodeMapper;

    public AllCode createAllCode(AllCode request){
        AllCode allCode = allCodeMapper.toAllCode((request));
        return allCodeRepository.save(allCode);
    }
    public List<AllCode> getAllCode(){
        return allCodeRepository.findAll();
    }
    public List<AllCode> getAllcodesByType(String type) {
        return allCodeRepository.findByType(type);
    }

}
