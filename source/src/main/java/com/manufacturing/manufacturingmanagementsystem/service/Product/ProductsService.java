package com.manufacturing.manufacturingmanagementsystem.service.Product;

import com.manufacturing.manufacturingmanagementsystem.model.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.repository.ProductsRepository;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.manufacturing.manufacturingmanagementsystem.dto.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.form.Products.CreateProductForm;
@Service
@AllArgsConstructor
public class ProductsService implements iProductsService {

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
        productEntity.setKind(productForm.getKindProduct());

        return productsRepository.save(productEntity);
    }

}
