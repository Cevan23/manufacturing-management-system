package com.manufacturing.manufacturingmanagementsystem.dtos.responses.Customer;


import com.manufacturing.manufacturingmanagementsystem.models.CustomersEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String name;
    private String address;
    private String contact;
    public static CustomerResponse fromCustomer(CustomersEntity customer) {
        return CustomerResponse.builder()
                .name(customer.getName())
                .address(customer.getAddress())
                .contact(customer.getContact())
                .build();
    }
}
