package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.BusTicketCreationRequest;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.BusTicket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusTicketMapper {

    BusTicket toBusTicket(BusTicketCreationRequest request);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
