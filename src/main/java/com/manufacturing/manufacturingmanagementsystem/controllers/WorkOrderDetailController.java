package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.WorkOrderDetails.WorkOrderDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Author: Pham Van Cao
// this class is used to handle the work order detail
@RestController
@RequestMapping("/api/WorkOrderDetail")
public class WorkOrderDetailController {

    private final WorkOrderDetailsServices workOrderDetailService;

    @Autowired
    public WorkOrderDetailController(WorkOrderDetailsServices workOrderDetailService) {
        this.workOrderDetailService = workOrderDetailService;
    }
    // Author: Pham Van Cao
    // this method is used to create the work order detail
    @PostMapping("/createWorkOrderDetail")
    public ResponseEntity<ApiResponse> createWorkOrderDetail(@RequestBody List<WorkOrderDetailRequest> workOrderDetailRequest) {
        if (workOrderDetailRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Invalid request")
                            .result(null)
                            .build());
        }
        try {
            for (WorkOrderDetailRequest workOrderDetail : workOrderDetailRequest) {
                System.out.println("WO detail: "+ workOrderDetail);
                workOrderDetailService.createWorkOrderDetails(workOrderDetail);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error creating work order detail")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order detail created successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to update the work order detail
    @PutMapping("/updateWorkOrderDetail")
    public ResponseEntity<ApiResponse> updateWorkOrderDetail(@RequestBody List<WorkOrderDetailRequest> workOrderDetailRequest) {
        if (workOrderDetailRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Invalid request")
                            .result(null)
                            .build());
        }
        try {
            for (WorkOrderDetailRequest workOrderDetail : workOrderDetailRequest) {

                workOrderDetailService.updateWorkOrderDetails(workOrderDetail);
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error updating work order detail")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order detail updated successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to delete the work order detail
    @DeleteMapping("/deleteWorkOrderDetail/")
    public ResponseEntity<ApiResponse> deleteWorkOrderDetail(@RequestBody WorkOrderDetailRequest workOrderDetailRequest) {
        if (workOrderDetailRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Invalid request")
                            .result(null)
                            .build());
        }
        try {
            workOrderDetailService.deleteWorkOrderDetails(workOrderDetailRequest.getWorkOrderId(), workOrderDetailRequest.getMasterProductionScheduleId());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error deleting work order detail")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order detail deleted successfully")
                        .result(null)
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to get all the work order detail
    @GetMapping("/getAllWorkOrderDetails")
    public ResponseEntity<ApiResponse> getAllWorkOrderDetails() {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order details retrieved successfully")
                        .result(workOrderDetailService.getAllWorkOrderDetails())
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to get the work order detail by id
    @GetMapping("/getWorkOrderDetail/{id}")
    public ResponseEntity<ApiResponse> getWorkOrderDetail(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Work order id is required")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order detail retrieved successfully")
                        .result(workOrderDetailService.getWorkOrderDetailsOfWO(id))
                        .build());
    }
    // Author: Pham Van Cao
    // this method is used to get the work order detail by master production schedule id
    @GetMapping("/sumProjectedProductionByMasterProductionSchedule/{id}")
    public ResponseEntity<ApiResponse> sumProjectedProductionByMasterProductionSchedule(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Master production schedule id is required")
                            .result(null)
                            .build());
        }
        Integer sum = workOrderDetailService.sumProjectedProductionByMasterProductionSchedule(id);
        if (sum == null) {
            sum = 0;
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Projected production sum retrieved successfully")
                        .result(sum)
                        .build());
    }


}
