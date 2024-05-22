package com.manufacturing.manufacturingmanagementsystem.dtos.requests;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoleRequest {
    String roleName;
    String description;
    Set<String> permissions;
}