package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;
// Author: Nguyen Cao Nhan
// this class is used to handle the OrderDetailsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private String orderId;

    private Long productId;

    private Integer quantity;

    private Float totalPrice;
}