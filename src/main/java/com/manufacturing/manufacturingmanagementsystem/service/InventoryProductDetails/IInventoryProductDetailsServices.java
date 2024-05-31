package com.manufacturing.manufacturingmanagementsystem.service.InventoryProductDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryProductDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryProductDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryProductDetailEntityId;

import java.util.List;
// Author: Pham Hien Nhan
// this interface is used to declare the methods that will be implemented in the InventoryProductDetailsServices class
public interface IInventoryProductDetailsServices {

    InventoryProductDetailsEntity insertInventoryProductDetail(InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception;

    List<InventoryProductDetailsEntity> getAllInventoryProduct();

    InventoryProductDetailsEntity updateInventoryProduct(
            InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception;

    void deleteInventoryProduct(long productId, long inventoryId) throws Exception;

}

