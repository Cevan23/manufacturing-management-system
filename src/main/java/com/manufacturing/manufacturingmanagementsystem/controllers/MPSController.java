package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.SuggestMPSMonthlyRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules.MasterProductionSchedulesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/MPS")
public class MPSController {

    private final MasterProductionSchedulesServices masterProductionSchedulesServices;

    @Autowired
    public MPSController(MasterProductionSchedulesServices masterProductionSchedulesServices) {
        this.masterProductionSchedulesServices = masterProductionSchedulesServices;
    }

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


}
