package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.DropoffLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BusRepository extends JpaRepository<Bus,Integer> {

    boolean existsById(Integer busId);
    Optional<Bus> findById(Integer busId);
    List<Bus> findAllByIdIn(List<Integer> ids);
}
