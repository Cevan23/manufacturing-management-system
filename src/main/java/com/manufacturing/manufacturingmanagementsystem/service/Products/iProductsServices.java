package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
// Author: Pham Hien Nhan
// this interface is used to declare the methods that will be implemented in the ProductsServices class
public interface iProductsServices {
    ProductsEntity findProductbyName(String name);

    void insertProduct(ProductsDTO product, Long bomID, Long categoryID);

    List<Map<String, Object>> getProductForSaleForecastById(Long id);

    List<Map<String, Object>> getProductForOrderProductById(Long id);

    List<ProductsEntity> getAllProducts();

    void updateProduct(ProductsDTO request);
}
