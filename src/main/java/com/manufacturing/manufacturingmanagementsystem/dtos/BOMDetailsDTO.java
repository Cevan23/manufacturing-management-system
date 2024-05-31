package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Author: Pham Van Cao
// this class is used to handle the BOMDetailsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BOMDetailsDTO {

    private Long BOMId;

    private Long materialId;

    private Integer quantity;

    private Float totalUnitPrice;
}
