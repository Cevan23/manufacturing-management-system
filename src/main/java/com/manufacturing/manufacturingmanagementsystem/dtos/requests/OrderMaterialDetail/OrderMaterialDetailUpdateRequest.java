package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderMaterialDetailUpdateRequest {
    private Long oid;
    private Long mid;
    private Integer quantity;
}
