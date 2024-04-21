package com.manufacturing.manufacturingmanagementsystem.controllers;


import com.manufacturing.manufacturingmanagementsystem.dtos.requests.CreateProductForm;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ProductResponses;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ResponseObject;
import com.manufacturing.manufacturingmanagementsystem.service.Products.iProductsServices;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductControllers {
    private final iProductsServices productsService;

    @PostMapping("/insert")
    public ResponseEntity<?> insertProduct(
            @Valid @RequestBody CreateProductForm createProductForm,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            ProductsEntity productResponse = productsService.insertProduct(createProductForm);
            return ResponseEntity.ok(ResponseObject.builder()
                    .data(ProductResponses.fromProduct(productResponse))
                    .message("Create project successfully")
                    .status(HttpStatus.OK)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/hello")
    public String insertProduct(

    ) {
        return "new ResponseEntity<>().toString()";
    }


}
