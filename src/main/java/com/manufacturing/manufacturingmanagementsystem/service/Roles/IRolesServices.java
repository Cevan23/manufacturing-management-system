package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;

public interface IRolesServices {
    String getRoleNameById(Long id);

    Long getRoleIdByRoleName(String roleName);

    RolesEntity getRoleByRoleName(String roleName);

    RolesEntity getRoleById(Long id);

}

