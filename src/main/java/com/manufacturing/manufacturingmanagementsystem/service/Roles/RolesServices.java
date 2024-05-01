package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.PermissionRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RolesServices implements IRolesServices {

    RolesRepository rolesRepository;

    PermissionRepository permissionRepository;

    RoleMapper roleMapper;


    public RolesServices(RolesRepository rolesRepository, PermissionRepository permissionRepository, RoleMapper roleMapper) {
        this.rolesRepository = rolesRepository;
        this.permissionRepository = permissionRepository;
    }

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(permissions);

        role = rolesRepository.save(role);
        return roleMapper.toRoleResponse(role);

    }

    public RoleResponse update(RoleRequest request){
        System.out.println("request: " + request.toString());
        var role = roleMapper.toRole(request);
        System.out.println("role: " + role.toString());
        var permissions = permissionRepository.findAllById(request.getPermissions());
        System.out.println("permissions: " + permissions.toString());
        role.setPermissions(permissions);

        role = rolesRepository.save(role);
        return roleMapper.toRoleResponse(role);

    }

    public List<RoleResponse> getAll(){
        return rolesRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
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

