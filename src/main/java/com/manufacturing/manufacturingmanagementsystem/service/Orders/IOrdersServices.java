package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderRequest;
import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderUpdateRequest;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;
import java.util.Map;

public interface IOrdersServices {
    // Author: Nguyen Cao Nhan
    // This method is used to get all orders.
    OrdersEntity insertOrder(OrderRequest orderRequest);
    // Author: Nguyen Cao Nhan
    // This method is used to get all orders.
    List<Map<String, Object>> getAllOrders();
    // Author: Nguyen Cao Nhan
    // This method is used to get order by id.
    void deleteOrder(Long id);
    // Author: Nguyen Cao Nhan
    // This method is used to get order by id.
    OrdersEntity updateOrder(OrderUpdateRequest orderUpdateRequest);
}

