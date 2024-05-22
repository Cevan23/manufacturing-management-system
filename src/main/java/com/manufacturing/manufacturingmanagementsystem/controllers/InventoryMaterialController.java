package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryProductDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Inventories.IInventoriesServices;
import com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterialDetails.IInventoryMaterialDetailsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-material")
@RequiredArgsConstructor
public class InventoryMaterialController {

    private final IInventoryMaterialDetailsServices inventoryMaterialDetailsServices;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInventory(@RequestBody InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) {

        try {
            InventoryMaterialDetailsEntity inventories = inventoryMaterialDetailsServices
                    .insertInventoryMaterialDetail(inventoryMaterialDetailsDTO);

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Inventory Material created successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllInventoryMaterial() {

        try {
            List<InventoryMaterialDetailsEntity> inventories = inventoryMaterialDetailsServices
                    .getAllInventoryMaterial();

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Get all Inventory Material  successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }


    @PutMapping("")
    public ResponseEntity<ApiResponse> updateInventoryMaterial(
            @RequestBody InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO
    ) {

        try {
            InventoryMaterialDetailsEntity inventories = inventoryMaterialDetailsServices
                    .updateInventoryMaterial(inventoryMaterialDetailsDTO);

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Update Inventory Material successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteInventoryProduct(
            @Valid @RequestParam("materialId") Long materialId,
            @Valid @RequestParam("inventoryId") Long inventoryId
    ) {

        try {

            inventoryMaterialDetailsServices.deleteInventoryMaterial(materialId, inventoryId);

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Delete Inventory Material successfully")
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }
}
