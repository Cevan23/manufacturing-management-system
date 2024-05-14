package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.MPS.MPSRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.MasterProductionSchedules.MasterProductionSchedulesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (mpsRequest.getPMId() == null) {
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

}
