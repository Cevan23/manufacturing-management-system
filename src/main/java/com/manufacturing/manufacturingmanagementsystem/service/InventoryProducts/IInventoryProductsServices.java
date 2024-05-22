package com.manufacturing.manufacturingmanagementsystem.service.InventoryProducts;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryProductDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoryProductDetailsEntity;
import java.util.List;

public interface IInventoryProductsServices {
    // Khai báo các phương thức service cần thiết

    InventoryProductDetailsEntity insertInventoryProductDetail(InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception;

    List<InventoryProductDetailsEntity> getAllInventoryProduct();

}

