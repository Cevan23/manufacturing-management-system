package com.manufacturing.manufacturingmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDTO {

    @JsonProperty("name")
    @NotBlank(message = "Product name is required")
    private String name;

    @JsonProperty("unit")
    @NotBlank(message = "Product unit is required")
    private String unit;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("kind")
    @NotBlank(message = "Product kind is required")
    private String kind;

}