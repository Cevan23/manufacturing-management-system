package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Order;

import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Customer.CustomerResponse;
import com.manufacturing.manufacturingmanagementsystem.dtos.responses.Product.ProductListResponse;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.ProductsEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import com.manufacturing.manufacturingmanagementsystem.service.Orders.OrdersServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Date dateStart;

    private Date dateEnd;

    private String kindOrder;

    private Float totalPrice;

    private String orderStatus;

    private CustomerResponse customerResponse;

    private ProductListResponse productListResponse;
    public static OrderResponse fromOrder(OrdersEntity order) {
        return OrderResponse.builder()
                .dateStart(order.getDateStart())
                .dateEnd(order.getDateEnd())
                .kindOrder(order.getKindOrder())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getOrderStatus())
                .customerResponse(CustomerResponse.fromCustomer(order.getCustomer()))
                .build();
    }
}
