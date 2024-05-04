package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMDetailsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.BOMsRepository;
import com.manufacturing.manufacturingmanagementsystem.repositories.ID.BOMDetailEntityId;
import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BOMDetailsServices implements IBOMDetailsServices {

    private final BOMDetailsRepository bomDetailsRepository;
    private final BOMsRepository bomsRepository;
    private final MaterialsRepository materialsRepository;

    @Override
    public BOMDetailsDTO createBOMDetails(BOMDetailsDTO bomDetails){

        // Fetch the BOMsEntity and MaterialsEntity from the database
        BOMsEntity bom = bomsRepository.findById(bomDetails.getBOMId())
                .orElseThrow(() -> new RuntimeException("BOM not found with id " + bomDetails.getBOMId()));
        MaterialsEntity material = materialsRepository.findById(bomDetails.getMaterialId())
                .orElseThrow(() -> new RuntimeException("Material not found with id " + bomDetails.getMaterialId()));

        // Create the BOMDetailsEntity
        BOMDetailsEntity bomDetailsEntity = new BOMDetailsEntity();
        bomDetailsEntity.setId(new BOMDetailEntityId(bomDetails.getBOMId(), bomDetails.getMaterialId()));
        bomDetailsEntity.setBOM(bom);
        bomDetailsEntity.setMaterial(material);
        bomDetailsEntity.setQuantity(bomDetails.getQuantity());
        bomDetailsEntity.setTotalUnitPrice(bomDetails.getTotalUnitPrice());

        // Save the entity in the database
        BOMDetailsEntity savedEntity = bomDetailsRepository.save(bomDetailsEntity);

        // Convert the saved entity back to a DTO
        BOMDetailsDTO savedDto = new BOMDetailsDTO();
        savedDto.setBOMId(savedEntity.getBOM().getId());
        savedDto.setMaterialId(savedEntity.getMaterial().getId());
        savedDto.setQuantity(savedEntity.getQuantity());
        savedDto.setTotalUnitPrice(savedEntity.getTotalUnitPrice());

        return savedDto;
    }

    @Override
    public void deleteBOMDetailsByBOMId(Long bomId) {
        try{
            bomDetailsRepository.deleteByBOMId(bomId);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete BOM details with BOM id " + bomId);
        }
    }

    @Override
    public List<BOMDetailsDTO> getBOMDetailsByBOMId(Long bomId) {
        List<BOMDetailsEntity> bomDetailsEntities = bomDetailsRepository.findByBOMId(bomId);
        List<BOMDetailsDTO> bomDetailsDTOs = new ArrayList<>();

        for (BOMDetailsEntity bomDetailsEntity : bomDetailsEntities) {
            BOMDetailsDTO bomDetailsDTO = new BOMDetailsDTO();

            bomDetailsDTO.setBOMId(bomDetailsEntity.getBOM().getId());
            bomDetailsDTO.setMaterialId(bomDetailsEntity.getMaterial().getId());
            bomDetailsDTO.setQuantity(bomDetailsEntity.getQuantity());
            bomDetailsDTO.setTotalUnitPrice(bomDetailsEntity.getTotalUnitPrice());

            bomDetailsDTOs.add(bomDetailsDTO);
        }

        return bomDetailsDTOs;
    }



}
