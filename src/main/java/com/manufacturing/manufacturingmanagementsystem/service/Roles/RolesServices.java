package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionListResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.PermissionRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesServices implements IRolesServices {

    RolesRepository rolesRepository;

    PermissionRepository permissionRepository;

    public RolesServices(RolesRepository rolesRepository, PermissionRepository permissionRepository) {
        this.rolesRepository = rolesRepository;
        this.permissionRepository = permissionRepository;
    }
    private PermissionsEntity getOrCreatePermission(String permissionName) {
        PermissionsEntity permission = permissionRepository.findByName(permissionName);
        if (permission == null) {
            System.out.println("No permission found for the given name, creating new one");
            permission = new PermissionsEntity();
            permission.setName(permissionName);
            permission.setDescription("This is used for " + permissionName);
            permission = permissionRepository.save(permission);
        }
        return permission;
    }

    public RoleResponse update(RoleRequest request){
        System.out.println("request: " + request.toString());

        // Create a new RolesEntity and manually set the fields from the request
        var role = new RolesEntity();
        role = rolesRepository.findRoleByRoleName(request.getRoleName());
        System.out.println("role: " + role.toString());

        if (request.getPermissions() == null) {
            System.out.println("request.getPermissions() is null");
        } else {
            var permissions = new HashSet<PermissionsEntity>();
            for (String permissionName : request.getPermissions()) {
                PermissionsEntity permission = getOrCreatePermission(permissionName);
                permissions.add(permission);
            }
            List<PermissionsEntity> permissionsList = new ArrayList<>(permissions);
            role.setPermissions(permissionsList);
        }

        role = rolesRepository.save(role);

        // Manually create a RoleResponse from the saved RolesEntity
        var response = new RoleResponse();
        response.setName(role.getRoleName());
        response.setDescription(role.getDescription());
        // Convert each PermissionsEntity in the list to a PermissionResponse
        var permissionResponses = role.getPermissions().stream()
                .map(PermissionResponse::fromPermission)
                .collect(Collectors.toList());
        System.out.println("permissionResponses: " + permissionResponses.toString());
        response.setPermissions(new PermissionListResponse(permissionResponses));

        return response;

    }

    public RoleResponse addPermission(RoleRequest request){
        RolesEntity role = rolesRepository.findRoleByRoleName(request.getRoleName());

        if (request.getPermissions() != null) {
            HashSet<PermissionsEntity> permissions = new HashSet<>(rolesRepository.findPermissionsByRoleId(role.getId()));

            for (String permissionName : request.getPermissions()) {
                PermissionsEntity permission = getOrCreatePermission(permissionName);
                permissions.add(permission);
            }
            role.getPermissions().clear();
            role.getPermissions().addAll(permissions);
        }

        role = rolesRepository.save(role);

        RoleResponse response = new RoleResponse();
        response.setName(role.getRoleName());
        response.setDescription(role.getDescription());
        List<PermissionResponse> permissionResponses = role.getPermissions().stream()
                .map(PermissionResponse::fromPermission)
                .collect(Collectors.toList());
        System.out.println("permissionResponses: " + permissionResponses.toString());
        response.setPermissions(new PermissionListResponse(permissionResponses));

        return response;
    }

    public void delete(String role){
        var roleId = rolesRepository.findIdByRoleName(role);
        rolesRepository.deleteById(roleId);
    }

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

    public boolean isRoleNameExists(String roleName) {
        return rolesRepository.findRoleByRoleName(roleName) != null;

    }

}

