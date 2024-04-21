package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.CreateProductForm;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

public interface iProductsServices {
    ProductsEntity findProductbyName(String name);

    ProductsEntity insertProduct(CreateProductForm product);
}
