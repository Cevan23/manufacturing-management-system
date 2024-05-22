package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMaterialDetailsDTO {

    private long inventoryId;

    private long materialId;

    private Integer quantity;

    private Integer safetyStockAmount;
}