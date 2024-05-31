package com.manufacturing.manufacturingmanagementsystem.mapper;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import org.mapstruct.Mapper;
// Author: Pham Van Cao
// this class is used to handle the PermissionMapper response
@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionsEntity toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(PermissionsEntity permission);
}