package com.manufacturing.manufacturingmanagementsystem.mapper;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionsEntity toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(PermissionsEntity permission);
}