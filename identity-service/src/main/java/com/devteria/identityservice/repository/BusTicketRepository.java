package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.BusTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusTicketRepository extends JpaRepository<BusTicket,Integer> {
//    boolean existsByBusName(String busname);
//
//    Optional<User> findByBusName(String busname);
}
