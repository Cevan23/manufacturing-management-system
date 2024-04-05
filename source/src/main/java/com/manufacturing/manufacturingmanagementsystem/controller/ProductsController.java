package com.manufacturing.manufacturingmanagementsystem.controller;

import com.manufacturing.manufacturingmanagementsystem.form.Products.CreateProductForm;
import com.manufacturing.manufacturingmanagementsystem.responses.ResponseObject;
import com.manufacturing.manufacturingmanagementsystem.service.Products.iProductsService;
import com.manufacturing.manufacturingmanagementsystem.model.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.responses.Products.ProductResponses;
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
public class ProductsController {
    private final iProductsService productsService;

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
