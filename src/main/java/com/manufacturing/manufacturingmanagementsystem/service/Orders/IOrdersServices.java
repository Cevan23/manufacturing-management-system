package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;
import java.util.Map;

public interface IOrdersServices {
    OrdersEntity insertOrder(OrderRequest orderRequest);
    List<Map<String, Object>> getAllOrders();
    void deleteOrder(Long id);
    OrdersEntity updateOrder(OrderUpdateRequest orderUpdateRequest);
}

