package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.CreateProductForm;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

import java.util.List;

public interface iProductsServices {
    ProductsEntity findProductbyName(String name);

    ProductsEntity insertProduct(CreateProductForm product);
}
