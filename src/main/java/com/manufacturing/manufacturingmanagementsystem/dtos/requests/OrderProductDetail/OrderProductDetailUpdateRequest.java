package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderProductDetail;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrderProductDetailUpdateRequest {
    private Long oid;
    private Long pid;
    private Integer quantity;
}
