package com.manufacturing.manufacturingmanagementsystem.dtos.requests.WorkOrder;

import lombok.*;
// Author: Pham Van Cao
// this class is used to handle the WorkOrderDetail request
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderDetailRequest {

    private Long workOrderId;

    private Long masterProductionScheduleId;

    private Double productPrice;

    private String note;

    private Integer projectedProduction;

    private Integer actualProduction;

    private Integer faultyProducts;

    private Double actualProductionPrice;

    private Double faultyProductPrice;
}
