package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Customer;

import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;

import java.util.List;
// Author: Pham Hien Nhan
// this class is used to handle the CustomerList response
public class CustomerListResponse {

    private final List<CustomerResponse> customers;

    public CustomerListResponse(List<CustomerResponse> customers) {
        this.customers = customers;
    }

    public static CustomerListResponse fromCustomerList(List<CustomersEntity> customerEntities) {
        List<CustomerResponse> customerResponses = customerEntities.stream()
                .map(CustomerResponse::fromCustomer)
                .toList();

        return new CustomerListResponse(customerResponses);
    }

    public List<CustomerResponse> getCustomers() {
        return customers;
    }
}
