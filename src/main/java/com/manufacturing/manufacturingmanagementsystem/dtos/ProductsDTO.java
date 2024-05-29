package com.manufacturing.manufacturingmanagementsystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {

    private Long id;

    @JsonProperty("category_id")
    private Long categoryID;

    @JsonProperty("bom_id")
    private Long bomID;

    @JsonProperty("name")
    @NotBlank(message = "Product name is required")
    private String name;

    @JsonProperty("unit")
    @NotBlank(message = "Product unit is required")
    private String unit;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("sell_price")
    private Double sellPrice;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("kind")
    @NotBlank(message = "Product kind is required")
    private String kind;

}