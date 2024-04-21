package com.manufacturing.manufacturingmanagementsystem.dtos.requests;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateProductForm {

    @ApiModelProperty(name = "nameProduct", required = true)
    @NotEmpty
    private String nameProduct;
    @ApiModelProperty(name = "unitProduct", required = true)
    private String unitProduct;
    @ApiModelProperty(name = "priceProduct", required = true)
    @NotNull
    private Double priceProduct;
    @ApiModelProperty(name = "volumeProduct", required = true)
    @NotNull
    private Double volumeProduct;
    @ApiModelProperty(name = "kindProduct", required = true)
    private String kindProduct;
}