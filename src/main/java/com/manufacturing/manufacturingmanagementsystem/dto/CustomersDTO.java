package com.manufacturing.manufacturingmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    private Long id;
    private String name;
    private String address;
    private String contact;

}
