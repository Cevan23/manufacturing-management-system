package com.manufacturing.manufacturingmanagementsystem.service.Orders;

import com.manufacturing.manufacturingmanagementsystem.repositories.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdersServices implements IOrdersServices {

    private final OrdersRepository ordersRepository;

    // Các phương thức service khác cần thiết
}

