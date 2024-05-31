package com.manufacturing.manufacturingmanagementsystem.repositories;

import com.manufacturing.manufacturingmanagementsystem.models.*;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryProductDetailEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Author: Pham Hien Nhan
// this class is used to handle the InventoryProductDetailsRepository response
@Repository
public interface InventoryProductDetailsRepository extends JpaRepository<InventoryProductDetailsEntity, InventoryProductDetailEntityId> {

    InventoryProductDetailsEntity findByInventoryAndProduct(InventoriesEntity inventory, ProductsEntity material);
}
