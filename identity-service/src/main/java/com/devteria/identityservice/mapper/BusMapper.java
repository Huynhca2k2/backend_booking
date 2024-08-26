package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.BusResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BusMapper {

    Bus toBus(BusCreationRequest request);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
