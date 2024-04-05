package com.manufacturing.manufacturingmanagementsystem.service.Inventories;

import com.manufacturing.manufacturingmanagementsystem.repositories.InventoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoriesServices implements IInventoriesServices {

    private final InventoriesRepository inventoriesRepository;

    // Các phương thức service khác cần thiết
}

