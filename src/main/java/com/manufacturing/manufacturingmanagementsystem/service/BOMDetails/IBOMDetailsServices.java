package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMDetailRequest;

import java.util.List;

public interface IBOMDetailsServices {

    void createBOMDetails(BOMDetailRequest bomDetails);

    void deleteBOMDetailsByBOMId(Long bomId);

    List<BOMDetailsDTO> getBOMDetailsByBOMId(Long bomId);

}

