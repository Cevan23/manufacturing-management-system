package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.WorkOrders.WorkOrdersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/WorkOrder")
public class WorkOrderController {

    private final WorkOrdersServices workOrderService;

    @Autowired
    public WorkOrderController(WorkOrdersServices workOrderService) {
        this.workOrderService = workOrderService;
    }

    @PostMapping("/createWorkOrder")
    public ResponseEntity<ApiResponse> createWorkOrder(@RequestBody WorkOrderRequest workOrderRequest) {
        if (workOrderRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Invalid request")
                            .result(null)
                            .build());
        }
        try {
            Long id = workOrderService.createWorkOrder(workOrderRequest);
            if(id == null) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.builder()
                                .code(ErrorCode.BAD_REQUEST.getCode())
                                .message("Error creating work order")
                                .result(null)
                                .build());
            } else {
                return ResponseEntity.ok()
                        .body(ApiResponse.builder()
                                .message("Work order created successfully")
                                .result(id)
                                .build());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error creating work order")
                            .result(null)
                            .build());
        }
    }

    @PutMapping("/updateWorkOrder")
    @Transactional
    public ResponseEntity<ApiResponse> updateWorkOrder(@RequestBody WorkOrderRequest workOrderRequest) {
        if (workOrderRequest == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Invalid request")
                            .result(null)
                            .build());
        }
        try {
            workOrderService.updateWorkOrder(workOrderRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error updating work order")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order updated successfully")
                        .result(null)
                        .build());
    }

    @PutMapping("/updateWorkOrderStatus/{id}")
    @Transactional
    public ResponseEntity<ApiResponse> updateWorkOrderStatus(@PathVariable Long id, @RequestParam String status) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Work order id is required")
                            .result(null)
                            .build());
        }
        try {
            workOrderService.updateWorkOrderStatus(id, status);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error updating work order status")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order status updated successfully")
                        .result(null)
                        .build());
    }

    @DeleteMapping("/deleteWorkOrder/{id}")
    public ResponseEntity<ApiResponse> deleteWorkOrder(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Work order id is required")
                            .result(null)
                            .build());
        }
        try {
            workOrderService.deleteWorkOrder(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Error deleting work order")
                            .result(null)
                            .build());
        }
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .message("Work order deleted successfully")
                        .result(null)
                        .build());
    }

    @GetMapping("/getAllWorkOrders")
    public ResponseEntity<ApiResponse> getAllWorkOrders() {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Work orders retrieved successfully")
                            .result(workOrderService.getAllWorkOrders())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve work orders")
                            .result(null)
                            .build());
        }
    }

    @GetMapping("/getAllWorkOrdersOfPM")
    public ResponseEntity<ApiResponse> getAllWorkOrdersOfPM(@RequestParam Long pmID) {
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
                            .message("Work orders retrieved successfully")
                            .result(workOrderService.getAllWorkOrdersOfPM(pmID))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve work orders")
                            .result(null)
                            .build());
        }
    }

    @GetMapping("/getDetailAboutWorkOrder/{id}")
    public ResponseEntity<ApiResponse> getDetailAboutWorkOrder(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Work order id is required")
                            .result(null)
                            .build());
        }
        try {

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Work order details retrieved successfully")
                            .result(workOrderService.getWorkOrderById(id))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.builder()
                            .code(ErrorCode.BAD_REQUEST.getCode())
                            .message("Failed to retrieve work order details")
                            .result(null)
                            .build());
        }
    }

}
