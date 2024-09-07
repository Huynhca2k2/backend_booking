package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DropoffLocationRepository extends JpaRepository<DropoffLocation,Integer> {
    boolean existsById(Integer dropoffLocationId);

    Optional<DropoffLocation> findById(Integer dropoffLocationId);
    List<DropoffLocation> findAllByIdIn(List<Integer> ids);
}
