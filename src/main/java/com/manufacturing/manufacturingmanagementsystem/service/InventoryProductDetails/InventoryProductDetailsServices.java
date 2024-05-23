package com.manufacturing.manufacturingmanagementsystem.service.InventoryProductDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.InventoryProductDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.*;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryMaterialDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.InventoryProductDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoriesRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.InventoryProductDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryProductDetailsServices implements IInventoryProductDetailsServices {

    private final InventoryProductDetailsRepository inventoryProductDetailsRepository;

    private final InventoriesRepository inventoriesRepository;

    private final ProductsRepository productsRepository;
    @Override
    public InventoryProductDetailsEntity insertInventoryProductDetail(InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception {

        long inventoryId = inventoryProductDetailsDTO.getInventoryId();
        long productId = inventoryProductDetailsDTO.getProductId();

        Optional<InventoryProductDetailsEntity> optionalInventoryProduct
                = inventoryProductDetailsRepository.findById(new InventoryProductDetailEntityId(productId, inventoryId));

        if(optionalInventoryProduct.isPresent()) {
            throw new Exception("Inventory Product Already Exists");
        }

        Optional<InventoriesEntity> optionalInventory = inventoriesRepository.findById(inventoryId);
        Optional<ProductsEntity> optionalProduct = productsRepository.findById(productId);

        if(optionalProduct.isPresent() && optionalInventory.isPresent()) {
            InventoriesEntity inventory = optionalInventory.get();
            ProductsEntity product = optionalProduct.get();

            InventoryProductDetailsEntity inventoryProductDetails = InventoryProductDetailsEntity.builder()
                    .id(new InventoryProductDetailEntityId(productId, inventoryId))
                    .inventory(inventory)
                    .product(product)
                    .quantity(inventoryProductDetailsDTO.getQuantity())
                    .safetyStockAmount(inventoryProductDetailsDTO.getSafetyStockAmount())
                    .build();

            return inventoryProductDetailsRepository.save(inventoryProductDetails);

        } else {
            throw new Exception("Inventory Material already exists");
        }

    }

    @Override
    public List<InventoryProductDetailsEntity> getAllInventoryProduct() {
        return inventoryProductDetailsRepository.findAll();
    }

    @Override
    public InventoryProductDetailsEntity updateInventoryProduct(
            InventoryProductDetailsDTO inventoryProductDetailsDTO) throws Exception {

        Optional<InventoryProductDetailsEntity>
                optionalInventoryProductDetails = inventoryProductDetailsRepository.findById(new InventoryProductDetailEntityId(inventoryProductDetailsDTO.getProductId(), inventoryProductDetailsDTO.getInventoryId()));

        if(optionalInventoryProductDetails.isPresent()) {
            InventoryProductDetailsEntity existingInventoryProduct = optionalInventoryProductDetails.get();

            if(inventoryProductDetailsDTO.getQuantity() != null) {
                existingInventoryProduct.setQuantity(inventoryProductDetailsDTO.getQuantity());
            }

            if(inventoryProductDetailsDTO.getSafetyStockAmount() != null) {
                existingInventoryProduct.setQuantity(inventoryProductDetailsDTO.getSafetyStockAmount());
            }

            return inventoryProductDetailsRepository.save(existingInventoryProduct);
        } else {
            throw new Exception("Cannot find Inventory Product");
        }
    }

    @Override
    public void deleteInventoryProduct(long productId, long inventoryId) throws Exception {

        Optional<InventoryProductDetailsEntity> optionalInventoryProduct
                = inventoryProductDetailsRepository.findById(new InventoryProductDetailEntityId(productId, inventoryId));

        optionalInventoryProduct.ifPresent(inventoryProductDetailsRepository::delete);

    }

    // Các phương thức service khác cần thiết
}

