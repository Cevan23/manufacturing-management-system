package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryMaterialDetailsRepository extends JpaRepository<InventoryMaterialDetailsEntity, InventoryMaterialDetailEntityId> {
    // Add custom query methods if needed

    InventoryMaterialDetailsEntity findByInventoryAndMaterial(InventoriesEntity inventory, MaterialsEntity material);
}
