package com.manufacturing.manufacturingmanagementsystem.service.OrderDetails;

import com.manufacturing.manufacturingmanagementsystem.repositories.OrderDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDetailsServices implements IOrderDetailsServices {

    private final OrderDetailsRepository orderDetailsRepository;

    // Các phương thức service khác cần thiết
}

