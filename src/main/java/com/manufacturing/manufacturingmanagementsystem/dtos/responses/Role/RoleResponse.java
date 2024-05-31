package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionListResponse;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the Role response
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleResponse {
    private String name;
    private String description;
    private PermissionListResponse permissions;

    public static RoleResponse fromRole(RolesEntity role) {
        return RoleResponse.builder()
                .name(role.getRoleName())
                .description(role.getDescription())
                .permissions(PermissionListResponse.fromPermissionList(role.getPermissions()))
                .build();
    }
}
