package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleControllers {
    @Autowired
    RolesServices roleService;

    @PostMapping("/create")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        System.out.println("request: " + request.toString());
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}