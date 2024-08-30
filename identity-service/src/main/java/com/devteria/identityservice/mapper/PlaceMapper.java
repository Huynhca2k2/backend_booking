package com.devteria.identityservice.mapper;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Place;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    Place toPlace(Place request);
//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
