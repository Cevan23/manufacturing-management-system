package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

public interface iProductsServices {
    ProductsEntity findProductbyName(String name);

    void insertProduct(ProductsDTO product, Long bomID, Long categoryID);
}
