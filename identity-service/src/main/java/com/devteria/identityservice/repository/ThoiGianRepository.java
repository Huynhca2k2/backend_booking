package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.ThoiGian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ThoiGianRepository extends JpaRepository<ThoiGian,Integer> {
//    List<AllCode> findByType(String type);

    boolean existsById(Integer thoigianId);
    Optional<ThoiGian> findById(Integer thoigianId);
}
