package com.manufacturing.manufacturingmanagementsystem.repositories.ID;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
// Author: Pham Hien Nhan
// this class is used to handle the InventoryProductDetailEntityId response
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InventoryProductDetailEntityId implements Serializable {
    private Long productId;
    private Long inventoryId;
}
