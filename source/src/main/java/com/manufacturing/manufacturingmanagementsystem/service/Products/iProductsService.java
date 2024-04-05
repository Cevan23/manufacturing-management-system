package com.manufacturing.manufacturingmanagementsystem.service.Products;
import com.manufacturing.manufacturingmanagementsystem.form.Products.CreateProductForm;

import com.manufacturing.manufacturingmanagementsystem.model.ProductsEntity;

public interface iProductsService {
    ProductsEntity findProductbyName(String name);

    ProductsEntity insertProduct(CreateProductForm product);
}
