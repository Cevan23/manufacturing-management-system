package com.manufacturing.manufacturingmanagementsystem.controllers;


import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.BOMs.BOMsServices;
import com.manufacturing.manufacturingmanagementsystem.service.Products.ProductsServices;
import com.manufacturing.manufacturingmanagementsystem.service.Products.iProductsServices;
import jakarta.validation.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductControllers {

    private final ProductsServices productsService;
    private final BOMsServices bomsService;

    @PostMapping("/createProduct")
    public ResponseEntity<ApiResponse> createProduct(
            @Valid @RequestBody CreateProductRequest createProductRequest
    ) {
        if(createProductRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("Product creation request is required.")
                            .result(null)
                            .build());
        }
        try {
            Long bomID = createProductRequest.getBomID();
            Long categoryID = createProductRequest.getCategoryID();
            ProductsDTO product = new ProductsDTO();

            BOMsDTO bom = bomsService.getBOMById(bomID);
            product.setName(bom.getName());
            product.setUnit(bom.getUnit());
            product.setPrice(bom.getSellPrice());
            product.setVolume(bom.getTotalPrice());
            productsService.insertProduct(product, bomID, categoryID);

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Product created successfully")
                            .result(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.builder()
                            .message("An error occurred: " + e.getMessage())
                            .result(null)
                            .build());
        }
    }
    @GetMapping("/forSaleDetail/{id}")
    public ResponseEntity<ApiResponse> getProductsForSaleForecastById(
            @Valid @PathVariable Long id) {
        try {
            List<Map<String, Object>> listProducts = productsService.getProductForSaleForecastById(id);
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Get products successfully")
                            .result(listProducts)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.builder()
                            .message("An error occurred: " + e.getMessage())
                            .result(null)
                            .build());
        }
    }
    @GetMapping("/forOrderProduct/{id}")
    public ResponseEntity<ApiResponse> getProductsForOrderProductById(
            @Valid @PathVariable Long id) {
        try {
            List<Map<String, Object>> listProducts = productsService.getProductForOrderProductById(id);
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Get products successfully")
                            .result(listProducts)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.builder()
                            .message("An error occurred: " + e.getMessage())
                            .result(null)
                            .build());
        }
    }
}
