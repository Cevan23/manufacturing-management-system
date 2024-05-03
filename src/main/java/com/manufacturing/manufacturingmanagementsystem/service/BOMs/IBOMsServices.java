package com.manufacturing.manufacturingmanagementsystem.service.BOMs;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMsEntity;

public interface IBOMsServices {

    BOMsEntity createBOM(BOMsDTO bomRequest);

    BOMsEntity findBOMByName(String name);

    Boolean deleteBOM(Long id);
}

