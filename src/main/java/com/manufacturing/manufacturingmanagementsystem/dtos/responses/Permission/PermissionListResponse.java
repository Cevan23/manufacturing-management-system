package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission;

import com.manufacturing.manufacturingmanagementsystem.models.Permissions;

import java.util.List;

public class PermissionListResponse {

    private final List<PermissionResponse> permissions;

    public PermissionListResponse(List<PermissionResponse> permissions) {
        this.permissions = permissions;
    }

    public static PermissionListResponse fromPermissionList(List<Permissions> permissionEntities) {
        List<PermissionResponse> permissionResponses = permissionEntities.stream()
                .map(PermissionResponse::fromPermission)
                .toList();

        return new PermissionListResponse(permissionResponses);
    }

    public List<PermissionResponse> getPermissions() {
        return permissions;
    }
}
