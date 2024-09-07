package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PickupLocationMapper {
    PickupLocation toPickupLocation(PickupLocation request);
    PickupLocationResponse toPickupLocationResponse(PickupLocation pickupLocation);

}
