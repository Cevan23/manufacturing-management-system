package com.manufacturing.manufacturingmanagementsystem.dtos;
import com.manufacturing.manufacturingmanagementsystem.models.Permissions;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDTO {

    private String roleName;

    private String description;

    Set<Permissions> permissions;
}