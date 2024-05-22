package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.CategoriesDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.service.Categories.CategoriesServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoriesServices categoriesServices;

    @PostMapping("/createCategory")
    public ResponseEntity<ApiResponse> createCategory(
            @Valid @RequestBody CategoriesDTO createCategoryRequest
            ) {
        if(createCategoryRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("Category creation request is required.")
                            .result(null)
                            .build());
        }
        try {
            categoriesServices.createCategory(createCategoryRequest.getCategoryName());
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Category created successfully")
                            .result(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(ApiResponse.builder()
                            .message("An error occurred: " + e.getMessage())
                            .result(null)
                            .build());
        }
    }
}
