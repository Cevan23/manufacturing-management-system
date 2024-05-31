package com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules;

import com.manufacturing.manufacturingmanagementsystem.dtos.MasterProductionSchedulesDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS.MPSResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.MPS.MPSSuggestionMonthlyResponse;

import java.sql.Date;
import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the IMasterProductionSchedulesServices response
public interface IMasterProductionSchedulesServices {
    void createMPS(MPSRequest mpsRequest);

    void updateMPS(MPSUpdateRequest mpsRequest);

    MPSResponse getById(Long id);

    List<MPSResponse> getAllMPSofPM(Long pmID);

    List<MPSResponse> getALl();

    void deleteMPS(Long id);

    MPSSuggestionMonthlyResponse suggestMPSMonthly(Long productId, Date month);

    List<MasterProductionSchedulesDTO> getAllMPSbyInProgress(Float inProgress);
}

