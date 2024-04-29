package com.manufacturing.manufacturingmanagementsystem.mapper;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.models.Permissions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permissions toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permissions permission);
}