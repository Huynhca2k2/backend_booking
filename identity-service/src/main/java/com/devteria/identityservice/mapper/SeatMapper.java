package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.SeatCreationRequest;
import com.devteria.identityservice.dto.response.SeatResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    Seat toSeat(SeatCreationRequest request);

    SeatResponse toSeatResponse(Seat seat);

}
