package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.request.SeatCreationRequest;
import com.devteria.identityservice.dto.request.SeatUpdateRequest;
import com.devteria.identityservice.dto.response.SeatResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    Seat toSeat(SeatCreationRequest request);

    SeatResponse toSeatResponse(Seat seat);

    void updateSeat(@MappingTarget Seat seat, SeatUpdateRequest request);

}
