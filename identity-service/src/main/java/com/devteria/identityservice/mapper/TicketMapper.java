package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.request.TicketUpdateRequest;
import com.devteria.identityservice.dto.response.DropoffLocationResponse;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketCreationRequest request);

//    @Mapping(source = "ticket.pickupLocation", target = "pickupLocation")
//    @Mapping(source = "ticket.dropoffLocation", target = "dropoffLocation")
//    @Mapping(source = "ticket.busSelectedId", target = "busSelectedId")
    TicketResponse toTicketResponse(Ticket ticket);

    void updateTicket(@MappingTarget Ticket ticket, TicketUpdateRequest request);


}
