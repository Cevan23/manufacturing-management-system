package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.model.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMaterialDetailsRepository extends JpaRepository<InventoryMaterialDetailsEntity, InventoryMaterialDetailEntityId> {
    // Add custom query methods if needed
}
