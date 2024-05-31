package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.PermissionMapper;
import com.manufacturing.manufacturingmanagementsystem.models.PermissionsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// Author: Pham Van Cao
// this class is used to implement the methods defined in the IPermissionService interface
@Service
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    // this method is used to create a permission
    public PermissionResponse create(PermissionRequest request){
        PermissionsEntity permission = new PermissionsEntity();
        permission.setName(request.getName());
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    // this method is used to get all permissions
    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    // this method is used to get a permission by its name
    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
