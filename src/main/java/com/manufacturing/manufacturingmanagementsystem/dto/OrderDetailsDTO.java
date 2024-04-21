package com.manufacturing.manufacturingmanagementsystem.dto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private String orderId;

    private Long productId;

    private Integer quantity;

    private Float totalPrice;
}