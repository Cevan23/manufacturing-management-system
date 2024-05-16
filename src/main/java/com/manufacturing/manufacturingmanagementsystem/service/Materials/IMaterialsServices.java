package com.manufacturing.manufacturingmanagementsystem.service.Materials;

import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;

import java.util.List;

public interface IMaterialsServices {

    void createMaterial(MaterialsDTO material);

    void updateMaterial(MaterialsDTO material);

    void deleteMaterial(Long id);

    List<MaterialsEntity> findAllMaterials();

    List<MaterialsEntity> findAlikeName(String name);

    MaterialsEntity findMaterialByName(String name);

    MaterialsDTO findMaterialById(Long id);
}

