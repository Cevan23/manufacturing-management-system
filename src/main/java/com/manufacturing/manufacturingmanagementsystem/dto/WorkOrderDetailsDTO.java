package com.manufacturing.manufacturingmanagementsystem.dto;
import lombok.*;

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
