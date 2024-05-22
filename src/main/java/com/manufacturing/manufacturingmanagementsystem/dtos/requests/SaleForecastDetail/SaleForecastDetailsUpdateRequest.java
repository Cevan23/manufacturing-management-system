package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleForecastDetailsUpdateRequest {
    private Long sid;
    private Long pid;
    private Integer quantity;
    private Float totalPrice;
    private Float totalSalePrice;
}
