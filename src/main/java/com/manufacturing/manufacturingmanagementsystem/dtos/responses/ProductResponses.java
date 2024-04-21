package com.manufacturing.manufacturingmanagementsystem.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponses {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("kind")
    private String kind;

    public static ProductResponses fromProduct(ProductsEntity productEntity) {
        return ProductResponses.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .unit(productEntity.getUnit())
                .price(productEntity.getPrice())
                .volume(productEntity.getVolume())
                .build();
    }
}