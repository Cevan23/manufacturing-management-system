package com.manufacturing.manufacturingmanagementsystem.mapper;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "id", ignore = true)
    RolesEntity toRole(RoleRequest request);

    @Mapping(target = "id", ignore = true)
    RoleResponse toRoleResponse(RolesEntity role);
}