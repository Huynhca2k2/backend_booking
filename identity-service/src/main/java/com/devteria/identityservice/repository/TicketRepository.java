package com.devteria.identityservice.repository;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Optional<Ticket> findById(Integer ticketId);
    boolean existsById(Integer ticketId);
}
