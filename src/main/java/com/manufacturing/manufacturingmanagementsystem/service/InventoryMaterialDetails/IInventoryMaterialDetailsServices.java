package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;

import java.util.List;
// Author: Pham Hien Nhan
// this interface is used to declare the methods that will be implemented in the InventoryMaterialDetailsServices class
public interface IInventoryMaterialDetailsServices {

    InventoryMaterialDetailsEntity insertInventoryMaterialDetail(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception;

    List<InventoryMaterialDetailsEntity> getAllInventoryMaterial();

    InventoryMaterialDetailsEntity updateInventoryMaterial(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception;

    void deleteInventoryMaterial(long materialId, long inventoryId) throws Exception;

}


