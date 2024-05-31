package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail.OrderMaterialDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderMaterialDetail.OrderMaterialDetailUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.service.OrderMaterialDetails.OrderMaterialDetailsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Author: Nguyen Cao Nhan
@RestController
@RequestMapping("/api/order_material_detail")
@RequiredArgsConstructor
public class OrderMaterialDetailController {
    private final OrderMaterialDetailsServices orderMaterialDetailsServices;
    // Author: Nguyen Cao Nhan
    // Insert Order Material Detail Controller
    @PostMapping("/create")
    public ResponseEntity<?> insertOrderMaterialDetail(@Valid @RequestBody OrderMaterialDetailRequest orderMaterialDetailRequest) {
        try {

            List<Map<String, Object>> list_order_material_detail = orderMaterialDetailsServices.insertOrderMaterialDetail(orderMaterialDetailRequest.getOid(),orderMaterialDetailRequest.getMids(),orderMaterialDetailRequest.getQuantities());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Add successfully")
                    .result(list_order_material_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Author: Nguyen Cao Nhan
    // Update Order Material Detail Controller
    @PutMapping("")
    public ResponseEntity<?> updateOrderMaterialDetail(@Valid @RequestBody OrderMaterialDetailUpdateRequest orderMaterialDetailUpdateRequest) {
        try {

            Map<String, Object> order_material_detail = orderMaterialDetailsServices.updateOrderMaterialDetail(orderMaterialDetailUpdateRequest.getOid(),orderMaterialDetailUpdateRequest.getMid(),orderMaterialDetailUpdateRequest.getQuantity());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Update successfully")
                    .result(order_material_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Author: Nguyen Cao Nhan
    // Get Order Material Detail By Id Controller
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderMaterialDetailById(@PathVariable Long id) {
        try {
            List<Map<String, Object>> order_material_detail_list = orderMaterialDetailsServices.findOrderMaterialDetailById(id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get successfully")
                    .result(order_material_detail_list)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Delete Order Material Detail Controller
    @DeleteMapping("/{oid}")
    public ResponseEntity<?> DeleteOrderMaterialtDetail(@PathVariable Long oid,
                                                       @Valid @RequestParam("mid") Long mid) {
        try {
            orderMaterialDetailsServices.deleteOrderMaterialDetail(mid,oid);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Successfully delete order material detail")
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

