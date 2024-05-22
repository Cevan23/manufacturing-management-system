package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryProductDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Inventories.IInventoriesServices;
import com.manufacturing.manufacturingmanagementsystem.service.InventoryProductDetails.IInventoryProductDetailsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-product")
@RequiredArgsConstructor
public class InventoryProductController {

    private final IInventoryProductDetailsServices inventoryProductDetailsServices;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInventory(@RequestBody InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception {

        try {
            InventoryProductDetailsEntity inventoryProductDetails = inventoryProductDetailsServices
                    .insertInventoryProductDetail(inventoryProductDetailsDTO);
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Inventory Product created successfully")
                            .result(inventoryProductDetails)
                            .build());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse> getAllInventoryProduct() {

        try {
            List<InventoryProductDetailsEntity> inventories = inventoryProductDetailsServices
                    .getAllInventoryProduct();

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Get all Inventory Product successfully")
                            .result(inventories)
                            .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }
    }


}