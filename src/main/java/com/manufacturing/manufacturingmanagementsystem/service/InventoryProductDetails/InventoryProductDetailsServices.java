package com.manufacturing.manufacturingmanagementsystem.service.InventoryProductDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.InventoryProductDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryProductDetailsServices implements IInventoryProductDetailsServices {

    private final InventoryProductDetailsRepository inventoryProductDetailsRepository;

    // Các phương thức service khác cần thiết
}

