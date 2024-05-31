package com.manufacturing.manufacturingmanagementsystem.dtos.requests;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
// Author: Pham Van Cao
// this class is used to handle the Role request
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