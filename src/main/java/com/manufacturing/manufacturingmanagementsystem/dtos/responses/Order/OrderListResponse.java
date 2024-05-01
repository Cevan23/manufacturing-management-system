package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Order;

import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;

import java.util.List;

public class OrderListResponse {

    private final List<OrderResponse> orders;

    public OrderListResponse(List<OrderResponse> orders) {
        this.orders = orders;
    }

    public static OrderListResponse fromOrderList(List<OrdersEntity> orderEntities) {
        List<OrderResponse> orderResponses = orderEntities.stream()
                .map(OrderResponse::fromOrder)
                .toList();

        return new OrderListResponse(orderResponses);
    }

    public List<OrderResponse> getOrders() {
        return orders;
    }
}
