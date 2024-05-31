package com.manufacturing.manufacturingmanagementsystem.dtos;
import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the WorkOrderDetailsDTO response
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrderDetailsDTO {

    private String workOrderId;

    private Long masterProductionScheduleId;

    private String note;

    private Integer projectedProduction;

    private Integer actualProduction;

    private Integer faultyProducts;

    private Double actualProductionPrice;

    private Double faultyProductPrice;
}
