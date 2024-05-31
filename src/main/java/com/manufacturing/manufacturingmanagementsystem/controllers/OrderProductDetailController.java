package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderProductDetail.OrderProductDetailRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.OrderProductDetail.OrderProductDetailUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.service.OrderProductDetails.OrderProductDetailsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Author: Nguyen Cao Nhan
@RestController
@RequestMapping("/api/order_product_detail")
@RequiredArgsConstructor
public class OrderProductDetailController {
    private final OrderProductDetailsServices orderProductDetailsServices;
    // Author: Nguyen Cao Nhan
    // Insert Order Product Detail Controller
    @PostMapping("/create")
    public ResponseEntity<?> insertOrderProductDetail(@Valid @RequestBody OrderProductDetailRequest orderProductDetailRequest) {
        try {

            List<Map<String, Object>> list_order_product_detail = orderProductDetailsServices.insertOrderProductDetail(orderProductDetailRequest.getOid(),orderProductDetailRequest.getPids(),orderProductDetailRequest.getQuantities());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Add successfully")
                    .result(list_order_product_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Author: Nguyen Cao Nhan
    // Update Order Product Detail Controller
    @PutMapping("")
    public ResponseEntity<?> updateOrderProductDetail(@Valid @RequestBody OrderProductDetailUpdateRequest orderProductDetailUpdateRequest) {
        try {

            Map<String, Object> order_product_detail = orderProductDetailsServices.updateOrderProductDetail(orderProductDetailUpdateRequest.getOid(),orderProductDetailUpdateRequest.getPid(),orderProductDetailUpdateRequest.getQuantity());
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Update successfully")
                    .result(order_product_detail)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Get Order Product Detail By Id Controller
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderProductDetailById(@PathVariable Long id) {
        try {
            List<Map<String, Object>> order_product_detail_list = orderProductDetailsServices.findOrderProductDetailById(id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get successfully")
                    .result(order_product_detail_list)
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Author: Nguyen Cao Nhan
    // Delete Order Product Detail Controller
    @DeleteMapping("/{oid}")
    public ResponseEntity<?> DeleteOrderProducttDetail(@PathVariable Long oid,
                                                      @Valid @RequestParam("pid") Long pid) {
        try {
            orderProductDetailsServices.deleteOrderProductDetail(pid,oid);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Successfully delete order product detail")
                    .build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
