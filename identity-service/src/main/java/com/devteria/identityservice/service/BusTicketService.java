package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.BusTicketCreationRequest;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.BusTicket;
import com.devteria.identityservice.mapper.BusMapper;
import com.devteria.identityservice.mapper.BusTicketMapper;
import com.devteria.identityservice.repository.BusRepository;
import com.devteria.identityservice.repository.BusTicketRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class BusTicketService {
    BusTicketRepository busTicketRepository;
    BusTicketMapper busTicketMapper;

    public BusTicket createBusTicket(BusTicketCreationRequest request){
        BusTicket busTicket =  busTicketMapper.toBusTicket(request);
        return busTicketRepository.save(busTicket);
    }
    public List<BusTicket> getBusTicket(){
        return busTicketRepository.findAll();
    }

}
