package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.CategoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.CategoriesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsServices implements iProductsServices {

    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;
    private final BOMsRepository bomsRepository;
    @Override
    public ProductsEntity findProductbyName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return productsRepository.findFirstByName(name);
    }

    @Override
    public void insertProduct(ProductsDTO productsDTO, Long bomID, Long categoryID) {
        ProductsEntity productEntity = new ProductsEntity();
        productEntity.setName(productsDTO.getName());
        productEntity.setUnit(productsDTO.getUnit());
        productEntity.setPrice(productsDTO.getPrice());
        productEntity.setVolume(productsDTO.getVolume());
        Optional<CategoriesEntity> categoryOptional = categoriesRepository.findById(categoryID);
        Optional<BOMsEntity> bomOptional = bomsRepository.findById(bomID);

        if (categoryOptional.isPresent() && bomOptional.isPresent()) {
            productEntity.setCategory(categoryOptional.get());
            productEntity.setBom(bomOptional.get());
        }
        productsRepository.save(productEntity);
    }
}
