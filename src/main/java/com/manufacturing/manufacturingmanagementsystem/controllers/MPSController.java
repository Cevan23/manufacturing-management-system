package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.MasterProductionSchedulesDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.SuggestMPSMonthlyRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules.MasterProductionSchedulesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Author: Pham Van Cao
// this class is used to handle the MPS request
@RestController
@RequestMapping("/api/MPS")
public class MPSController {

    private final MasterProductionSchedulesServices masterProductionSchedulesServices;

    @Autowired
    public MPSController(MasterProductionSchedulesServices masterProductionSchedulesServices) {
        this.masterProductionSchedulesServices = masterProductionSchedulesServices;
    }
    // Author: Pham Van Cao
    // this method is used to create the MPS
    @PostMapping("/createMPS")
    public ResponseEntity<ApiResponse> createMPS(@RequestBody MPSRequest mpsRequest) {

        if (mpsRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("MPS request is required")
                            .result(null)
                            .build());
        }
        if (mpsRequest.getProduct_manager_ID() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("PMId is required")
                            .result(null)
                            .build());
        }
        if (mpsRequest.getProductId() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .message("ProductId is required")
                            .result(null)
                            .build());
        }
        try {
            masterProductionSchedulesServices.createMPS(mpsRequest);
        }catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to create MPS")
                            .result(null)
                            .build());
        }

        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("MPS created successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to update the MPS
    @PutMapping("/updateMPS")
    public ResponseEntity<ApiResponse> updateMPS(@RequestBody MPSUpdateRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("MPS request is required")
                            .result(null)
                            .build());
        }
        if (request.getMpsID() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("MPS ID is required")
                            .result(null)
                            .build());
        }
        try {
            masterProductionSchedulesServices.updateMPS(request);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to update MPS")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("MPS updated successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to get the MPS by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("MPS ID is required")
                            .result(null)
                            .build());
        }
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("MPS retrieved successfully")
                            .result(masterProductionSchedulesServices.getById(id))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve MPS")
                            .result(null)
                            .build());
        }
    }
    // Author: Pham Van Cao
    // this method is used to get all the MPS of the PM
    @GetMapping("/getAllMPSofPM/{pmID}")
    public ResponseEntity<ApiResponse> getAllMPSofPM(@PathVariable Long pmID) {
        if (pmID == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("PM ID is required")
                            .result(null)
                            .build());
        }
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("MPS retrieved successfully")
                            .result(masterProductionSchedulesServices.getAllMPSofPM(pmID))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve MPS")
                            .result(null)
                            .build());
        }
    }
    // Author: Pham Van Cao
    // this method is used to get all the MPS
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAll() {
        try {

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("MPS retrieved successfully")
                            .result(masterProductionSchedulesServices.getALl())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve MPS")
                            .result(null)
                            .build());
        }
    }
    // Author: Pham Van Cao
    // this method is used to delete the MPS
    @DeleteMapping("/deleteMPS/{id}")
    public ResponseEntity<ApiResponse> deleteMPS(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("MPS ID is required")
                            .result(null)
                            .build());
        }
        try {
            masterProductionSchedulesServices.deleteMPS(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to delete MPS")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("MPS deleted successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to suggest the MPS monthly
    @GetMapping("/suggestMPSMonthly")
    public ResponseEntity<ApiResponse> suggestMPSMonthly(@RequestBody SuggestMPSMonthlyRequest request) {
        if (request.getProductId() == null || request.getMonth() == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("id and time is required")
                            .result(null)
                            .build());
        }
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("MPS retrieved successfully")
                            .result(masterProductionSchedulesServices.suggestMPSMonthly(request.getProductId(), request.getMonth()))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve MPS")
                            .result(null)
                            .build());
        }
    }
    // Author: Pham Van Cao
    // this method is used to get all the MPS by in progress
    @GetMapping("/getAllMPSbyInProgress")
    public ResponseEntity<ApiResponse> getAllMPSbyInProgress(@RequestParam Float inProgress) {
        if (inProgress == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("inProgress is required")
                            .result(null)
                            .build());
        }
        try {
            List<MasterProductionSchedulesDTO> mpsList = masterProductionSchedulesServices.getAllMPSbyInProgress(inProgress);
            if (mpsList == null || mpsList.isEmpty()) {
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.NOT_FOUND.getCode())
                                .message("No items found")
                                .result(null)
                                .build());
            }
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("MPS retrieved successfully")
                            .result(mpsList)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve MPS")
                            .result(null)
                            .build());
        }
    }



}
