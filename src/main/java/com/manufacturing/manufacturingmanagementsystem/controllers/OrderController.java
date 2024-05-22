package com.manufacturing.manufacturingmanagementsystem.controllers;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ApiResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Order.OrderListResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.ResponseObject;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.UserResponse;
import com.manufacturing.manufacturingmanagementsystem.mapper.RoleMapper;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;
import com.manufacturing.manufacturingmanagementsystem.service.Orders.OrdersServices;
import com.manufacturing.manufacturingmanagementsystem.service.Users.UsersServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrdersServices ordersServices;

    @GetMapping("")
    public ResponseEntity<?> getAllOrder() {
        try {
            List<Map<String, Object>> ordersEntityList = ordersServices.getAllOrders();
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Get all orders successfull")
                    .result(ordersEntityList)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> insertOrder(@Valid @RequestBody OrderRequest orderRequest) {
        try {
            OrdersEntity ordersEntity = ordersServices.insertOrder(orderRequest);

            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Add order successfully")
                    .result(ordersEntity)
                    .build());

        } catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrder(@Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        try {
            OrdersEntity order = ordersServices.updateOrder(orderUpdateRequest);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("update order successfully")
                    .result(order)
                    .build());
        }catch (Exception e) {
            // Xử lý lỗi và trả về phản hồi lỗi (status code 500)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        try {
            ordersServices.deleteOrder(id);
            return ResponseEntity.ok().body(ApiResponse.builder()
                    .message("Successfully delete order")
                    .build());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
