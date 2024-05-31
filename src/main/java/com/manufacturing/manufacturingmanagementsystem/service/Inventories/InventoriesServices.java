package com.manufacturing.manufacturingmanagementsystem.service.Inventories;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryDTO;
import com.manufacturing.manufacturingmanagementsystem.models.InventoriesEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
// Author: Pham Hien Nhan
// this class is used to implement the methods declared in the IInventoriesServices interface
@Service
@AllArgsConstructor
public class InventoriesServices implements IInventoriesServices {

    private final InventoriesRepository inventoriesRepository;
    // this service is used to insert a new inventory
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
    // this service is used to get all inventories
    @Override
    public List<InventoriesEntity> getAllInventory() throws Exception {
        return inventoriesRepository.findAll();
    }
    // this service is used to update an inventory
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
    // this service is used to delete an inventory
    @Override
    public void deleteInventory(long id) throws Exception {

    }

}

