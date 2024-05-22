package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Product;

import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

import java.util.List;

public class ProductListResponse {
    private final List<ProductResponse> products;

    public ProductListResponse(List<ProductResponse> products) {
        this.products = products;
    }

    public static ProductListResponse fromProductList(List<ProductsEntity> productEntities) {
        List<ProductResponse> productResponses = productEntities.stream()
                .map(ProductResponse::fromProduct)
                .toList();

        return new ProductListResponse(productResponses);
    }

    public List<ProductResponse> getProducts() {
        return products;
    }
}
