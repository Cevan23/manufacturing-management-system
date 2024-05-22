package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterials;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryMaterialDetailsEntity;
import java.util.List;

public interface IInventoryMaterialsServices {
    // Khai báo các phương thức service cần thiết

    InventoryMaterialDetailsEntity insertInventoryMaterialDetail(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception;

    List<InventoryMaterialDetailsEntity> getAllInventoryMaterial();

}


