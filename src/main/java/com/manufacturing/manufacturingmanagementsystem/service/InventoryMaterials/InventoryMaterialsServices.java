package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterials;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryMaterialDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.*;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoriesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoryMaterialDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@AllArgsConstructor
public class InventoryMaterialsServices implements IInventoryMaterialsServices {

    private final InventoryMaterialDetailsRepository inventoryMaterialDetailsRepository;

    private final InventoriesRepository inventoriesRepository;

    private final MaterialsRepository materialsRepository;
    @Override
    public InventoryMaterialDetailsEntity insertInventoryMaterialDetail(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception {

        long inventoryId = inventoryMaterialDetailsDTO.getInventoryId();
        long materialId = inventoryMaterialDetailsDTO.getMaterialId();

        Optional<InventoriesEntity> optionalInventory = inventoriesRepository.findById(inventoryId);
        Optional<MaterialsEntity> optionalMaterial = materialsRepository.findById(materialId);

        if(optionalMaterial.isPresent() && optionalInventory.isPresent()) {
            InventoriesEntity inventory = optionalInventory.get();
            MaterialsEntity material = optionalMaterial.get();

            InventoryMaterialDetailsEntity inventoryMaterialDetails = InventoryMaterialDetailsEntity.builder()
                    .id(new InventoryMaterialDetailEntityId(materialId, inventoryId))
                    .inventory(inventory)
                    .material(material)
                    .quantity(inventoryMaterialDetailsDTO.getQuantity())
                    .safetyStockAmount(inventoryMaterialDetailsDTO.getSafetyStockAmount())
                    .build();

            return inventoryMaterialDetailsRepository.save(inventoryMaterialDetails);

        } else {
            throw new Exception("Inventory Material already exists");
        }

    }

    @Override
    public List<InventoryMaterialDetailsEntity> getAllInventoryMaterial() {
        return inventoryMaterialDetailsRepository.findAll();
    }

    // Các phương thức service khác cần thiết
}