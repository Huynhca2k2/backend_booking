package com.devteria.identityservice.service;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ThoiGian;
import com.devteria.identityservice.mapper.AllCodeMapper;
import com.devteria.identityservice.mapper.ThoiGianMapper;
import com.devteria.identityservice.repository.AllCodeRepository;
import com.devteria.identityservice.repository.ThoiGianRepository;
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
public class ThoiGianService {
    ThoiGianRepository thoiGianRepository;
    ThoiGianMapper thoiGianMapper;

    public ThoiGian createThoiGian(ThoiGian request){
        ThoiGian thoiGian = thoiGianMapper.toThoiGian((request));
        return thoiGianRepository.save(thoiGian);
    }
    public List<ThoiGian> getThoiGian(){
        return thoiGianRepository.findAll();
    }
//    public List<AllCode> getAllcodesByType(String type) {
//        return allCodeRepository.findByType(type);
//    }

}
