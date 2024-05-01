package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.dtos.UsersDTO;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.UsersEntity;

import java.util.List;
import java.util.Map;

public interface IOrdersServices {
    List<Map<String, Object>> getAllOrders();
    OrdersEntity insertOrder(UsersDTO userForm);
    OrdersEntity updateOrder(long id,UsersDTO userForm);
    void deleteOrder(Long id);
}

