package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the InventoryProductDetailsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProductDetailsDTO {

    private Long inventoryId;
    private Long productId;
    private Integer quantity;
    private Integer safetyStockAmount;
}
