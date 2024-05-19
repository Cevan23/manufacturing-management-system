package com.manufacturing.manufacturingmanagementsystem.dtos.responses.WorkOrder;

import com.manufacturing.manufacturingmanagementsystem.models.WorkOrderDetailsEntity;
import com.manufacturing.manufacturingmanagementsystem.models.WorkOrdersEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class WorkOrderResponse {

    private WorkOrdersEntity workOrder;

    private List<WorkOrderDetailsEntity> workOrderDetails;
}
