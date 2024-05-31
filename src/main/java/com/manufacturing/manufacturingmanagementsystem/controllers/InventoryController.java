package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Inventories.IInventoriesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Author: Pham Hien Nhan
// this class is used to handle the request from the client side
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoriesServices inventoriesServices;
    // this method is used to create a new inventory
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInventory(@RequestBody InventoryDTO inventoryDTO) {

        try {
            InventoriesEntity inventories = inventoriesServices.insertInventory(inventoryDTO);
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Inventory created successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }
    // this method is used to update the inventory
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllInventory() {

        try {
            List<InventoriesEntity> inventories = inventoriesServices
                    .getAllInventory();

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Get all Inventory successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }

}
