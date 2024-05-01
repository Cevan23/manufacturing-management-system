package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.CreateProductForm;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductsServices implements iProductsServices {

    private final ProductsRepository productsRepository;
    @Override
    public ProductsEntity findProductbyName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return productsRepository.findFirstByName(name);
    }

    @Override
    public ProductsEntity insertProduct(CreateProductForm productForm){

        if (productForm == null) {
            return null;
        }

        ProductsEntity productEntity = new ProductsEntity();
        productEntity.setName(productForm.getNameProduct());
        productEntity.setUnit(productForm.getUnitProduct());
        productEntity.setPrice(productForm.getPriceProduct());
        productEntity.setVolume(productForm.getVolumeProduct());
        return productsRepository.save(productEntity);
    }
}
