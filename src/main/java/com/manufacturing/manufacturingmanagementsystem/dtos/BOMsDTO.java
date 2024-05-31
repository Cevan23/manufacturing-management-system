package com.manufacturing.manufacturingmanagementsystem.dtos;

import lombok.*;

import java.util.Date;
// Author: Pham Van Cao
// this class is used to handle the BOMsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
