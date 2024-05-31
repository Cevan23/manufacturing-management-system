package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the MaterialsDTO response
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsDTO {

    private Long id;

    private String name;

    private String unit;

    private Double price;

    private Double volume;
}