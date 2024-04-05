package com.manufacturing.manufacturingmanagementsystem.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleForecastDetailsDTO {

    private String saleForecastId;

    private Long productId;

    private Integer quantity;

    private Float totalPrice;

    private Float totalSalePrice;
}
