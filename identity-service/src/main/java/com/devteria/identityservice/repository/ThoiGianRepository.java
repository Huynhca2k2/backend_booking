package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ThoiGian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ThoiGianRepository extends JpaRepository<ThoiGian,Integer> {
//    List<AllCode> findByType(String type);
}
