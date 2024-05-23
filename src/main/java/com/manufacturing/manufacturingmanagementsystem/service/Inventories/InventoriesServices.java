package com.manufacturing.manufacturingmanagementsystem.service.Inventories;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoriesServices implements IInventoriesServices {

    private final InventoriesRepository inventoriesRepository;

    @Override
    public InventoriesEntity insertInventory(InventoryDTO inventoryDTO) throws Exception {

        String inventoryName = inventoryDTO.getName();
        if(inventoryName == null) {
            throw new Exception("Inventory đã tồn tại");
        }

        InventoriesEntity inventory = InventoriesEntity.builder()
                .name(inventoryName)
                .address(inventoryDTO.getAddress())
                .maxVolume(inventoryDTO.getMaxVolume())
                .build();

        return inventoriesRepository.save(inventory);
    }

    @Override
    public List<InventoriesEntity> getAllInventory() throws Exception {
        return inventoriesRepository.findAll();
    }

    @Override
    public InventoriesEntity updateInventory(InventoryDTO inventoryDTO, long id) throws Exception {

        InventoriesEntity inventory = inventoriesRepository.getReferenceById(id);

        if(inventoryDTO.getName() != null) {
            inventory.setName(inventoryDTO.getName());
        }

        if(inventoryDTO.getAddress() != null) {
            inventory.setAddress(inventoryDTO.getAddress());
        }

        if(inventoryDTO.getMaxVolume() != null) {
            inventory.setMaxVolume(inventoryDTO.getMaxVolume());
        }


        return inventoriesRepository.save(inventory);
    }

    @Override
    public void deleteInventory(long id) throws Exception {

    }

    // Các phương thức service khác cần thiết
}

