package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;

import java.util.List;

public interface IBOMsServices {

    BOMsEntity createBOM(BOMsDTO bomRequest);

    BOMsEntity findBOMByName(String name);

    Boolean deleteBOM(Long id);

    List<BOMsEntity> getAllBOMs();

    List<BOMsEntity> getAllBOMsbyStatus(String status);

    List<BOMsEntity> getBOMsByLikeName(String name);
}

