package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission;

import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionResponse {
    private String name;
    private String description;

    public static PermissionResponse fromPermission(PermissionsEntity permission) {
        return PermissionResponse.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
