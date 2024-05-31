package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.RoleRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Role.RoleResponse;
import com.manufacturing.manufacturingmanagementsystem.service.Roles.RolesServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Author: Pham Van Cao
// this class is used to handle the role
@RestController
@RequestMapping("/api/roles")
@Slf4j
public class RoleControllers {
    @Autowired
    RolesServices roleService;
    // Author: Pham Van Cao
    // this method is used to create the role
    @PostMapping("/create")
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        System.out.println("request: " + request.toString());
        return ApiResponse.<RoleResponse>builder()
//                .result(roleService.create(request))
                .build();
    }
    // Author: Pham Van Cao
    // this method is used to get all the role
    @GetMapping("/getAll")
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
//                .result(roleService.getAll())
                .build();
    }
    // Author: Pham Van Cao
    // this method is used to delete the role
    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
    // Author: Pham Van Cao
    // this method is used to update the role
    @PostMapping("/update")
    ApiResponse<RoleResponse> update(@RequestBody RoleRequest request){
        if (!roleService.isRoleNameExists(request.getRoleName())) {
            return ApiResponse.<RoleResponse>builder()
                    .message("Role name don't exists")
                    .build();
        }
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.update(request))
                .build();
    }
    // Author: Pham Van Cao
    // this method is used to add the permission
    @PostMapping("/addPermission")
    ApiResponse<RoleResponse> addPermission(@RequestBody RoleRequest request){
        if (!roleService.isRoleNameExists(request.getRoleName())) {
            return ApiResponse.<RoleResponse>builder()
                    .message("Role name don't exists")
                    .build();
        }
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.addPermission(request))
                .build();
    }
}
