package com.manufacturing.manufacturingmanagementsystem.controllers;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.PermissionRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Permission.PermissionResponse;
import com.manufacturing.manufacturingmanagementsystem.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the permission
@RestController
@RequestMapping("/api/permissions")
public class PermissionControllers {

    PermissionService permissionService;
    // Author: Pham Van Cao
    // this method is used to create the permission
    @PostMapping("/create")
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){
        System.out.println("request: " + request.toString());
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.create(request))
                .build();
    }
    // Author: Pham Van Cao
    // this method is used to get all the permission
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAll())
                .build();
    }
    // Author: Pham Van Cao
    // this method is used to delete the permission
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}