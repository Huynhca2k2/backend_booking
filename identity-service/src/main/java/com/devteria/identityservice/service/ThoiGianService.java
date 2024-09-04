package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.entity.ThoiGian;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.AllCodeMapper;
import com.devteria.identityservice.mapper.ThoiGianMapper;
import com.devteria.identityservice.repository.AllCodeRepository;
import com.devteria.identityservice.repository.ChuyenXeRepository;
import com.devteria.identityservice.repository.ThoiGianRepository;
import jakarta.transaction.Transactional;
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
    ChuyenXeRepository chuyenXeRepository;

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


    @Transactional
    public ThoiGian addTimeToChuyenXe(Integer chuyenXeId, Integer timeId) {
        ChuyenXe chuyenXe = chuyenXeRepository.findById(chuyenXeId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        ThoiGian thoiGian = thoiGianRepository.findById(timeId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
//        if (thoiGian.getChuyenXe() != null) {
//            throw new AppException(ErrorCode.valueOf("Time da dc gan"));
//        }
        thoiGian.addChuyenXe(chuyenXe);
        ThoiGian updated = thoiGianRepository.save(thoiGian);
        // Trả về phản hồi sau khi thêm thành công
        return thoiGianMapper.toThoiGian(updated);
    }

}
