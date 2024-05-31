package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the InventoryMaterialDetailsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMaterialDetailsDTO {

    private long inventoryId;

    private long materialId;

    private Integer quantity;

    private Integer safetyStockAmount;
}