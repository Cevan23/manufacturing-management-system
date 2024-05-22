package com.manufacturing.manufacturingmanagementsystem.service.Products;

import com.manufacturing.manufacturingmanagementsystem.dtos.ProductsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product.CreateProductRequest;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.CategoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.SaleForecastDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.*;
import com.manufacturing.manufacturingmanagementsystem.service.SaleForecastDetails.SaleForecastDetailsServices;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServices implements iProductsServices {

    private final ProductsRepository productsRepository;
    private final CategoriesRepository categoriesRepository;
    private final BOMsRepository bomsRepository;
    private final SaleForecastDetailsServices saleForecastDetailsServices;

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

    @Override
    public List<Map<String, Object>> getProductForSaleForecastById(Long id) {
        List<ProductsEntity> productsEntityList = productsRepository.findAll();
        List<Map<String, Object>> listSaleDetail = saleForecastDetailsServices.findSaleForecastDetailById(id);
        List<Map<String, Object>> productMap = new ArrayList<>();
        if (listSaleDetail.isEmpty()) {
            for (ProductsEntity product : productsEntityList) {
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("id", product.getId());
                productInfo.put("name", product.getName());
                productInfo.put("price", product.getPrice());
                productInfo.put("sellPrice", product.getSellPrice());
                productMap.add(productInfo);
            }
        } else {
            Set<Long> saleDetailProductIds = listSaleDetail.stream()
                    .map(detail -> (Long) detail.get("product_id"))
                    .collect(Collectors.toSet());
            for (ProductsEntity product : productsEntityList) {
                if (!saleDetailProductIds.contains(product.getId())) {
                    Map<String, Object> productInfo = new HashMap<>();
                    productInfo.put("id", product.getId());
                    productInfo.put("name", product.getName());
                    productInfo.put("price", product.getPrice());
                    productInfo.put("sellPrice", product.getSellPrice());
                    productMap.add(productInfo);
                }
            }
        }
        return productMap;
    }
}
