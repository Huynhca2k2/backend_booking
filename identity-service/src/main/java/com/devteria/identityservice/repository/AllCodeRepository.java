package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Set;


@Repository
public interface AllCodeRepository extends JpaRepository<AllCode,Integer> {
//    Arrays findAllById(Set<Long> allCodeIds);
//    boolean existsByBusName(String busname);
//
//    Optional<User> findByBusName(String busname);
}
