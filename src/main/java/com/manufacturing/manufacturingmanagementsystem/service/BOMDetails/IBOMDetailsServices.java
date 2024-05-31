package com.manufacturing.manufacturingmanagementsystem.service.BOMDetails;

import com.manufacturing.manufacturingmanagementsystem.dtos.BOMDetailsDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.BOM.BOMDetailRequest;

import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the IBOMDetailsServices response
public interface IBOMDetailsServices {

    void createBOMDetails(BOMDetailRequest bomDetails);

    void deleteBOMDetailsByBOMId(Long bomId);

    List<BOMDetailsDTO> getBOMDetailsByBOMId(Long bomId);

    void deleteBOMDetail(Long bomId, Long materialId);
}

