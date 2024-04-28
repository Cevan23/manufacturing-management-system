package com.manufacturing.manufacturingmanagementsystem.service;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.PermissionMapper;
import com.manufacturing.manufacturingmanagementsystem.models.Permissions;
import com.manufacturing.manufacturingmanagementsystem.repositories.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request){
        Permissions permission = new Permissions();
        permission.setName(request.getName());
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}
