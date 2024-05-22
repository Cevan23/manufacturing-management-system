package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.*;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryProductDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductDetailsRepository extends JpaRepository<InventoryProductDetailsEntity, InventoryProductDetailEntityId> {

    InventoryProductDetailsEntity findByInventoryAndProduct(InventoriesEntity inventory, ProductsEntity material);
}
