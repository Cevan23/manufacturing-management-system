package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterialDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.InventoryMaterialDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryMaterialDetailsServices implements IInventoryMaterialDetailsServices {

    private final InventoryMaterialDetailsRepository inventoryMaterialDetailsRepository;

    // Các phương thức service khác cần thiết
}