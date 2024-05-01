package com.manufacturing.manufacturingmanagementsystem.mapper;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);


    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "id", ignore = true)
    RolesEntity toRole(RoleRequest request);

    @Mapping(target = "id", ignore = true)
    RoleResponse toRoleResponse(RolesEntity role);
}