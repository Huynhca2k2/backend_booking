package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Chair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChairRepository extends JpaRepository<Chair,Integer> {
//    Arrays findAllById(Set<Long> allCodeIds);
//    boolean existsByBusName(String busname);
//
//    Optional<User> findByBusName(String busname);

//    List<AllCode> findByType(String type);
}
