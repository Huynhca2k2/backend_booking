package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusUpdateRequest;
import com.devteria.identityservice.dto.request.TripCreationRequest;
import com.devteria.identityservice.dto.request.TripUpdateRequest;
import com.devteria.identityservice.dto.response.DropoffLocationResponse;
import com.devteria.identityservice.dto.response.PickupLocationResponse;
import com.devteria.identityservice.dto.response.TripResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import com.devteria.identityservice.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TripMapper {

    Trip toTrip(TripCreationRequest request);

//    @Mapping(source = "trip.pickupLocations", target = "pickupLocations")
//    @Mapping(source = "trip.dropoffLocations", target = "dropoffLocations")
//    @Mapping(source = "trip.buses", target = "buses")
    TripResponse toTripResponse(Trip trip);

    void updateTrip(@MappingTarget Trip trip, TripUpdateRequest request);

}
