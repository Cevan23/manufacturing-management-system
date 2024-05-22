package com.manufacturing.manufacturingmanagementsystem.service.Customers;

import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;

public interface ICustomersServices {
    CustomersEntity insertCustomerForOrder(String name, String contact);
}

