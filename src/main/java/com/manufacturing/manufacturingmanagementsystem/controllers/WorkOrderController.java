package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder.WorkOrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.WorkOrder.WorkOrderDetailResponse;
import com.manufacturing.manufacturingmanagementsystem.exceptions.ErrorCode;
import com.manufacturing.manufacturingmanagementsystem.service.WorkOrders.WorkOrdersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
// Author: Pham Van Cao
// this class is used to handle the work order
@RestController
@RequestMapping("/api/WorkOrder")
public class WorkOrderController {

    private final WorkOrdersServices workOrderService;

    @Autowired
    public WorkOrderController(WorkOrdersServices workOrderService) {
        this.workOrderService = workOrderService;
    }
    // Author: Pham Van Cao
    // this method is used to create the work order
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
    // Author: Pham Van Cao
    // this method is used to update the work order
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
    // Author: Pham Van Cao
    // this method is used to update the work order status
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
    // Author: Pham Van Cao
    // this method is used to delete the work order
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
    // Author: Pham Van Cao
    // this method is used to get all the work orders starting today
    @GetMapping("/getAllWorkOrdersStartingToday")
    public ResponseEntity<ApiResponse> getAllWorkOrdersStartingToday() {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Work orders retrieved successfully")
                            .result(workOrderService.getAllWorkOrdersStartingToday())
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
    // Author: Pham Van Cao
    // this method is used to get all the work orders
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
    // Author: Pham Van Cao
    // this method is used to get all the work orders of the product manager
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
    // Author: Pham Van Cao
    // this method is used to get the work order by id
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
            var data = workOrderService.getWorkOrderById(id);
            var workOrderData = data.getWorkOrder();

            // Create a new WorkOrderRequest and set the properties
            WorkOrderRequest workOrder = new WorkOrderRequest();
            workOrder.setId(workOrderData.getId());
            workOrder.setProductManagerID(workOrderData.getProductManager().getId());
            workOrder.setDateStart(workOrderData.getDateStart());
            workOrder.setDateEnd(workOrderData.getDateEnd());
            workOrder.setWorkOrderStatus(workOrderData.getWorkOrderStatus());

            var workOrderDetailsData = data.getWorkOrderDetails();

            // Create a new list to hold the WorkOrderDetailRequest objects
            List<WorkOrderDetailRequest> workOrderDetails = new ArrayList<>();

            // Loop through the work order details data and create new WorkOrderDetailRequest objects
            for (var detailData : workOrderDetailsData) {
                WorkOrderDetailRequest detailRequest = new WorkOrderDetailRequest();

                // Set the properties of the detailRequest object
                detailRequest.setWorkOrderId(detailData.getId().getWorkOrderId());
                detailRequest.setMasterProductionScheduleId(detailData.getId().getMasterProductionScheduleId());
                detailRequest.setProductPrice(detailData.getMasterProductionSchedule().getProducts().getPrice());
                detailRequest.setActualProduction(detailData.getActualProduction());
                detailRequest.setActualProductionPrice(detailData.getActualProductionPrice());
                detailRequest.setFaultyProducts(detailData.getFaultyProducts());
                detailRequest.setFaultyProductPrice(detailData.getFaultyProductPrice());
                detailRequest.setNote(detailData.getNote());
                detailRequest.setProjectedProduction(detailData.getProjectedProduction());

                // Add the detailRequest to the list
                workOrderDetails.add(detailRequest);
            }



            WorkOrderDetailResponse response = WorkOrderDetailResponse.builder()
                    .workOrder(workOrder)
                    .workOrderDetails(workOrderDetails)
                    .build();

            return ResponseEntity.ok()
                    .body(ApiResponse.builder()
                            .message("Work order details retrieved successfully")
                            .result(response)
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
