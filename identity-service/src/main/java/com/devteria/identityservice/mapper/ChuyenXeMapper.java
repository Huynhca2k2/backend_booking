package com.devteria.identityservice.mapper;


import com.devteria.identityservice.dto.request.BusCreationRequest;
import com.devteria.identityservice.dto.request.ChuyenXeCreationRequest;
import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChuyenXeMapper {

    ChuyenXe toChuyenXe(ChuyenXeCreationRequest request);

    ChuyenXeResponse toChuyenXeResponse(ChuyenXe chuyenXe);
//    UserResponse toUserResponse(User user);

//    BusResponse toBusResponse(Bus bus);

//    @Mapping(target = "roles", ignore = true)
//    void updateUser(@MappingTarget Bus bus, UserUpdateRequest request);
}
