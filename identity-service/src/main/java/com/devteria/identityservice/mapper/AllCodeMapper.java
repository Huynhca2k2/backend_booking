package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.Bus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllCodeMapper {

    AllCode toAllCode(AllCode request);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
