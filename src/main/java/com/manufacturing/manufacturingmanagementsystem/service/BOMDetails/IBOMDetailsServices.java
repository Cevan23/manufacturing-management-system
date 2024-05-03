package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.models.BOMDetailsEntity;

import java.util.List;

public interface IBOMDetailsServices {

    BOMDetailsDTO createBOMDetails(BOMDetailsDTO bomDetails);

    void deleteBOMDetailsByBOMId(Long bomId);

    List<BOMDetailsEntity> getBOMDetailsByBOMId(Long bomId);

}

