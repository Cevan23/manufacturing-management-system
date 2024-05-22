package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;

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