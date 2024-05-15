package com.manufacturing.manufacturingmanagementsystem.dtos.requests.SaleForecastDetail;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SaleForecastDetailUpdateRequest {
    private Long sid;
    private Long pid;
    private Integer quantity;
    private Float totalPrice;
    private Float totalSalePrice;
    private Long change_pid;
}
