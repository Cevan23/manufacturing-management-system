package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Materials.MaterialsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialsServices materialsServices;

    @Autowired
    public MaterialController(MaterialsServices materialsServices) {
        this.materialsServices = materialsServices;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createMaterial(@RequestBody MaterialsDTO material) {
        if(material == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());

        } else {
            MaterialsEntity materialEntity = materialsServices.findMaterialByName(material.getName());
            if (materialEntity != null) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.DATA_ALREADY_EXISTS.getCode())
                                .message(ErrorCode.DATA_ALREADY_EXISTS.getMessage())
                                .build());
            }
            try {
                materialsServices.createMaterial(material);
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .message("Material created successfully")
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateMaterial(@RequestBody MaterialsDTO material) {
        if(material == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());

        } else {
            if(materialsServices.findMaterialById(material.getId()) == null) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.NOT_FOUND.getCode())
                                .message("Material " + ErrorCode.NOT_FOUND.getMessage())
                                .build());
            }
            try {
                materialsServices.updateMaterial(material);
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .message("Material updated successfully")
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteMaterial(@PathVariable Long id) {
        if(id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());

        } else {
            try {
                materialsServices.deleteMaterial(id);
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .message("Material deleted successfully")
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAllMaterials() {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .result(materialsServices.findAllMaterials())
                        .build());
    }

    @GetMapping("/find")
    public ResponseEntity<ApiResponse> findMaterialByName(@RequestBody String name) {
        if (name == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());
        } else {
            try {
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .result(materialsServices.findMaterialByName(name))
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse> findMaterialById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());
        } else {
            try {
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .result(materialsServices.findMaterialById(id))
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }

    }

    @GetMapping("/findAlike")
    public ResponseEntity<ApiResponse> findAlikeName(@RequestBody String name) {
        if (name == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message(ErrorCode.BAD_REQUEST.getMessage())
                            .build());
        } else {
            try {
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .result(materialsServices.findAlikeName(name))
                                .build());
            } catch (Exception e) {
               throw new AppException(ErrorCode.BAD_REQUEST);
            }

        }

    }


}
