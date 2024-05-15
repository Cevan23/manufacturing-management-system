package com.manufacturing.manufacturingmanagementsystem.dtos.requests.Product;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class CreateProductRequest {

    @ApiModelProperty(name = "bomID", required = true)
    @NotNull
    private Long bomID;

    @ApiModelProperty(name = "categoryID", required = true)
    @NotNull
    private Long categoryID;

    @ApiModelProperty(name = "volumeProduct", required = true)
    private Double volumeProduct;

}