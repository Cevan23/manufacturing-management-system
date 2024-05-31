package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
// Author: Pham Van Cao
// this interface is used to get role name by id, get role id by role name, get role by role name, get role by id
public interface IRolesServices {
    String getRoleNameById(Long id);

    Long getRoleIdByRoleName(String roleName);

    RolesEntity getRoleByRoleName(String roleName);

    RolesEntity getRoleById(Long id);

}

