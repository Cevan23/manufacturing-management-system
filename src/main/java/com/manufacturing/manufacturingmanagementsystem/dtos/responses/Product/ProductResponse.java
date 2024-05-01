package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String unit;
    private Double price;
    private Double volume;
    private String kind;
    public static ProductResponse fromProduct(ProductsEntity product) {
        return ProductResponse.builder()
                .name(product.getName())
                .unit(product.getUnit())
                .price(product.getPrice())
                .volume(product.getVolume())
                .kind(product.getCategory().getCategoryName())
                .build();
    }
}
