package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BOMsDTO {

    private Long productManagerId;
    private String name;
    private String BOMstatus;
    private Date dateCreation;
    private Float timeProduction;
    private String unit;
    private Double totalPrice;
    private Double sellPrice;
}
