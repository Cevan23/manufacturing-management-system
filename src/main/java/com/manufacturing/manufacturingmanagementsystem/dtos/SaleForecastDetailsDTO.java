package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;
// Author: Nguyen Cao Nhan
// this class is used to handle the SaleForecastDetailsDTO response
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
