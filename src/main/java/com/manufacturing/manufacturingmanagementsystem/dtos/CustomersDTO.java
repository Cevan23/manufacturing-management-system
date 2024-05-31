package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Author: Pham Van Cao
// this class is used to handle the CustomersDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomersDTO {

    private Long id;
    private String name;
    private String address;
    private String contact;

}
