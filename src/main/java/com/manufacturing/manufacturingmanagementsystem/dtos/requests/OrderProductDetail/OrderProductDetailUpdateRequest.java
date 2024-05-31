package com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderProductDetail;

import lombok.*;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderProductDetail request
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
