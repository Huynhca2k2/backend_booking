package com.devteria.identityservice.mapper;


import com.devteria.identityservice.entity.DropoffLocation;
import com.devteria.identityservice.entity.PickupLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DropoffLocationMapper {
    DropoffLocation toDropoffLocation(DropoffLocation request);

}
