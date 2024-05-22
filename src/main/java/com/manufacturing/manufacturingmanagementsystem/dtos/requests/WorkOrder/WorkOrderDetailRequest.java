package com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderDetailRequest {

    private Long id;

    private Long workOrderId;

    private Long masterProductionScheduleId;

    private String note;

    private Integer projectedProduction;

    private Integer actualProduction;

    private Integer faultyProducts;

    private Double actualProductionPrice;

    private Double faultyProductPrice;
}
