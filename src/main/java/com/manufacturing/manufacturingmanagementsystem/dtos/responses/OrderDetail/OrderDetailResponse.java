package com.manufacturing.manufacturingmanagementsystem.dtos.responses.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// Author: Nguyen Cao Nhan
// this class is used to handle the Order Detail response
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private String orderId;

    private Long productId;

    private Integer quantity;

    private Float totalPrice;
}
