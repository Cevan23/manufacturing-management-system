package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductDetailsDTO {

    private Long inventoryId;
    private Long productId;
    private Integer quantity;
    private Integer safetyStockAmount;
}
