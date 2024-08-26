package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {
//    boolean existsByBusName(String busname);
//
//    Optional<User> findByBusName(String busname);

    boolean existsById(Integer busId);
    Optional<Bus> findById(Integer busId);
}
