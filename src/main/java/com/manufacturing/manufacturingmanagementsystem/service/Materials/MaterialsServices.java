package com.manufacturing.manufacturingmanagementsystem.service.Materials;

import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MaterialsServices implements IMaterialsServices {

    private final MaterialsRepository materialsRepository;

    @Override
    public MaterialsEntity createMaterial(MaterialsDTO materialDto)  {

        // Convert DTO to entity
        MaterialsEntity material = new MaterialsEntity();
        material.setName(materialDto.getName());
        material.setPrice(materialDto.getPrice());
        material.setUnit(materialDto.getUnit());
        material.setVolume(materialDto.getVolume());

        // Save entity in the database
        return materialsRepository.save(material);
    }

    @Override
    public MaterialsEntity findMaterialByName(String name){
        return materialsRepository.findByName(name).orElse(null);
    }

    @Override
    public MaterialsDTO findMaterialById(Long id){
        MaterialsEntity materialEntity = materialsRepository.findById(id).orElse(null);
        if (materialEntity == null) {
            return null;
        }

        // Convert entity to DTO
        MaterialsDTO materialDto = new MaterialsDTO();
        materialDto.setName(materialEntity.getName());
        materialDto.setPrice(materialEntity.getPrice());
        materialDto.setUnit(materialEntity.getUnit());
        materialDto.setVolume(materialEntity.getVolume());

        return materialDto;
    }


}

