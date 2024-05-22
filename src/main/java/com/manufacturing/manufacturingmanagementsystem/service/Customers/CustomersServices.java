package com.manufacturing.manufacturingmanagementsystem.service.Customers;

import com.manufacturing.manufacturingmanagementsystem.dtos.requests.Order.OrderRequest;
import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;
import com.manufacturing.manufacturingmanagementsystem.models.OrdersEntity;
import com.manufacturing.manufacturingmanagementsystem.repositories.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
public class CustomersServices implements ICustomersServices {

    private final CustomersRepository customersRepository;

    @Override
    public CustomersEntity insertCustomerForOrder(String name,String contact) {
        try {
            CustomersEntity customersEntity = new CustomersEntity();
            customersEntity.setName(name);
            customersEntity.setContact(contact);
            customersRepository.save(customersEntity);
            return customersEntity;
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert customer: " + e.getMessage());
        }
    }
}

