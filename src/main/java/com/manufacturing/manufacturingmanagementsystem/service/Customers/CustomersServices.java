package com.manufacturing.manufacturingmanagementsystem.service.Customers;

import com.manufacturing.manufacturingmanagementsystem.repositories.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServices implements ICustomersServices {

    private final CustomersRepository customersRepository;

    // Các phương thức service khác cần thiết
}

