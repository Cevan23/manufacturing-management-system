package com.manufacturing.manufacturingmanagementsystem.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialsDTO {

    private String name;

    private String unit;

    private Double price;

    private Double volume;
}