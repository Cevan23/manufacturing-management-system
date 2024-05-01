package com.manufacturing.manufacturingmanagementsystem.service.Materials;

import com.manufacturing.manufacturingmanagementsystem.dtos.MaterialsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.MaterialsEntity;

public interface IMaterialsServices {

    MaterialsEntity createMaterial(MaterialsDTO material);

    MaterialsEntity findMaterialByName(String name);

    MaterialsDTO findMaterialById(Long id);
}

