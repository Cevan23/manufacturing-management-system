package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BOMsDTO {
    private Long id;
    private Long productManagerId;
    private String name;
    private String BOMstatus;
    private Date dateCreation;
    private Float timeProduction;
    private String unit;
    private Double totalPrice;
    private Double sellPrice;
}
