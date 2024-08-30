package com.devteria.identityservice.mapper;


import com.devteria.identityservice.entity.AllCode;
import com.devteria.identityservice.entity.ThoiGian;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ThoiGianMapper {

    ThoiGian toThoiGian(ThoiGian request);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
