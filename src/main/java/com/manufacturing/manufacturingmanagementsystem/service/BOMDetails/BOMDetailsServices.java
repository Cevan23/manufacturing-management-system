package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.exceptions.AppException;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class BOMDetailsServices implements IBOMDetailsServices {

    private final BOMDetailsRepository bomDetailsRepository;
    private final BOMsRepository bomsRepository;
    private final MaterialsRepository materialsRepository;

    @Override
    public void createBOMDetails(BOMDetailRequest bomDetails) {
        if(bomDetails.getBOMId() == null || bomDetails.getMaterial() == null) {
            return;
        }
        System.out.println("BOM detail : " + bomDetails);
        BOMsEntity bom = bomsRepository.findById(bomDetails.getBOMId())
                .orElseThrow(() -> new RuntimeException("BOM not found with id " + bomDetails.getBOMId()));
        MaterialsEntity material = materialsRepository.findByName(bomDetails.getMaterial().getMaterialName())
                .orElseThrow(() -> new RuntimeException("Material not found with id " + bomDetails.getMaterial().getMaterialName()));

        try {
            BOMDetailsEntity bomDetailsEntity = BOMDetailsEntity.builder()
                    .id(new BOMDetailEntityId(bomDetails.getBOMId(), material.getId()))
                    .BOM(bom)
                    .material(material)
                    .quantity(bomDetails.getQuantity())
                    .totalUnitPrice(bomDetails.getTotalUnitPrice())
                    .build();

            bomDetailsRepository.save(bomDetailsEntity);
        } catch (Exception e) {
            throw new AppException(ErrorCode.BAD_REQUEST);
        }

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
