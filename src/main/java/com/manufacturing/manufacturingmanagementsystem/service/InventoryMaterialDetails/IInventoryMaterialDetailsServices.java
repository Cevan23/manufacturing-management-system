package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import java.util.List;

public interface IInventoryMaterialDetailsServices {
    // Khai báo các phương thức service cần thiết

    InventoryMaterialDetailsEntity insertInventoryMaterialDetail(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception;

    List<InventoryMaterialDetailsEntity> getAllInventoryMaterial();

    InventoryMaterialDetailsEntity updateInventoryMaterial(long id, InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO);

}


