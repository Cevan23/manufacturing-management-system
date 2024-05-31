package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import lombok.*;
import lombok.experimental.FieldDefaults;
// Author: Pham Van Cao
// this class is used to handle the Permission request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequest {
    String name;
    String description;
}