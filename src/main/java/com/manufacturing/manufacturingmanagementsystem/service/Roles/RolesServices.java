package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RolesServices implements IRolesServices {

    private final RolesRepository rolesRepository;

    @Override
    public String getRoleNameById(Long id) {
        try {
            String roleName = rolesRepository.findRoleNameById(id);
            return roleName;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No role found with the given ID");
        }
    }

    @Override
    public Long getRoleIdByRoleName(String roleName) {
        try {
            Long id = rolesRepository.findIdByRoleName(roleName);
            return id;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No role found with the given name");
        }
    }

    @Override
    public RolesEntity getRoleByRoleName(String roleName) {
        try {
            RolesEntity role = rolesRepository.findRoleByRoleName(roleName);
            return role;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No role found with the given name");
        }
    }

    @Override
    public RolesEntity getRoleById(Long id) {
        try {
            RolesEntity role = rolesRepository.findRoleById(id);
            return role;
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("No role found with the given ID");
        }
    }
}

