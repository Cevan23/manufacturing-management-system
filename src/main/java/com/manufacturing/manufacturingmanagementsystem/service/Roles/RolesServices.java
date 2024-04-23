package com.manufacturing.manufacturingmanagementsystem.service.Roles;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.RolesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.PermissionRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.RolesRepository;
import lombok.AllArgsConstructor;
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

    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = rolesRepository.save(role);
        return roleMapper.toRoleResponse(role);
//        var role = new RolesEntity();
//        role.setRoleName(request.getRoleName());
//        role.setDescription(request.getDescription());
//
//        var permissions = permissionRepository.findAllById(request.getPermissions());
//        role.setPermissions(new HashSet<>(permissions));
//        role = rolesRepository.save(role);
//        return toRoleResponse(role);

    }

    private RoleResponse toRoleResponse(RolesEntity role) {
        RoleResponse response = new RoleResponse();
        response.setName(role.getRoleName());
        response.setDescription(role.getDescription());

        Set<PermissionResponse> permissionResponses = role.getPermissions().stream()
                .map(permission -> {
                    PermissionResponse permissionResponse = new PermissionResponse();
                    // Assuming PermissionResponse has a field 'name'
                    permissionResponse.setName(permission.getName());
                    // Map other fields if exist
                    return permissionResponse;
                })
                .collect(Collectors.toSet());

        response.setPermissions(permissionResponses);

        return response;
    }

    public List<RoleResponse> getAll(){
        return rolesRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

//    public List<RoleResponse> getAll(){
//        return rolesRepository.findAll()
//                .stream()
//                .map(role -> {
//                    RoleResponse response = new RoleResponse();
//                    response.setName(role.getRoleName());
//                    response.setDescription(role.getDescription());
//
//                    Set<PermissionResponse> permissionResponses = role.getPermissions().stream()
//                            .map(permission -> {
//                                PermissionResponse permissionResponse = new PermissionResponse();
//                                // Assuming PermissionResponse has a field 'name'
//                                permissionResponse.setName(permission.getName());
//                                // Map other fields if exist
//                                return permissionResponse;
//                            })
//                            .collect(Collectors.toSet());
//
//                    response.setPermissions(permissionResponses);
//
//                    return response;
//                })
//                .collect(Collectors.toList());
//    }

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
}

