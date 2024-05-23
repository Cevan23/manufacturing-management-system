package com.manufacturing.manufacturingmanagementsystem.service.InventoryMaterialDetails;

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
public class InventoryMaterialDetailsServices implements IInventoryMaterialDetailsServices {

    private final InventoryMaterialDetailsRepository inventoryMaterialDetailsRepository;

    private final InventoriesRepository inventoriesRepository;

    private final MaterialsRepository materialsRepository;
    @Override
    public InventoryMaterialDetailsEntity insertInventoryMaterialDetail(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception {

        long inventoryId = inventoryMaterialDetailsDTO.getInventoryId();
        long materialId = inventoryMaterialDetailsDTO.getMaterialId();

        Optional<InventoryMaterialDetailsEntity> optionalInventoryMaterial
                = inventoryMaterialDetailsRepository.findById(new InventoryMaterialDetailEntityId(materialId, inventoryId));

        if(optionalInventoryMaterial.isPresent()) {
            throw new Exception("Inventory Material Already Exists");
        }

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

    @Override
    public InventoryMaterialDetailsEntity updateInventoryMaterial(InventoryMaterialDetailsDTO inventoryMaterialDetailsDTO) throws Exception {

        Optional<InventoryMaterialDetailsEntity>
                optionalInventoryMaterial =
                inventoryMaterialDetailsRepository
                        .findById(new InventoryMaterialDetailEntityId(inventoryMaterialDetailsDTO.getMaterialId(),
                                inventoryMaterialDetailsDTO.getInventoryId()));

        if(optionalInventoryMaterial.isPresent()) {
            InventoryMaterialDetailsEntity existingInventoryMaterial = optionalInventoryMaterial.get();

            if(inventoryMaterialDetailsDTO.getQuantity() != null) {
                existingInventoryMaterial.setQuantity(inventoryMaterialDetailsDTO.getQuantity());
            }

            if(inventoryMaterialDetailsDTO.getSafetyStockAmount() != null) {
                existingInventoryMaterial.setQuantity(inventoryMaterialDetailsDTO.getSafetyStockAmount());
            }

            return inventoryMaterialDetailsRepository.save(existingInventoryMaterial);
        } else {
            throw new Exception("Cannot find Inventory Material");
        }

    }

    @Override
    public void deleteInventoryMaterial(long materialId, long inventoryId) throws Exception {

        Optional<InventoryMaterialDetailsEntity> optionalInventoryMaterial
                = inventoryMaterialDetailsRepository.findById(new InventoryMaterialDetailEntityId(materialId, inventoryId));

        optionalInventoryMaterial.ifPresent(inventoryMaterialDetailsRepository::delete);
    }

    // Các phương thức service khác cần thiết
}