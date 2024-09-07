package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.Seat;
import com.devteria.identityservice.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PickupLocationRepository extends JpaRepository<PickupLocation,Integer> {
    boolean existsById(Integer pickupLocationId);

    Optional<PickupLocation> findById(Integer pickupLocationId);

    List<PickupLocation> findAllByIdIn(List<Integer> ids);
}
