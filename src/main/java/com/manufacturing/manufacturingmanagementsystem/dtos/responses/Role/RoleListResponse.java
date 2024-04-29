package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role;

import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;

import java.util.List;

public class RoleListResponse {

    private final List<RoleResponse> roles;

    public RoleListResponse(List<RoleResponse> roles) {
        this.roles = roles;
    }

    public static RoleListResponse fromRoleList(List<RolesEntity> roleEntities) {
        List<RoleResponse> roleResponses = roleEntities.stream()
                .map(RoleResponse::fromRole)
                .toList();

        return new RoleListResponse(roleResponses);
    }

    public List<RoleResponse> getRoles() {
        return roles;
    }
}