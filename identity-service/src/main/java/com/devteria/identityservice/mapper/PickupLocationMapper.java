package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.request.DropoffLocationCreationRequest;
import com.devteria.identityservice.dto.request.PickupLocationCreationRequest;
import com.devteria.identityservice.dto.request.PickupLocationUpdateRequest;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PickupLocationMapper {
    PickupLocation toPickupLocation(PickupLocationCreationRequest request);
    PickupLocationResponse toPickupLocationResponse(PickupLocation pickupLocation);
    void updatePickupLocation(@MappingTarget PickupLocation pickupLocation, PickupLocationUpdateRequest request);
}
