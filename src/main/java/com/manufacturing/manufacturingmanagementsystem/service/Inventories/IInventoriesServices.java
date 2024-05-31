package com.manufacturing.manufacturingmanagementsystem.service.Inventories;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;

import java.util.List;
// Author: Pham Hien Nhan
// this interface is used to declare the methods that will be implemented in the InventoriesServices class
public interface IInventoriesServices {
    // Khai báo các phương thức service cần thiết
    InventoriesEntity insertInventory(InventoryDTO inventoryDTO) throws Exception;

    List<InventoriesEntity> getAllInventory() throws Exception;

    InventoriesEntity updateInventory(InventoryDTO inventoryDTO, long id) throws Exception;

    void deleteInventory(long id) throws Exception;

}

