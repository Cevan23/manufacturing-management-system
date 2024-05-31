package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission;

import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the PermissionList response
public class PermissionListResponse {

    private final List<PermissionResponse> permissions;

    public PermissionListResponse(List<PermissionResponse> permissions) {
        this.permissions = permissions;
    }

    public static PermissionListResponse fromPermissionList(List<PermissionsEntity> permissionEntities) {
        List<PermissionResponse> permissionResponses = permissionEntities.stream()
                .map(PermissionResponse::fromPermission)
                .toList();

        return new PermissionListResponse(permissionResponses);
    }

    public List<PermissionResponse> getPermissions() {
        return permissions;
    }
}
