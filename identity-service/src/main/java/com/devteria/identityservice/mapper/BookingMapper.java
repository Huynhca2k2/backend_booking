package com.devteria.identityservice.mapper;


import com.devteria.identityservice.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toBooking(Booking request);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
