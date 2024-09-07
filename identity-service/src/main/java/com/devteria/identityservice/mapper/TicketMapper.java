package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.response.DropoffLocationResponse;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.Ticket;
import com.devteria.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Ticket toTicket(TicketCreationRequest request);

//    @Mapping(source = "ticket.pickupLocation", target = "pickupLocation")
//    @Mapping(source = "ticket.dropoffLocation", target = "dropoffLocation")
    TicketResponse toTicketResponse(Ticket ticket);


}
