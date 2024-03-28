package com.manufacturing.manufacturingmanagementsystem.repository.ID;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InventoriesDetailEntityId implements Serializable {
    private Long productId;
    private Long inventoryId;
}
