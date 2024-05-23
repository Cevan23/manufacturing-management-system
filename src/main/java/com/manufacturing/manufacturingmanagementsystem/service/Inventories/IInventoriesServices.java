package com.manufacturing.manufacturingmanagementsystem.service.Inventories;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;

import java.util.List;

public interface IInventoriesServices {
    // Khai báo các phương thức service cần thiết
    InventoriesEntity insertInventory(InventoryDTO inventoryDTO) throws Exception;

    List<InventoriesEntity> getAllInventory() throws Exception;

    InventoriesEntity updateInventory(InventoryDTO inventoryDTO, long id) throws Exception;

    void deleteInventory(long id) throws Exception;

}

